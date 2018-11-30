/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randwizop;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 *
 * @author maxamed
 */
@Entity
@Table(name = "DEPOSIT")
@NamedQueries({
    @NamedQuery(name = "Deposit.findAll", query = "SELECT d FROM Deposit d"),
    @NamedQuery(name = "Deposit.findByDepositid", query = "SELECT d FROM Deposit d WHERE d.depositid = :depositid"),
    @NamedQuery(name = "Deposit.findByDepositamount", query = "SELECT d FROM Deposit d WHERE d.depositamount = :depositamount"),
    @NamedQuery(name = "Deposit.findByTimestamps", query = "SELECT d FROM Deposit d WHERE d.timestamps = :timestamps"),
    @NamedQuery(name = "Deposit.findByComments", query = "SELECT d FROM Deposit d WHERE d.comments = :comments")})
public class Deposit implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEPOSITID")
    private Integer depositid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEPOSITAMOUNT")
    private Double depositamount;
    @Column(name = "TIMESTAMPS")
    private String timestamps;
    @Column(name = "COMMENTS")
    private String comments;
    @JoinColumn(name = "CLIENTID", referencedColumnName = "CLIENTID")
    @ManyToOne
    private Clients clientid;

    public Deposit() {
    }

    public Deposit(Integer depositid) {
        this.depositid = depositid;
    }

    public Integer getDepositid() {
        return depositid;
    }

    public void setDepositid(Integer depositid) {
        Integer oldDepositid = this.depositid;
        this.depositid = depositid;
        changeSupport.firePropertyChange("depositid", oldDepositid, depositid);
    }

    public Double getDepositamount() {
        return depositamount;
    }

    public void setDepositamount(Double depositamount) {
        Double oldDepositamount = this.depositamount;
        this.depositamount = depositamount;
        changeSupport.firePropertyChange("depositamount", oldDepositamount, depositamount);
    }

    public String getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(String timestamps) {
        String oldTimestamps = this.timestamps;
        this.timestamps = timestamps;
        changeSupport.firePropertyChange("timestamps", oldTimestamps, timestamps);
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        String oldComments = this.comments;
        this.comments = comments;
        changeSupport.firePropertyChange("comments", oldComments, comments);
    }

    public Clients getClientid() {
        return clientid;
    }

    public void setClientid(Clients clientid) {
        Clients oldClientid = this.clientid;
        this.clientid = clientid;
        changeSupport.firePropertyChange("clientid", oldClientid, clientid);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depositid != null ? depositid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deposit)) {
            return false;
        }
        Deposit other = (Deposit) object;
        if ((this.depositid == null && other.depositid != null) || (this.depositid != null && !this.depositid.equals(other.depositid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "randwizop.Deposit[ depositid=" + depositid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
