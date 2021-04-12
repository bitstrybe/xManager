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
@Table(name = "general_ledger")
@NamedQueries({
    @NamedQuery(name = "GeneralLedger.findAll", query = "SELECT g FROM GeneralLedger g"),
    @NamedQuery(name = "GeneralLedger.findByGeneralLedgerId", query = "SELECT g FROM GeneralLedger g WHERE g.generalLedgerId = :generalLedgerId"),
    @NamedQuery(name = "GeneralLedger.findByDate", query = "SELECT g FROM GeneralLedger g WHERE g.date = :date"),
    @NamedQuery(name = "GeneralLedger.findByAmount", query = "SELECT g FROM GeneralLedger g WHERE g.amount = :amount"),
    @NamedQuery(name = "GeneralLedger.findByType", query = "SELECT g FROM GeneralLedger g WHERE g.type = :type")})
public class GeneralLedger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "general_ledger_id", nullable = false)
    private Integer generalLedgerId;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private float amount;
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 2)
    private String type;
    @JoinColumn(name = "expenditures", referencedColumnName = "expenditures", nullable = false)
    @ManyToOne(optional = false)
    private Expenditures expenditures;

    public GeneralLedger() {
    }

    public GeneralLedger(Integer generalLedgerId) {
        this.generalLedgerId = generalLedgerId;
    }

    public GeneralLedger(Integer generalLedgerId, Date date, float amount, String type) {
        this.generalLedgerId = generalLedgerId;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public Integer getGeneralLedgerId() {
        return generalLedgerId;
    }

    public void setGeneralLedgerId(Integer generalLedgerId) {
        this.generalLedgerId = generalLedgerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Expenditures getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(Expenditures expenditures) {
        this.expenditures = expenditures;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (generalLedgerId != null ? generalLedgerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneralLedger)) {
            return false;
        }
        GeneralLedger other = (GeneralLedger) object;
        if ((this.generalLedgerId == null && other.generalLedgerId != null) || (this.generalLedgerId != null && !this.generalLedgerId.equals(other.generalLedgerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.GeneralLedger[ generalLedgerId=" + generalLedgerId + " ]";
    }
    
}
