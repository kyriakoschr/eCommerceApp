/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside.service;

import com.mycompany.serverside.Login;
import com.mycompany.serverside.User;
import com.mycompany.serverside.utilities.KeyHolder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kc
 */
@Stateless
@Path("login")
public class LoginFacadeREST {

    @PersistenceContext(unitName = "com.mycompany_serverSide_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @POST
    @Path("/login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String login(final Login loginData) {
        Login logged = this.login(loginData.getUsername(), loginData.getPassword());
        if (logged != null) {
            String token = issueToken(loginData.getUsername());
            System.out.println("herre");
            return token;
        }
        else {
            return "not";
        }
    }
    
    private String issueToken(String username) {
            Key key = KeyHolder.key;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            long expMillis = nowMillis + 3000000L;
            Date exp = new Date(expMillis);
            System.out.println(exp);
            String jws = Jwts.builder()
                        .setSubject(username)
                        .setIssuedAt(now)
                        .signWith(SignatureAlgorithm.HS512, key)
                        .setExpiration(exp)
                        .compact();
            return jws.trim();
    }
    
    private Login login(String username, String password)
    {
        Login login = new Login();  
        Query q = em.createQuery("Select u from User u where u.username = :username and u.password = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        User logins;
        try {
            logins =  (User) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
        if (logins!=null)
        {
            System.out.println("Uname: "+logins.getUsername()+"Pw: "+logins.getPassword());
            login.setPassword(logins.getPassword());
            login.setUsername(logins.getUsername());
        }

        return login;    
    }
}
