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
@Table(name = "WITHDRAW")
@NamedQueries({
    @NamedQuery(name = "Withdraw.findAll", query = "SELECT w FROM Withdraw w"),
    @NamedQuery(name = "Withdraw.findByWithdrawid", query = "SELECT w FROM Withdraw w WHERE w.withdrawid = :withdrawid"),
    @NamedQuery(name = "Withdraw.findByWithdrawamount", query = "SELECT w FROM Withdraw w WHERE w.withdrawamount = :withdrawamount"),
    @NamedQuery(name = "Withdraw.findByTimestamps", query = "SELECT w FROM Withdraw w WHERE w.timestamps = :timestamps"),
    @NamedQuery(name = "Withdraw.findByComments", query = "SELECT w FROM Withdraw w WHERE w.comments = :comments")})
public class Withdraw implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WITHDRAWID")
    private Integer withdrawid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WITHDRAWAMOUNT")
    private Double withdrawamount;
    @Column(name = "TIMESTAMPS")
    private String timestamps;
    @Column(name = "COMMENTS")
    private String comments;
    @JoinColumn(name = "CLIENTID", referencedColumnName = "CLIENTID")
    @ManyToOne
    private Clients clientid;

    public Withdraw() {
    }

    public Withdraw(Integer withdrawid) {
        this.withdrawid = withdrawid;
    }

    public Integer getWithdrawid() {
        return withdrawid;
    }

    public void setWithdrawid(Integer withdrawid) {
        Integer oldWithdrawid = this.withdrawid;
        this.withdrawid = withdrawid;
        changeSupport.firePropertyChange("withdrawid", oldWithdrawid, withdrawid);
    }

    public Double getWithdrawamount() {
        return withdrawamount;
    }

    public void setWithdrawamount(Double withdrawamount) {
        Double oldWithdrawamount = this.withdrawamount;
        this.withdrawamount = withdrawamount;
        changeSupport.firePropertyChange("withdrawamount", oldWithdrawamount, withdrawamount);
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
        hash += (withdrawid != null ? withdrawid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Withdraw)) {
            return false;
        }
        Withdraw other = (Withdraw) object;
        if ((this.withdrawid == null && other.withdrawid != null) || (this.withdrawid != null && !this.withdrawid.equals(other.withdrawid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "randwizop.Withdraw[ withdrawid=" + withdrawid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
