/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xmanager.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByItemCode", query = "SELECT i FROM Item i WHERE i.itemCode = :itemCode"),
    @NamedQuery(name = "Item.findByItemName", query = "SELECT i FROM Item i WHERE i.itemName = :itemName")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "item_code", nullable = false, length = 10)
    private String itemCode;
    @Basic(optional = false)
    @Column(name = "item_name", nullable = false, length = 45)
    private String itemName;
    @JoinColumn(name = "category", referencedColumnName = "category_code", nullable = false)
    @ManyToOne(optional = false)
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<Sales> salesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<Stockout> stockoutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<Stockin> stockinCollection;

    public Item() {
    }

    public Item(String itemCode) {
        this.itemCode = itemCode;
    }

    public Item(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<Sales> getSalesCollection() {
        return salesCollection;
    }

    public void setSalesCollection(Collection<Sales> salesCollection) {
        this.salesCollection = salesCollection;
    }

    public Collection<Stockout> getStockoutCollection() {
        return stockoutCollection;
    }

    public void setStockoutCollection(Collection<Stockout> stockoutCollection) {
        this.stockoutCollection = stockoutCollection;
    }

    public Collection<Stockin> getStockinCollection() {
        return stockinCollection;
    }

    public void setStockinCollection(Collection<Stockin> stockinCollection) {
        this.stockinCollection = stockinCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCode != null ? itemCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemCode == null && other.itemCode != null) || (this.itemCode != null && !this.itemCode.equals(other.itemCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Item[ itemCode=" + itemCode + " ]";
    }
    
}
