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
@Table(name = "expenditures")
@NamedQueries({
    @NamedQuery(name = "Expenditures.findAll", query = "SELECT e FROM Expenditures e"),
    @NamedQuery(name = "Expenditures.findByExpenditures", query = "SELECT e FROM Expenditures e WHERE e.expenditures = :expenditures")})
public class Expenditures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "expenditures", nullable = false, length = 145)
    private String expenditures;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expenditures")
    private Collection<GeneralLedger> generalLedgerCollection;

    public Expenditures() {
    }

    public Expenditures(String expenditures) {
        this.expenditures = expenditures;
    }

    public String getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(String expenditures) {
        this.expenditures = expenditures;
    }

    public Collection<GeneralLedger> getGeneralLedgerCollection() {
        return generalLedgerCollection;
    }

    public void setGeneralLedgerCollection(Collection<GeneralLedger> generalLedgerCollection) {
        this.generalLedgerCollection = generalLedgerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expenditures != null ? expenditures.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expenditures)) {
            return false;
        }
        Expenditures other = (Expenditures) object;
        if ((this.expenditures == null && other.expenditures != null) || (this.expenditures != null && !this.expenditures.equals(other.expenditures))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Expenditures[ expenditures=" + expenditures + " ]";
    }
    
}
