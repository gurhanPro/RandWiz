/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randwizop;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author maxamed
 */
@Entity
@Table(name = "TRANSACTIONTYPE")
@NamedQueries({
    @NamedQuery(name = "Transactiontype.findAll", query = "SELECT t FROM Transactiontype t"),
    @NamedQuery(name = "Transactiontype.findByTransactiontypeid", query = "SELECT t FROM Transactiontype t WHERE t.transactiontypeid = :transactiontypeid"),
    @NamedQuery(name = "Transactiontype.findByTransctiontypename", query = "SELECT t FROM Transactiontype t WHERE t.transctiontypename = :transctiontypename")})
public class Transactiontype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSACTIONTYPEID")
    private Integer transactiontypeid;
    @Column(name = "TRANSCTIONTYPENAME")
    private String transctiontypename;
    @OneToMany(mappedBy = "transactiontypeid")
    private Collection<Transactions> transactionsCollection;

    public Transactiontype() {
    }

    public Transactiontype(Integer transactiontypeid) {
        this.transactiontypeid = transactiontypeid;
    }

    public Integer getTransactiontypeid() {
        return transactiontypeid;
    }

    public void setTransactiontypeid(Integer transactiontypeid) {
        this.transactiontypeid = transactiontypeid;
    }

    public String getTransctiontypename() {
        return transctiontypename;
    }

    public void setTransctiontypename(String transctiontypename) {
        this.transctiontypename = transctiontypename;
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
        hash += (transactiontypeid != null ? transactiontypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactiontype)) {
            return false;
        }
        Transactiontype other = (Transactiontype) object;
        if ((this.transactiontypeid == null && other.transactiontypeid != null) || (this.transactiontypeid != null && !this.transactiontypeid.equals(other.transactiontypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "randwizop.Transactiontype[ transactiontypeid=" + transactiontypeid + " ]";
    }
    
}
