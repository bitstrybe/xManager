/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xmanager.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "stockin")
@NamedQueries({
    @NamedQuery(name = "Stockin.findAll", query = "SELECT s FROM Stockin s"),
    @NamedQuery(name = "Stockin.findByStockinId", query = "SELECT s FROM Stockin s WHERE s.stockinId = :stockinId"),
    @NamedQuery(name = "Stockin.findByDate", query = "SELECT s FROM Stockin s WHERE s.date = :date"),
    @NamedQuery(name = "Stockin.findByQuantity", query = "SELECT s FROM Stockin s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "Stockin.findByDetails", query = "SELECT s FROM Stockin s WHERE s.details = :details")})
public class Stockin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stockin_id", nullable = false)
    private Integer stockinId;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "Details", nullable = false, length = 100)
    private String details;
    @JoinColumn(name = "destination", referencedColumnName = "destination_code", nullable = false)
    @ManyToOne(optional = false)
    private Destination destination;
    @JoinColumn(name = "Item", referencedColumnName = "item_code", nullable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "user", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users user;

    public Stockin() {
    }

    public Stockin(Integer stockinId) {
        this.stockinId = stockinId;
    }

    public Stockin(Integer stockinId, Date date, int quantity, String details) {
        this.stockinId = stockinId;
        this.date = date;
        this.quantity = quantity;
        this.details = details;
    }

    public Integer getStockinId() {
        return stockinId;
    }

    public void setStockinId(Integer stockinId) {
        this.stockinId = stockinId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stockinId != null ? stockinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stockin)) {
            return false;
        }
        Stockin other = (Stockin) object;
        if ((this.stockinId == null && other.stockinId != null) || (this.stockinId != null && !this.stockinId.equals(other.stockinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Stockin[ stockinId=" + stockinId + " ]";
    }
    
}
