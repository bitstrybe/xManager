/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xmanager.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "category")
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCategoryCode", query = "SELECT c FROM Category c WHERE c.categoryCode = :categoryCode"),
    @NamedQuery(name = "Category.findByCategoryName", query = "SELECT c FROM Category c WHERE c.categoryName = :categoryName"),
    @NamedQuery(name = "Category.findByDateCreated", query = "SELECT c FROM Category c WHERE c.dateCreated = :dateCreated")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "category_code", nullable = false, length = 45)
    private String categoryCode;
    @Basic(optional = false)
    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Collection<Item> itemCollection;

    public Category() {
    }

    public Category(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Category(String categoryCode, String categoryName, Date dateCreated) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.dateCreated = dateCreated;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryCode != null ? categoryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.categoryCode == null && other.categoryCode != null) || (this.categoryCode != null && !this.categoryCode.equals(other.categoryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Category[ categoryCode=" + categoryCode + " ]";
    }
    
}
