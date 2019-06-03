/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside.service;

import com.mycompany.serverside.Category;
import com.mycompany.serverside.Item;
import com.mycompany.serverside.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Path("item")
public class ItemFacadeREST extends AbstractFacade<Item> {

    @PersistenceContext(unitName = "com.mycompany_serverSide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ItemFacadeREST() {
        super(Item.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Item entity) {
        HashSet<Category> categories = (HashSet<Category>) entity.getCategoryCollection();
//        System.out.println(entity.getId());
        for (Category cat : categories) {
            System.out.println(cat.getName());
            List<Category> temp = (List<Category>)em.createNamedQuery("Category.findByName").setParameter("name", cat.getName()).getResultList();
            if(temp.isEmpty()){
                System.out.println("FOUND NEW!!!!!!!!!!!!!!!!!!!!");
                cat.addItem(entity);
                em.persist(cat);              
            }
            else{
                System.out.println("FOUND existing !!!!!!!!!!!!!!!!!!!!");
                entity.setCategoryCollection(new HashSet<Category>());
                super.create(entity);
                em.flush();
                temp.get(0).addItem(entity);
                em.persist(entity);
                em.persist(temp.get(0));
                em.flush();
            }
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Item entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        int res=(int) em.createNamedQuery("Item.findByNumofbids").setParameter("id",id).getSingleResult();
        if(res==0)
            super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("byDesc/{words}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Item> find(@PathParam("words") String words) {
        Query query = em.createNativeQuery("select * from Item where match (Description,Name) against (? in natural language MODE)",Item.class);
        query.setParameter(1, words);
        return query.getResultList();
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("seller/{seller}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> findbySeller(@PathParam("seller") String sellerID ) {
        User seller=(User) em.createNamedQuery("User.findByUsername").setParameter("username",sellerID).getSingleResult();
        return em.createNamedQuery("Item.findBySeller").setParameter("sellerID",seller).getResultList();
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
