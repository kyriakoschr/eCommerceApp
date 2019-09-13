/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside.service;

import com.mycompany.serverside.Category;
import com.mycompany.serverside.Images;
import com.mycompany.serverside.Item;
import com.mycompany.serverside.User;
import com.mycompany.serverside.filters.AuthenticationFilter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("item")
public class ItemFacadeREST extends AbstractFacade<Item> {

    @PersistenceContext(unitName = "com.mycompany_serverSide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ItemFacadeREST() {
        super(Item.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public int create(@HeaderParam("Authorization") String token,Item entity) throws Exception {
        AuthenticationFilter.filter(token);
        HashSet<Category> categories = (HashSet<Category>) entity.getCategoryCollection();
        entity.setCategoryCollection(new HashSet<Category>() );
        super.create(entity);
//        System.out.println(entity.getId());
        for (Category cat : categories) {
            System.out.println(cat.getName());
            try{
                Category temp = (Category)em.createNamedQuery("Category.findByName").setParameter("name", cat.getName()).getSingleResult();
                System.out.println("FOUND existing !!!!!!!!!!!!!!!!!!!!");
                cat.addItem(entity);
                em.merge(entity);
                em.merge(cat);
                em.flush();
//                entity.setCategoryCollection(new HashSet<Category>());
//                super.create(entity);
//                em.flush();
//                temp.get(0).addItem(entity);
//                em.persist(entity);
//                em.persist(temp.get(0));
//                em.flush();
            }
            catch(NoResultException e){
                System.out.println("FOUND NEW!!!!!!!!!!!!!!!!!!!!");
                cat.addItem(entity);
                em.merge(cat);  
                em.merge(entity);
            }
//            else{
//                System.out.println("FOUND existing !!!!!!!!!!!!!!!!!!!!");
//                entity.setCategoryCollection(new HashSet<Category>());
//                super.create(entity);
//            em.flush();
//                temp.get(0).addItem(entity);
//        em.persist(entity);
//                em.persist(temp.get(0));
//        em.flush();
//            }
        }
//        entity.setCategoryCollection(categories);
//        em.merge(entity);
        System.out.println("ID of item is "+entity.getId());
        return entity.getId();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@HeaderParam("Authorization") String token,@PathParam("id") Integer id, Item entity) throws Exception {
        AuthenticationFilter.filter(token);
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@HeaderParam("Authorization") String token,@PathParam("id") Integer id) throws Exception {
        AuthenticationFilter.filter(token);
        System.out.println("DELEET");
        int res=(int) em.createNamedQuery("Item.findByNumofbids").setParameter("id",id).getSingleResult();
        if(res==0)
            super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Item find(@PathParam("id") Integer    id) {
        return super.find(id);
    }

    @GET
    @Path("byDesc/{words}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Item> find(@PathParam("words") String words) {
        System.out.println(words);
        Query query = em.createNativeQuery("select * from Item where match (Description,Name) against (? in natural language MODE)",Item.class);
        query.setParameter(1, words);
        if(query.getResultList().isEmpty()){
            System.out.println("EMPTY");
            return null;
        }
        return query.getResultList();
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> findAll() {
        List<Item> items= super.findAll();
        for(Item i:items)
            i.setImagesCollection(null);
        return items;
    }
    
    @GET
    @Path("seller/{seller}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Item> findbySeller(@HeaderParam("Authorization") String token,@PathParam("seller") String sellerID ) throws Exception {
//        AuthenticationFilter.filter(token);
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

    @GET
    @Path("images/{id}")
//    @Produces(MediaType.TEXT_PLAIN)
    public byte[] fetchImage(@PathParam("id") Integer id) {
        System.out.println("ID is "+id);
        Item item = find(id);
        if(item.getImagesCollection().isEmpty()){
            System.out.println("NULL "+"ID is "+id);
            return null;
        }
        return item.getImagesCollection().iterator().next().getImage();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
