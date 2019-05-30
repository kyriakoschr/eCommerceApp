/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gunner
 */
@Embeddable
public class BidsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Item_ID")
    private int itemID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "Bidder_ID")
    private String bidderID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    public BidsPK() {
    }

    public BidsPK(int itemID, String bidderID, Date dateTime) {
        this.itemID = itemID;
        this.bidderID = bidderID;
        this.dateTime = dateTime;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getBidderID() {
        return bidderID;
    }

    public void setBidderID(String bidderID) {
        this.bidderID = bidderID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemID;
        hash += (bidderID != null ? bidderID.hashCode() : 0);
        hash += (dateTime != null ? dateTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BidsPK)) {
            return false;
        }
        BidsPK other = (BidsPK) object;
        if (this.itemID != other.itemID) {
            return false;
        }
        if ((this.bidderID == null && other.bidderID != null) || (this.bidderID != null && !this.bidderID.equals(other.bidderID))) {
            return false;
        }
        if ((this.dateTime == null && other.dateTime != null) || (this.dateTime != null && !this.dateTime.equals(other.dateTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.BidsPK[ itemID=" + itemID + ", bidderID=" + bidderID + ", dateTime=" + dateTime + " ]";
    }
    
}
