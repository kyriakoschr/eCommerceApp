/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside.service;

import com.mycompany.serverside.User;
import com.mycompany.serverside.filters.AuthenticationFilter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kc
 */
@Stateless
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "com.mycompany_serverSide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        System.out.println("CREATE "+entity.getUsername());
//        List<User> u=(List<User>)em.createNamedQuery("User.findByUsername").setParameter("username", entity.getUsername()).getResultList();
//        if(!u.isEmpty()){
//            return;
//        }
//        List<User> u2=(List<User>)em.createNamedQuery("User.findByEmail").setParameter("email", entity.getEmail()).getResultList();
//        if(!u2.isEmpty()){
//            return null;
//        }
        super.create(entity);   
//        return entity;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public int create2(User entity) {
        System.out.println("CREATE 2"+entity.getUsername());
        List<User> u=(List<User>)em.createNamedQuery("User.findByUsername").setParameter("username", entity.getUsername()).getResultList();
        if(!u.isEmpty()){
            return 1;
        }
        List<User> u2=(List<User>)em.createNamedQuery("User.findByEmail").setParameter("email", entity.getEmail()).getResultList();
        if(!u2.isEmpty()){
            return 2;
        }
        List<User> u3=(List<User>)em.createNamedQuery("User.findByAfm").setParameter("afm", entity.getAfm()).getResultList();
        if(!u3.isEmpty()){
            return 3;
        }
        return super.create2(entity);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@HeaderParam("Authorization") String token, @PathParam("id") String id, User entity) throws Exception {
        AuthenticationFilter.filter(token);
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@HeaderParam("Authorization") String token,@PathParam("id") String id) throws Exception {
        AuthenticationFilter.filter(token);
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") String id,@HeaderParam("Authorization") String token) throws Exception {
        AuthenticationFilter.filter(token);
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll(@HeaderParam("Authorization") String token) throws Exception {
        AuthenticationFilter.filter(token);
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("check/{uname}")
    public Boolean checkUname(@PathParam("uname") String uname){
        return super.find(uname)==null;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
