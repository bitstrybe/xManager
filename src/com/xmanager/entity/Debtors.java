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
@Table(name = "debtors")
@NamedQueries({
    @NamedQuery(name = "Debtors.findAll", query = "SELECT d FROM Debtors d"),
    @NamedQuery(name = "Debtors.findByDebtorsId", query = "SELECT d FROM Debtors d WHERE d.debtorsId = :debtorsId"),
    @NamedQuery(name = "Debtors.findByAmount", query = "SELECT d FROM Debtors d WHERE d.amount = :amount"),
    @NamedQuery(name = "Debtors.findByDetails", query = "SELECT d FROM Debtors d WHERE d.details = :details"),
    @NamedQuery(name = "Debtors.findByType", query = "SELECT d FROM Debtors d WHERE d.type = :type"),
    @NamedQuery(name = "Debtors.findByDate", query = "SELECT d FROM Debtors d WHERE d.date = :date")})
public class Debtors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "debtors_id", nullable = false)
    private Integer debtorsId;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private float amount;
    @Column(name = "details", length = 45)
    private String details;
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 2)
    private String type;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "accounts", referencedColumnName = "AccountsId", nullable = false)
    @ManyToOne(optional = false)
    private Accounts accounts;

    public Debtors() {
    }

    public Debtors(Integer debtorsId) {
        this.debtorsId = debtorsId;
    }

    public Debtors(Integer debtorsId, float amount, String type, Date date) {
        this.debtorsId = debtorsId;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Integer getDebtorsId() {
        return debtorsId;
    }

    public void setDebtorsId(Integer debtorsId) {
        this.debtorsId = debtorsId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (debtorsId != null ? debtorsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Debtors)) {
            return false;
        }
        Debtors other = (Debtors) object;
        if ((this.debtorsId == null && other.debtorsId != null) || (this.debtorsId != null && !this.debtorsId.equals(other.debtorsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Debtors[ debtorsId=" + debtorsId + " ]";
    }
    
}
