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
@Table(name = "sales")
@NamedQueries({
    @NamedQuery(name = "Sales.findAll", query = "SELECT s FROM Sales s"),
    @NamedQuery(name = "Sales.findBySalesId", query = "SELECT s FROM Sales s WHERE s.salesId = :salesId"),
    @NamedQuery(name = "Sales.findByQuantity", query = "SELECT s FROM Sales s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "Sales.findByPrice", query = "SELECT s FROM Sales s WHERE s.price = :price"),
    @NamedQuery(name = "Sales.findByDate", query = "SELECT s FROM Sales s WHERE s.date = :date"),
    @NamedQuery(name = "Sales.findByEntry", query = "SELECT s FROM Sales s WHERE s.entry = :entry")})
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sales_id", nullable = false)
    private Integer salesId;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private float price;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "entry", nullable = false, length = 45)
    private String entry;
    @JoinColumn(name = "item", referencedColumnName = "item_code", nullable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "shop", referencedColumnName = "shopsid", nullable = false)
    @ManyToOne(optional = false)
    private Shops shop;
    @JoinColumn(name = "user", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users user;

    public Sales() {
    }

    public Sales(Integer salesId) {
        this.salesId = salesId;
    }

    public Sales(Integer salesId, int quantity, float price, Date date, String entry) {
        this.salesId = salesId;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.entry = entry;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Shops getShop() {
        return shop;
    }

    public void setShop(Shops shop) {
        this.shop = shop;
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
        hash += (salesId != null ? salesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sales)) {
            return false;
        }
        Sales other = (Sales) object;
        if ((this.salesId == null && other.salesId != null) || (this.salesId != null && !this.salesId.equals(other.salesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Sales[ salesId=" + salesId + " ]";
    }
    
}
