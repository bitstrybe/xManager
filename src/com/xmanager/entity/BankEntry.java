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
@Table(name = "bank_entry")
@NamedQueries({
    @NamedQuery(name = "BankEntry.findAll", query = "SELECT b FROM BankEntry b"),
    @NamedQuery(name = "BankEntry.findByEntry", query = "SELECT b FROM BankEntry b WHERE b.entry = :entry"),
    @NamedQuery(name = "BankEntry.findByAmount", query = "SELECT b FROM BankEntry b WHERE b.amount = :amount"),
    @NamedQuery(name = "BankEntry.findByType", query = "SELECT b FROM BankEntry b WHERE b.type = :type"),
    @NamedQuery(name = "BankEntry.findByDate", query = "SELECT b FROM BankEntry b WHERE b.date = :date"),
    @NamedQuery(name = "BankEntry.findByDetails", query = "SELECT b FROM BankEntry b WHERE b.details = :details")})
public class BankEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "entry", nullable = false)
    private Integer entry;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private float amount;
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 2)
    private String type;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "details", length = 145)
    private String details;
    @JoinColumn(name = "bank", referencedColumnName = "bankid", nullable = false)
    @ManyToOne(optional = false)
    private Bank bank;

    public BankEntry() {
    }

    public BankEntry(Integer entry) {
        this.entry = entry;
    }

    public BankEntry(Integer entry, float amount, String type, Date date) {
        this.entry = entry;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Integer getEntry() {
        return entry;
    }

    public void setEntry(Integer entry) {
        this.entry = entry;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entry != null ? entry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankEntry)) {
            return false;
        }
        BankEntry other = (BankEntry) object;
        if ((this.entry == null && other.entry != null) || (this.entry != null && !this.entry.equals(other.entry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.BankEntry[ entry=" + entry + " ]";
    }
    
}
