/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.classes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kc
 */
@Entity
@Table(name = "Item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.itemPK.id = :id"),
    @NamedQuery(name = "Item.findBySellerID", query = "SELECT i FROM Item i WHERE i.itemPK.sellerID = :sellerID"),
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name"),
    @NamedQuery(name = "Item.findByCurrentPrice", query = "SELECT i FROM Item i WHERE i.currentPrice = :currentPrice"),
    @NamedQuery(name = "Item.findByFirstBid", query = "SELECT i FROM Item i WHERE i.firstBid = :firstBid"),
    @NamedQuery(name = "Item.findByNumofbids", query = "SELECT i FROM Item i WHERE i.numofbids = :numofbids"),
    @NamedQuery(name = "Item.findByLocation", query = "SELECT i FROM Item i WHERE i.location = :location"),
    @NamedQuery(name = "Item.findByCountry", query = "SELECT i FROM Item i WHERE i.country = :country"),
    @NamedQuery(name = "Item.findByStartDate", query = "SELECT i FROM Item i WHERE i.startDate = :startDate"),
    @NamedQuery(name = "Item.findByEndDate", query = "SELECT i FROM Item i WHERE i.endDate = :endDate"),
    @NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item i WHERE i.description = :description")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemPK itemPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CurrentPrice")
    private Float currentPrice;
    @Column(name = "FirstBid")
    private Float firstBid;
    @Column(name = "Num_of_bids")
    private Integer numofbids;
    @Size(max = 45)
    @Column(name = "Location")
    private String location;
    @Size(max = 45)
    @Column(name = "Country")
    private String country;
    @Column(name = "Start_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "End_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Size(max = 256)
    @Column(name = "Description")
    private String description;
    @ManyToMany(mappedBy = "itemCollection")
    private Collection<Category> categoryCollection;
    @JoinColumn(name = "Seller_ID", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<Images> imagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<Bids> bidsCollection;

    public Item() {
    }

    public Item(ItemPK itemPK) {
        this.itemPK = itemPK;
    }

    public Item(ItemPK itemPK, String name) {
        this.itemPK = itemPK;
        this.name = name;
    }

    public Item(int id, String sellerID) {
        this.itemPK = new ItemPK(id, sellerID);
    }

    public ItemPK getItemPK() {
        return itemPK;
    }

    public void setItemPK(ItemPK itemPK) {
        this.itemPK = itemPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Float getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(Float firstBid) {
        this.firstBid = firstBid;
    }

    public Integer getNumofbids() {
        return numofbids;
    }

    public void setNumofbids(Integer numofbids) {
        this.numofbids = numofbids;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Category> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(Collection<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public Collection<Images> getImagesCollection() {
        return imagesCollection;
    }

    public void setImagesCollection(Collection<Images> imagesCollection) {
        this.imagesCollection = imagesCollection;
    }

    @XmlTransient
    public Collection<Bids> getBidsCollection() {
        return bidsCollection;
    }

    public void setBidsCollection(Collection<Bids> bidsCollection) {
        this.bidsCollection = bidsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemPK != null ? itemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemPK == null && other.itemPK != null) || (this.itemPK != null && !this.itemPK.equals(other.itemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.backend.classes.Item[ itemPK=" + itemPK + " ]";
    }
    
}
