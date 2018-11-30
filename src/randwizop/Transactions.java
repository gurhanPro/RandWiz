/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randwizop;

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

/**
 *
 * @author maxamed
 */
@Entity
@Table(name = "TRANSACTIONS")
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByTransactionid", query = "SELECT t FROM Transactions t WHERE t.transactionid = :transactionid"),
    @NamedQuery(name = "Transactions.findByTimestamps", query = "SELECT t FROM Transactions t WHERE t.timestamps = :timestamps")})
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSACTIONID")
    private Integer transactionid;
    @Column(name = "TIMESTAMPS")
    private String timestamps;
    @JoinColumn(name = "HANDLERID", referencedColumnName = "ADMINID")
    @ManyToOne
    private Admin handlerid;
    @JoinColumn(name = "CLIENTID", referencedColumnName = "CLIENTID")
    @ManyToOne
    private Clients clientid;
    @JoinColumn(name = "TRANSACTIONTYPEID", referencedColumnName = "TRANSACTIONTYPEID")
    @ManyToOne
    private Transactiontype transactiontypeid;

    public Transactions() {
    }

    public Transactions(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public Integer getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public String getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(String timestamps) {
        this.timestamps = timestamps;
    }

    public Admin getHandlerid() {
        return handlerid;
    }

    public void setHandlerid(Admin handlerid) {
        this.handlerid = handlerid;
    }

    public Clients getClientid() {
        return clientid;
    }

    public void setClientid(Clients clientid) {
        this.clientid = clientid;
    }

    public Transactiontype getTransactiontypeid() {
        return transactiontypeid;
    }

    public void setTransactiontypeid(Transactiontype transactiontypeid) {
        this.transactiontypeid = transactiontypeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionid != null ? transactionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionid == null && other.transactionid != null) || (this.transactionid != null && !this.transactionid.equals(other.transactionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "randwizop.Transactions[ transactionid=" + transactionid + " ]";
    }
    
}
