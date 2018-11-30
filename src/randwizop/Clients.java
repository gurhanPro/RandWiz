/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randwizop;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author maxamed
 */
@Entity
@Table(name = "CLIENTS")
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT c FROM Clients c"),
    @NamedQuery(name = "Clients.findByClientid", query = "SELECT c FROM Clients c WHERE c.clientid = :clientid"),
    @NamedQuery(name = "Clients.findByFullname", query = "SELECT c FROM Clients c WHERE c.fullname = :fullname"),
    @NamedQuery(name = "Clients.findByStreet", query = "SELECT c FROM Clients c WHERE c.street = :street"),
    @NamedQuery(name = "Clients.findBySuburb", query = "SELECT c FROM Clients c WHERE c.suburb = :suburb"),
    @NamedQuery(name = "Clients.findByCity", query = "SELECT c FROM Clients c WHERE c.city = :city"),
    @NamedQuery(name = "Clients.findByCountry", query = "SELECT c FROM Clients c WHERE c.country = :country"),
    @NamedQuery(name = "Clients.findByPhone", query = "SELECT c FROM Clients c WHERE c.phone = :phone"),
    @NamedQuery(name = "Clients.findByDatecreated", query = "SELECT c FROM Clients c WHERE c.datecreated = :datecreated"),
    @NamedQuery(name = "Clients.findByClientbalance", query = "SELECT c FROM Clients c WHERE c.clientbalance = :clientbalance")})
public class Clients implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLIENTID")
    private Integer clientid;
    @Column(name = "FULLNAME")
    private String fullname;
    @Column(name = "STREET")
    private String street;
    @Column(name = "SUBURB")
    private String suburb;
    @Column(name = "CITY")
    private String city;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "DATECREATED")
    private String datecreated;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CLIENTBALANCE")
    private Double clientbalance;
    @OneToMany(mappedBy = "clientid")
    private Collection<Withdraw> withdrawCollection;
    @OneToMany(mappedBy = "clientidto")
    private Collection<Transfer> transferCollection;
    @OneToMany(mappedBy = "clientidfrom")
    private Collection<Transfer> transferCollection1;
    @OneToMany(mappedBy = "clientid")
    private Collection<Deposit> depositCollection;
    @OneToMany(mappedBy = "clientid")
    private Collection<Transactions> transactionsCollection;
    @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID")
    @ManyToOne
    private Company companyid;

    public Clients() {
    }

    public Clients(Integer clientid) {
        this.clientid = clientid;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        Integer oldClientid = this.clientid;
        this.clientid = clientid;
        changeSupport.firePropertyChange("clientid", oldClientid, clientid);
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        String oldFullname = this.fullname;
        this.fullname = fullname;
        changeSupport.firePropertyChange("fullname", oldFullname, fullname);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        String oldStreet = this.street;
        this.street = street;
        changeSupport.firePropertyChange("street", oldStreet, street);
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        String oldSuburb = this.suburb;
        this.suburb = suburb;
        changeSupport.firePropertyChange("suburb", oldSuburb, suburb);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        String oldCity = this.city;
        this.city = city;
        changeSupport.firePropertyChange("city", oldCity, city);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        String oldCountry = this.country;
        this.country = country;
        changeSupport.firePropertyChange("country", oldCountry, country);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        String oldPhone = this.phone;
        this.phone = phone;
        changeSupport.firePropertyChange("phone", oldPhone, phone);
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        String oldDatecreated = this.datecreated;
        this.datecreated = datecreated;
        changeSupport.firePropertyChange("datecreated", oldDatecreated, datecreated);
    }

    public Double getClientbalance() {
        return clientbalance;
    }

    public void setClientbalance(Double clientbalance) {
        Double oldClientbalance = this.clientbalance;
        this.clientbalance = clientbalance;
        changeSupport.firePropertyChange("clientbalance", oldClientbalance, clientbalance);
    }

    public Collection<Withdraw> getWithdrawCollection() {
        return withdrawCollection;
    }

    public void setWithdrawCollection(Collection<Withdraw> withdrawCollection) {
        this.withdrawCollection = withdrawCollection;
    }

    public Collection<Transfer> getTransferCollection() {
        return transferCollection;
    }

    public void setTransferCollection(Collection<Transfer> transferCollection) {
        this.transferCollection = transferCollection;
    }

    public Collection<Transfer> getTransferCollection1() {
        return transferCollection1;
    }

    public void setTransferCollection1(Collection<Transfer> transferCollection1) {
        this.transferCollection1 = transferCollection1;
    }

    public Collection<Deposit> getDepositCollection() {
        return depositCollection;
    }

    public void setDepositCollection(Collection<Deposit> depositCollection) {
        this.depositCollection = depositCollection;
    }

    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    public Company getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Company companyid) {
        Company oldCompanyid = this.companyid;
        this.companyid = companyid;
        changeSupport.firePropertyChange("companyid", oldCompanyid, companyid);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientid != null ? clientid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clients)) {
            return false;
        }
        Clients other = (Clients) object;
        if ((this.clientid == null && other.clientid != null) || (this.clientid != null && !this.clientid.equals(other.clientid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+getFullname()+"  >>>>  R"+getClientbalance();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
