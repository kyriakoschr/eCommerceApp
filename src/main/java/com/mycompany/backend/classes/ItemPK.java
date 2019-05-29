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
import javax.validation.constraints.Size;

/**
 *
 * @author kc
 */
@Embeddable
public class ItemPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "Seller_ID")
    private String sellerID;

    public ItemPK() {
    }

    public ItemPK(int id, String sellerID) {
        this.id = id;
        this.sellerID = sellerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (sellerID != null ? sellerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPK)) {
            return false;
        }
        ItemPK other = (ItemPK) object;
        if (this.id != other.id) {
            return false;
        }
        if ((this.sellerID == null && other.sellerID != null) || (this.sellerID != null && !this.sellerID.equals(other.sellerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.backend.classes.ItemPK[ id=" + id + ", sellerID=" + sellerID + " ]";
    }
    
}
