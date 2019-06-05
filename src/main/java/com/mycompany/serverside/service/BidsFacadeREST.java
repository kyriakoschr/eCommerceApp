/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside.service;

import com.mycompany.serverside.Bids;
import com.mycompany.serverside.BidsPK;
import com.mycompany.serverside.Item;
import com.mycompany.serverside.filters.AuthenticationFilter;
import java.util.Date;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author kc
 */
@Stateless
@Path("bids")
public class BidsFacadeREST extends AbstractFacade<Bids> {

    @PersistenceContext(unitName = "com.mycompany_serverSide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private BidsPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;itemID=itemIDValue;bidderID=bidderIDValue;dateTime=dateTimeValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.mycompany.serverside.BidsPK key = new com.mycompany.serverside.BidsPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> itemID = map.get("itemID");
        if (itemID != null && !itemID.isEmpty()) {
            key.setItemID(new java.lang.Integer(itemID.get(0)));
        }
        java.util.List<String> bidderID = map.get("bidderID");
        if (bidderID != null && !bidderID.isEmpty()) {
            key.setBidderID(bidderID.get(0));
        }
        java.util.List<String> dateTime = map.get("dateTime");
        if (dateTime != null && !dateTime.isEmpty()) {
            key.setDateTime(new java.util.Date(dateTime.get(0)));
        }
        return key;
    }

    public BidsFacadeREST() {
        super(Bids.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(@HeaderParam("Authorization") String token,Bids entity) throws Exception {
        AuthenticationFilter.filter(token);
        Item item=(Item) em.createNamedQuery("Item.findById").setParameter("id",entity.getBidsPK().getItemID()).getSingleResult();
        BidsPK pk = entity.getBidsPK();
        pk.setDateTime(new Date());
        entity.setBidsPK(pk);
        if(entity.getAmount()>item.getCurrentPrice()) {
            if(entity.getBidsPK().getDateTime().compareTo(item.getEndDate())<0) {
                if(!item.getSellerID().equals(entity.getUser())){
                    item.addBid(entity.getAmount());
                    em.merge(item);
                    super.create(entity);
                }
            }
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@HeaderParam("Authorization") String token,@PathParam("id") PathSegment id, Bids entity) throws Exception {
        AuthenticationFilter.filter(token);
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@HeaderParam("Authorization") String token,@PathParam("id") PathSegment id) throws Exception {
        AuthenticationFilter.filter(token);
        com.mycompany.serverside.BidsPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bids find(@PathParam("id") PathSegment id) {
        com.mycompany.serverside.BidsPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bids> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bids> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
