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
@Table(name = "stockout")
@NamedQueries({
    @NamedQuery(name = "Stockout.findAll", query = "SELECT s FROM Stockout s"),
    @NamedQuery(name = "Stockout.findByStockoutId", query = "SELECT s FROM Stockout s WHERE s.stockoutId = :stockoutId"),
    @NamedQuery(name = "Stockout.findByDate", query = "SELECT s FROM Stockout s WHERE s.date = :date"),
    @NamedQuery(name = "Stockout.findByQuantity", query = "SELECT s FROM Stockout s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "Stockout.findByDetails", query = "SELECT s FROM Stockout s WHERE s.details = :details")})
public class Stockout implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stockout_id", nullable = false)
    private Integer stockoutId;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "details", nullable = false, length = 145)
    private String details;
    @JoinColumn(name = "source", referencedColumnName = "destination_code", nullable = false)
    @ManyToOne(optional = false)
    private Destination source;
    @JoinColumn(name = "item", referencedColumnName = "item_code", nullable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "user", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users user;

    public Stockout() {
    }

    public Stockout(Integer stockoutId) {
        this.stockoutId = stockoutId;
    }

    public Stockout(Integer stockoutId, Date date, int quantity, String details) {
        this.stockoutId = stockoutId;
        this.date = date;
        this.quantity = quantity;
        this.details = details;
    }

    public Integer getStockoutId() {
        return stockoutId;
    }

    public void setStockoutId(Integer stockoutId) {
        this.stockoutId = stockoutId;
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

    public Destination getSource() {
        return source;
    }

    public void setSource(Destination source) {
        this.source = source;
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
        hash += (stockoutId != null ? stockoutId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stockout)) {
            return false;
        }
        Stockout other = (Stockout) object;
        if ((this.stockoutId == null && other.stockoutId != null) || (this.stockoutId != null && !this.stockoutId.equals(other.stockoutId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Stockout[ stockoutId=" + stockoutId + " ]";
    }
    
}
