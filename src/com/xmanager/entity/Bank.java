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
@Table(name = "bank")
@NamedQueries({
    @NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b"),
    @NamedQuery(name = "Bank.findByBankid", query = "SELECT b FROM Bank b WHERE b.bankid = :bankid"),
    @NamedQuery(name = "Bank.findByBankName", query = "SELECT b FROM Bank b WHERE b.bankName = :bankName"),
    @NamedQuery(name = "Bank.findByLocation", query = "SELECT b FROM Bank b WHERE b.location = :location")})
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "bankid", nullable = false, length = 20)
    private String bankid;
    @Column(name = "bank_name", length = 100)
    private String bankName;
    @Column(name = "location", length = 45)
    private String location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
    private Collection<BankEntry> bankEntryCollection;

    public Bank() {
    }

    public Bank(String bankid) {
        this.bankid = bankid;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Collection<BankEntry> getBankEntryCollection() {
        return bankEntryCollection;
    }

    public void setBankEntryCollection(Collection<BankEntry> bankEntryCollection) {
        this.bankEntryCollection = bankEntryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankid != null ? bankid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bank)) {
            return false;
        }
        Bank other = (Bank) object;
        if ((this.bankid == null && other.bankid != null) || (this.bankid != null && !this.bankid.equals(other.bankid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Bank[ bankid=" + bankid + " ]";
    }
    
}
