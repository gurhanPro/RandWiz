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
@Table(name = "ADMIN")
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findByAdminid", query = "SELECT a FROM Admin a WHERE a.adminid = :adminid"),
    @NamedQuery(name = "Admin.findByUsername", query = "SELECT a FROM Admin a WHERE a.username = :username"),
    @NamedQuery(name = "Admin.findByPassword", query = "SELECT a FROM Admin a WHERE a.password = :password"),
    @NamedQuery(name = "Admin.findByPasswordhint", query = "SELECT a FROM Admin a WHERE a.passwordhint = :passwordhint"),
    @NamedQuery(name = "Admin.findByAdminbalance", query = "SELECT a FROM Admin a WHERE a.adminbalance = :adminbalance"),
    @NamedQuery(name = "Admin.findByTotaldeposited", query = "SELECT a FROM Admin a WHERE a.totaldeposited = :totaldeposited"),
    @NamedQuery(name = "Admin.findByTotalwithdrawn", query = "SELECT a FROM Admin a WHERE a.totalwithdrawn = :totalwithdrawn"),
    @NamedQuery(name = "Admin.findByMasterusername", query = "SELECT a FROM Admin a WHERE a.masterusername = :masterusername"),
    @NamedQuery(name = "Admin.findByMasterpassword", query = "SELECT a FROM Admin a WHERE a.masterpassword = :masterpassword"),
    @NamedQuery(name = "Admin.findByDatecreated", query = "SELECT a FROM Admin a WHERE a.datecreated = :datecreated")})
public class Admin implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ADMINID")
    private Integer adminid;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PASSWORDHINT")
    private String passwordhint;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ADMINBALANCE")
    private Double adminbalance;
    @Column(name = "TOTALDEPOSITED")
    private Double totaldeposited;
    @Column(name = "TOTALWITHDRAWN")
    private Double totalwithdrawn;
    @Column(name = "MASTERUSERNAME")
    private String masterusername;
    @Column(name = "MASTERPASSWORD")
    private String masterpassword;
    @Column(name = "DATECREATED")
    private String datecreated;
    @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID")
    @ManyToOne
    private Company companyid;
    @OneToMany(mappedBy = "handlerid")
    private Collection<Transactions> transactionsCollection;

    public Admin() {
    }

    public Admin(Integer adminid) {
        this.adminid = adminid;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        Integer oldAdminid = this.adminid;
        this.adminid = adminid;
        changeSupport.firePropertyChange("adminid", oldAdminid, adminid);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        changeSupport.firePropertyChange("username", oldUsername, username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    public String getPasswordhint() {
        return passwordhint;
    }

    public void setPasswordhint(String passwordhint) {
        String oldPasswordhint = this.passwordhint;
        this.passwordhint = passwordhint;
        changeSupport.firePropertyChange("passwordhint", oldPasswordhint, passwordhint);
    }

    public Double getAdminbalance() {
        return adminbalance;
    }

    public void setAdminbalance(Double adminbalance) {
        Double oldAdminbalance = this.adminbalance;
        this.adminbalance = adminbalance;
        changeSupport.firePropertyChange("adminbalance", oldAdminbalance, adminbalance);
    }

    public Double getTotaldeposited() {
        return totaldeposited;
    }

    public void setTotaldeposited(Double totaldeposited) {
        Double oldTotaldeposited = this.totaldeposited;
        this.totaldeposited = totaldeposited;
        changeSupport.firePropertyChange("totaldeposited", oldTotaldeposited, totaldeposited);
    }

    public Double getTotalwithdrawn() {
        return totalwithdrawn;
    }

    public void setTotalwithdrawn(Double totalwithdrawn) {
        Double oldTotalwithdrawn = this.totalwithdrawn;
        this.totalwithdrawn = totalwithdrawn;
        changeSupport.firePropertyChange("totalwithdrawn", oldTotalwithdrawn, totalwithdrawn);
    }

    public String getMasterusername() {
        return masterusername;
    }

    public void setMasterusername(String masterusername) {
        String oldMasterusername = this.masterusername;
        this.masterusername = masterusername;
        changeSupport.firePropertyChange("masterusername", oldMasterusername, masterusername);
    }

    public String getMasterpassword() {
        return masterpassword;
    }

    public void setMasterpassword(String masterpassword) {
        String oldMasterpassword = this.masterpassword;
        this.masterpassword = masterpassword;
        changeSupport.firePropertyChange("masterpassword", oldMasterpassword, masterpassword);
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        String oldDatecreated = this.datecreated;
        this.datecreated = datecreated;
        changeSupport.firePropertyChange("datecreated", oldDatecreated, datecreated);
    }

    public Company getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Company companyid) {
        Company oldCompanyid = this.companyid;
        this.companyid = companyid;
        changeSupport.firePropertyChange("companyid", oldCompanyid, companyid);
    }

    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminid != null ? adminid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.adminid == null && other.adminid != null) || (this.adminid != null && !this.adminid.equals(other.adminid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "randwizop.Admin[ adminid=" + adminid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
