/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside.service;

import com.mycompany.serverside.Images;
import com.mycompany.serverside.Item;
import com.mycompany.serverside.filters.AuthenticationFilter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jdk.nashorn.internal.parser.JSONParser;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import sun.misc.IOUtils;

/**
 *
 * @author kc
 */
@Stateless
@Path("images")
public class ImagesFacadeREST extends AbstractFacade<Images> {

    @PersistenceContext(unitName = "com.mycompany_serverSide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ImagesFacadeREST() {
        super(Images.class);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void create(@FormDataParam("image") InputStream uploadedInputStream,
            @FormDataParam("data") String data,@HeaderParam("Authorization") String token) throws IOException, JSONException, Exception {
        AuthenticationFilter.filter(token);
        if (uploadedInputStream != null && data != null){
            Images entity = new Images();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] bytes = new byte[16384];
            while ((nRead = uploadedInputStream.read(bytes, 0, bytes.length)) != -1) {
              buffer.write(bytes, 0, nRead);
            }
            entity.setImage(buffer.toByteArray());
            JSONObject jsonObj = new JSONObject(data);
            Item item;
            item = (Item) em.createNamedQuery("Item.findById").setParameter("id",jsonObj.getJSONObject("item").getInt("id")).getSingleResult();
            entity.setId(jsonObj.getInt("id"));
            entity.setItemID(item);
            uploadedInputStream.close();
            super.create(entity);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Images entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Images find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Images> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Images> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
