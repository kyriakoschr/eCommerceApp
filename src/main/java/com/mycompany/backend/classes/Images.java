/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.classes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kc
 */
@Entity
@Table(name = "Images")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Images.findAll", query = "SELECT i FROM Images i"),
    @NamedQuery(name = "Images.findByItemID", query = "SELECT i FROM Images i WHERE i.imagesPK.itemID = :itemID"),
    @NamedQuery(name = "Images.findById", query = "SELECT i FROM Images i WHERE i.imagesPK.id = :id")})
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ImagesPK imagesPK;
    @Lob
    @Column(name = "Image")
    private byte[] image;
    @JoinColumn(name = "Item_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;

    public Images() {
    }

    public Images(ImagesPK imagesPK) {
        this.imagesPK = imagesPK;
    }

    public Images(int itemID, int id) {
        this.imagesPK = new ImagesPK(itemID, id);
    }

    public ImagesPK getImagesPK() {
        return imagesPK;
    }

    public void setImagesPK(ImagesPK imagesPK) {
        this.imagesPK = imagesPK;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imagesPK != null ? imagesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Images)) {
            return false;
        }
        Images other = (Images) object;
        if ((this.imagesPK == null && other.imagesPK != null) || (this.imagesPK != null && !this.imagesPK.equals(other.imagesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.backend.classes.Images[ imagesPK=" + imagesPK + " ]";
    }
    
}
