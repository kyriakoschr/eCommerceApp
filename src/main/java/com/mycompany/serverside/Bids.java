/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "Bids")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bids.findAll", query = "SELECT b FROM Bids b"),
    @NamedQuery(name = "Bids.findByItemID", query = "SELECT b FROM Bids b WHERE b.bidsPK.itemID = :itemID"),
    @NamedQuery(name = "Bids.findByBidderID", query = "SELECT b FROM Bids b WHERE b.bidsPK.bidderID = :bidderID"),
    @NamedQuery(name = "Bids.findByDateTime", query = "SELECT b FROM Bids b WHERE b.bidsPK.dateTime = :dateTime"),
    @NamedQuery(name = "Bids.findByAmount", query = "SELECT b FROM Bids b WHERE b.amount = :amount")})
public class Bids implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BidsPK bidsPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Amount")
    private Float amount;
    @JoinColumn(name = "Item_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "Bidder_ID", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Bids() {
    }

    public Bids(BidsPK bidsPK) {
        this.bidsPK = bidsPK;
    }

    public Bids(int itemID, String bidderID, Date dateTime) {
        this.bidsPK = new BidsPK(itemID, bidderID, dateTime);
    }

    public BidsPK getBidsPK() {
        return bidsPK;
    }

    public void setBidsPK(BidsPK bidsPK) {
        this.bidsPK = bidsPK;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bidsPK != null ? bidsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bids)) {
            return false;
        }
        Bids other = (Bids) object;
        if ((this.bidsPK == null && other.bidsPK != null) || (this.bidsPK != null && !this.bidsPK.equals(other.bidsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.serverside.Bids[ bidsPK=" + bidsPK + " ]";
    }
    
}
