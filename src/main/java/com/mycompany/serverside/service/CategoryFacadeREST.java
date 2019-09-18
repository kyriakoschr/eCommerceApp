/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside.service;

import com.mycompany.serverside.Category;
import com.mycompany.serverside.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("category")
public class CategoryFacadeREST extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "com.mycompany_serverSide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CategoryFacadeREST() {
        super(Category.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Category entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Category entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Category find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Category> findAll() {
        List<Category> res = super.findAll();
        System.out.println(res.size());
        if(res==null)
            return null;                    
        return res;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Category> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("ibc/{category}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Item> find_itCategory(@PathParam("category") String category) {
        System.out.println(category);
        Category cat = find(category);
        List<Item> res = new ArrayList<Item>();
        res.addAll(cat.getItemCollection());
        if(res.isEmpty())
            return res;
        for (Iterator<Item> it = res.iterator(); it.hasNext();) {
            Item i = it.next();
            if(i.getEndDate()==null||i.getEndDate().before(new Date())){
                it.remove();
            }
            else if(i.getStartDate()==null||i.getStartDate().after(new Date())){
                it.remove();
            }
        }
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
