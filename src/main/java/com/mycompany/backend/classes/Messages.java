/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.classes;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Messages.findByFromUserID", query = "SELECT m FROM Messages m WHERE m.messagesPK.fromUserID = :fromUserID"),
    @NamedQuery(name = "Messages.findByToUserID", query = "SELECT m FROM Messages m WHERE m.messagesPK.toUserID = :toUserID"),
    @NamedQuery(name = "Messages.findById", query = "SELECT m FROM Messages m WHERE m.messagesPK.id = :id"),
    @NamedQuery(name = "Messages.findByDateTime", query = "SELECT m FROM Messages m WHERE m.dateTime = :dateTime"),
    @NamedQuery(name = "Messages.findByMessage", query = "SELECT m FROM Messages m WHERE m.message = :message"),
    @NamedQuery(name = "Messages.findBySeen", query = "SELECT m FROM Messages m WHERE m.seen = :seen")})
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MessagesPK messagesPK;
    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Size(max = 256)
    @Column(name = "Message")
    private String message;
    @Column(name = "Seen")
    private Boolean seen;
    @JoinColumn(name = "From_UserID", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "To_UserID", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public Messages() {
    }

    public Messages(MessagesPK messagesPK) {
        this.messagesPK = messagesPK;
    }

    public Messages(String fromUserID, String toUserID, int id) {
        this.messagesPK = new MessagesPK(fromUserID, toUserID, id);
    }

    public MessagesPK getMessagesPK() {
        return messagesPK;
    }

    public void setMessagesPK(MessagesPK messagesPK) {
        this.messagesPK = messagesPK;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messagesPK != null ? messagesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.messagesPK == null && other.messagesPK != null) || (this.messagesPK != null && !this.messagesPK.equals(other.messagesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.backend.classes.Messages[ messagesPK=" + messagesPK + " ]";
    }
    
}
