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
@Table(name = "accounts")
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findByAccountsId", query = "SELECT a FROM Accounts a WHERE a.accountsId = :accountsId"),
    @NamedQuery(name = "Accounts.findByName", query = "SELECT a FROM Accounts a WHERE a.name = :name"),
    @NamedQuery(name = "Accounts.findByAddress", query = "SELECT a FROM Accounts a WHERE a.address = :address"),
    @NamedQuery(name = "Accounts.findByPhone", query = "SELECT a FROM Accounts a WHERE a.phone = :phone"),
    @NamedQuery(name = "Accounts.findByType", query = "SELECT a FROM Accounts a WHERE a.type = :type")})
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AccountsId", nullable = false, length = 20)
    private String accountsId;
    @Basic(optional = false)
    @Column(name = "Name", nullable = false, length = 45)
    private String name;
    @Basic(optional = false)
    @Column(name = "Address", nullable = false, length = 45)
    private String address;
    @Column(name = "phone", length = 45)
    private String phone;
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 8)
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts")
    private Collection<Creditors> creditorsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts")
    private Collection<Debtors> debtorsCollection;

    public Accounts() {
    }

    public Accounts(String accountsId) {
        this.accountsId = accountsId;
    }

    public Accounts(String accountsId, String name, String address, String type) {
        this.accountsId = accountsId;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(String accountsId) {
        this.accountsId = accountsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Creditors> getCreditorsCollection() {
        return creditorsCollection;
    }

    public void setCreditorsCollection(Collection<Creditors> creditorsCollection) {
        this.creditorsCollection = creditorsCollection;
    }

    public Collection<Debtors> getDebtorsCollection() {
        return debtorsCollection;
    }

    public void setDebtorsCollection(Collection<Debtors> debtorsCollection) {
        this.debtorsCollection = debtorsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountsId != null ? accountsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.accountsId == null && other.accountsId != null) || (this.accountsId != null && !this.accountsId.equals(other.accountsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xmanager.entity.Accounts[ accountsId=" + accountsId + " ]";
    }
    
}
