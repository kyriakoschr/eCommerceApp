/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kc
 */
@Entity
@Table(name = "Messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findById", query = "SELECT m FROM Messages m WHERE m.id = :id"),
    @NamedQuery(name = "Messages.findByDateTime", query = "SELECT m FROM Messages m WHERE m.dateTime = :dateTime"),
    @NamedQuery(name = "Messages.findByMessage", query = "SELECT m FROM Messages m WHERE m.message = :message"),
    @NamedQuery(name = "Messages.findBySeen", query = "SELECT m FROM Messages m WHERE m.seen = :seen")})
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Size(max = 256)
    @Column(name = "Message")
    private String message;
    @Column(name = "Seen")
    private Boolean seen;
    @JoinColumn(name = "From_UserID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private User fromUserID;
    @JoinColumn(name = "To_UserID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private User toUserID;

    public Messages() {
    }

    public Messages(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public User getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(User fromUserID) {
        this.fromUserID = fromUserID;
    }

    public User getToUserID() {
        return toUserID;
    }

    public void setToUserID(User toUserID) {
        this.toUserID = toUserID;
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
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.serverside.Messages[ id=" + id + " ]";
    }
    
}
