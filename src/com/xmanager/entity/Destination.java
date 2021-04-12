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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "destination")
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d"),
    @NamedQuery(name = "Destination.findByDestinationCode", query = "SELECT d FROM Destination d WHERE d.destinationCode = :destinationCode"),
    @NamedQuery(name = "Destination.findByDestination", query = "SELECT d FROM Destination d WHERE d.destination = :destination")})
public class Destination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "destination_code", nullable = false, length = 50)
    private String destinationCode;
    @Basic(optional = false)
    @Column(name = "destination", nullable = false, length = 145)
    private String destination;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "source")
    private Collection<Stockout> stockoutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")
    private Collection<Stockin> stockinCollection;

    public Destination() {
    }

    public Destination(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public Destination(String destinationCode, String destination) {
        this.destinationCode = destinationCode;
        this.destination = destination;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
        hash += (destinationCode != null ? destinationCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.destinationCode == null && other.destinationCode != null) || (this.destinationCode != null && !this.destinationCode.equals(other.destinationCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Destination[ destinationCode=" + destinationCode + " ]";
    }
    
}
