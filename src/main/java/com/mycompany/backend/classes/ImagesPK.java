/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kc
 */
@Embeddable
public class ImagesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Item_ID")
    private int itemID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;

    public ImagesPK() {
    }

    public ImagesPK(int itemID, int id) {
        this.itemID = itemID;
        this.id = id;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemID;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagesPK)) {
            return false;
        }
        ImagesPK other = (ImagesPK) object;
        if (this.itemID != other.itemID) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.backend.classes.ImagesPK[ itemID=" + itemID + ", id=" + id + " ]";
    }
    
}
