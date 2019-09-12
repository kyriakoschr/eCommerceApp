/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside.service;

import com.mycompany.serverside.Messages;
import com.mycompany.serverside.User;
import com.mycompany.serverside.filters.AuthenticationFilter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("messages")
public class MessagesFacadeREST extends AbstractFacade<Messages> {

    @PersistenceContext(unitName = "com.mycompany_serverSide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public MessagesFacadeREST() {
        super(Messages.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Messages entity,@HeaderParam("Authorization") String token) throws Exception {
        AuthenticationFilter.filter(token);
        User to=entity.getToUserID();
        User from=entity.getFromUserID();
        entity.setSeen(false);
        entity.setDateTime(new Date());
        long res = (long) em.createQuery("Select count(b) from Bids b,Item i "
                + "where (b.user=:bidder and b.item= i and i.sellerID=:seller "
                + "and i.endDate < CURRENT_TIMESTAMP "
                + "and b.amount in (select max(bb.amount) from Bids bb where bb.item=i)) "
                + "or (b.user=:seller and b.item= i and i.sellerID=:bidder "
                + "and i.endDate < CURRENT_TIMESTAMP "
                + "and b.amount in (select max(bb.amount) from Bids bb where bb.item=i))")
                .setParameter("bidder", from)
                .setParameter("seller", to)
                .getSingleResult();
        if(res>0)
            super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@HeaderParam("Authorization") String token,@PathParam("id") Integer id, Messages entity) throws Exception {
        AuthenticationFilter.filter(token);
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@HeaderParam("Authorization") String token,@PathParam("id") Integer id) throws Exception {
        AuthenticationFilter.filter(token);
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Messages find(@HeaderParam("Authorization") String token,@PathParam("id") Integer id) throws Exception {
        AuthenticationFilter.filter(token);
        return super.find(id);
    }
    
    @GET
    @Path("from/{id}")
    @Produces( MediaType.APPLICATION_JSON)
    public List<Messages> findfrom(@HeaderParam("Authorization") String token,@PathParam("id") String id) throws Exception {
        AuthenticationFilter.filter(token);
        User from = (User) em.createNamedQuery("User.findByUsername").setParameter("username", id).getSingleResult();
        List<Messages> res = em.createNamedQuery("Messages.findByFrom").setParameter("from",from).getResultList();
        return res;
    }

    @GET
    @Path("to/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Messages> findto(@HeaderParam("Authorization") String token,@PathParam("id") String id) throws Exception {
        System.out.println("B4 AUTH");
        AuthenticationFilter.filter(token);
        System.out.println("AFTER AUTH");
        User to = (User) em.createNamedQuery("User.findByUsername").setParameter("username", id).getSingleResult();
        List<Messages> res = em.createNamedQuery("Messages.findByTo").setParameter("to",to).getResultList();
        System.out.println(res.size());
        return res;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Messages> findAll(@HeaderParam("Authorization") String token) {
        try {
            AuthenticationFilter.filter(token);
        } catch (Exception ex) {
            Logger.getLogger(MessagesFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Messages> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count/{to}")
    @Produces(MediaType.TEXT_PLAIN)
    public Long countREST(@HeaderParam("Authorization") String token,@PathParam("to") String to) throws Exception {
        AuthenticationFilter.filter(token);
        long msgs = (long) em.createNamedQuery("Messages.findBySeenTo")
        .setParameter("seen",false)
        .setParameter("toUserID",em.createNamedQuery("User.findByUsername")
                                .setParameter("username", to)
                                .getSingleResult()   )
        .getSingleResult();
        return msgs;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
