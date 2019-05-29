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
public class MessagesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "From_UserID")
    private String fromUserID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "To_UserID")
    private String toUserID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;

    public MessagesPK() {
    }

    public MessagesPK(String fromUserID, String toUserID, int id) {
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.id = id;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getToUserID() {
        return toUserID;
    }

    public void setToUserID(String toUserID) {
        this.toUserID = toUserID;
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
        hash += (fromUserID != null ? fromUserID.hashCode() : 0);
        hash += (toUserID != null ? toUserID.hashCode() : 0);
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessagesPK)) {
            return false;
        }
        MessagesPK other = (MessagesPK) object;
        if ((this.fromUserID == null && other.fromUserID != null) || (this.fromUserID != null && !this.fromUserID.equals(other.fromUserID))) {
            return false;
        }
        if ((this.toUserID == null && other.toUserID != null) || (this.toUserID != null && !this.toUserID.equals(other.toUserID))) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.backend.classes.MessagesPK[ fromUserID=" + fromUserID + ", toUserID=" + toUserID + ", id=" + id + " ]";
    }
    
}
