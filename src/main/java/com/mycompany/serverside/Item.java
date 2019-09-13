/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id"),
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name"),
    @NamedQuery(name = "Item.findByCurrentPrice", query = "SELECT i FROM Item i WHERE i.currentPrice = :currentPrice"),
    @NamedQuery(name = "Item.findByFirstBid", query = "SELECT i FROM Item i WHERE i.firstBid = :firstBid"),
    @NamedQuery(name = "Item.findByNumofbids", query = "SELECT i.numofbids FROM Item i WHERE i.id = :id"),
    @NamedQuery(name = "Item.findByLocation", query = "SELECT i FROM Item i WHERE i.location = :location"),
    @NamedQuery(name = "Item.findByCountry", query = "SELECT i FROM Item i WHERE i.country = :country"),
    @NamedQuery(name = "Item.findByStartDate", query = "SELECT i FROM Item i WHERE i.startDate = :startDate"),
    @NamedQuery(name = "Item.findByEndDate", query = "SELECT i FROM Item i WHERE i.endDate = :endDate"),
    @NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item i WHERE i.description = :description"),
    @NamedQuery(name = "Item.findBySeller", query = "SELECT i FROM Item i WHERE i.sellerID = :sellerID")})
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    @GeneratedValue (strategy=GenerationType.SEQUENCE, generator="seq")
    private Integer id;
    @Size(max = 45)
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
    @ManyToMany(mappedBy = "itemCollection",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
//    @JsonIgnore
    private Set<Category> categoryCollection = new HashSet<Category>();
    @JoinColumn(name = "Seller_ID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private User sellerID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemID")
    @JsonbTransient
    private Set<Images> imagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
//    @JsonbTransient
    private Collection<Bids> bidsCollection;

    public Item() {
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void addCategory(Category cat){
        categoryCollection.add(cat);
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Set<Category> getCategoryCollection() {
        return categoryCollection;
    }
    
    public void setCategoryCollection(HashSet<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }

    public User getSellerID() {
        return sellerID;
    }

    public void setSellerID(User sellerID) {
        this.sellerID = sellerID;
    }

    public void addBid(float bid){
        numofbids++;
        currentPrice=bid;
    }
    
//    @XmlTransient
//    @JsonbTransient
//    @JsonIgnore
    public Set<Images> getImagesCollection() {
        return imagesCollection;
    }

    public void setImagesCollection(Set<Images> imagesCollection) {
        this.imagesCollection = imagesCollection;
    }

//    @XmlTransient
//    @JsonbTransient
//    @JsonIgnore
    public Collection<Bids> getBidsCollection() {
        Collections.sort((List<Bids>) bidsCollection);
        Collections.reverse((List<Bids>) bidsCollection);
        return bidsCollection;
    }

    public void setBidsCollection(Collection<Bids> bidsCollection) {
        this.bidsCollection = bidsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.serverside.Item[ id=" + id + " ]";
    }
    
}
