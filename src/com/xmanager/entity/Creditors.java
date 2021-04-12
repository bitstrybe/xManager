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
@Table(name = "creditors")
@NamedQueries({
    @NamedQuery(name = "Creditors.findAll", query = "SELECT c FROM Creditors c"),
    @NamedQuery(name = "Creditors.findByCreditorsId", query = "SELECT c FROM Creditors c WHERE c.creditorsId = :creditorsId"),
    @NamedQuery(name = "Creditors.findByAmount", query = "SELECT c FROM Creditors c WHERE c.amount = :amount"),
    @NamedQuery(name = "Creditors.findByDetails", query = "SELECT c FROM Creditors c WHERE c.details = :details"),
    @NamedQuery(name = "Creditors.findByType", query = "SELECT c FROM Creditors c WHERE c.type = :type"),
    @NamedQuery(name = "Creditors.findByDate", query = "SELECT c FROM Creditors c WHERE c.date = :date")})
public class Creditors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "creditors_id", nullable = false)
    private Integer creditorsId;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private float amount;
    @Column(name = "details", length = 250)
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

    public Creditors() {
    }

    public Creditors(Integer creditorsId) {
        this.creditorsId = creditorsId;
    }

    public Creditors(Integer creditorsId, float amount, String type, Date date) {
        this.creditorsId = creditorsId;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Integer getCreditorsId() {
        return creditorsId;
    }

    public void setCreditorsId(Integer creditorsId) {
        this.creditorsId = creditorsId;
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
        hash += (creditorsId != null ? creditorsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creditors)) {
            return false;
        }
        Creditors other = (Creditors) object;
        if ((this.creditorsId == null && other.creditorsId != null) || (this.creditorsId != null && !this.creditorsId.equals(other.creditorsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Creditors[ creditorsId=" + creditorsId + " ]";
    }
    
}
