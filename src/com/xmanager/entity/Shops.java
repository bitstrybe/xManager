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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "shops")
@NamedQueries({
    @NamedQuery(name = "Shops.findAll", query = "SELECT s FROM Shops s"),
    @NamedQuery(name = "Shops.findByShopsid", query = "SELECT s FROM Shops s WHERE s.shopsid = :shopsid"),
    @NamedQuery(name = "Shops.findByShopName", query = "SELECT s FROM Shops s WHERE s.shopName = :shopName"),
    @NamedQuery(name = "Shops.findByType", query = "SELECT s FROM Shops s WHERE s.type = :type")})
public class Shops implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shopsid", nullable = false)
    private Integer shopsid;
    @Basic(optional = false)
    @Column(name = "shop_name", nullable = false, length = 45)
    private String shopName;
    @Column(name = "type")
    private Integer type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    private Collection<Sales> salesCollection;

    public Shops() {
    }

    public Shops(Integer shopsid) {
        this.shopsid = shopsid;
    }

    public Shops(Integer shopsid, String shopName) {
        this.shopsid = shopsid;
        this.shopName = shopName;
    }

    public Integer getShopsid() {
        return shopsid;
    }

    public void setShopsid(Integer shopsid) {
        this.shopsid = shopsid;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Collection<Sales> getSalesCollection() {
        return salesCollection;
    }

    public void setSalesCollection(Collection<Sales> salesCollection) {
        this.salesCollection = salesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shopsid != null ? shopsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shops)) {
            return false;
        }
        Shops other = (Shops) object;
        if ((this.shopsid == null && other.shopsid != null) || (this.shopsid != null && !this.shopsid.equals(other.shopsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Shops[ shopsid=" + shopsid + " ]";
    }
    
}
