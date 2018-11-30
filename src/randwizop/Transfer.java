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
@Table(name = "TRANSFER")
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t"),
    @NamedQuery(name = "Transfer.findByTransferid", query = "SELECT t FROM Transfer t WHERE t.transferid = :transferid"),
    @NamedQuery(name = "Transfer.findByTransferamount", query = "SELECT t FROM Transfer t WHERE t.transferamount = :transferamount"),
    @NamedQuery(name = "Transfer.findByTimestamps", query = "SELECT t FROM Transfer t WHERE t.timestamps = :timestamps"),
    @NamedQuery(name = "Transfer.findByComments", query = "SELECT t FROM Transfer t WHERE t.comments = :comments")})
public class Transfer implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSFERID")
    private Integer transferid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TRANSFERAMOUNT")
    private Double transferamount;
    @Column(name = "TIMESTAMPS")
    private String timestamps;
    @Column(name = "COMMENTS")
    private String comments;
    @JoinColumn(name = "CLIENTIDTO", referencedColumnName = "CLIENTID")
    @ManyToOne
    private Clients clientidto;
    @JoinColumn(name = "CLIENTIDFROM", referencedColumnName = "CLIENTID")
    @ManyToOne
    private Clients clientidfrom;

    public Transfer() {
    }

    public Transfer(Integer transferid) {
        this.transferid = transferid;
    }

    public Integer getTransferid() {
        return transferid;
    }

    public void setTransferid(Integer transferid) {
        Integer oldTransferid = this.transferid;
        this.transferid = transferid;
        changeSupport.firePropertyChange("transferid", oldTransferid, transferid);
    }

    public Double getTransferamount() {
        return transferamount;
    }

    public void setTransferamount(Double transferamount) {
        Double oldTransferamount = this.transferamount;
        this.transferamount = transferamount;
        changeSupport.firePropertyChange("transferamount", oldTransferamount, transferamount);
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

    public Clients getClientidto() {
        return clientidto;
    }

    public void setClientidto(Clients clientidto) {
        Clients oldClientidto = this.clientidto;
        this.clientidto = clientidto;
        changeSupport.firePropertyChange("clientidto", oldClientidto, clientidto);
    }

    public Clients getClientidfrom() {
        return clientidfrom;
    }

    public void setClientidfrom(Clients clientidfrom) {
        Clients oldClientidfrom = this.clientidfrom;
        this.clientidfrom = clientidfrom;
        changeSupport.firePropertyChange("clientidfrom", oldClientidfrom, clientidfrom);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transferid != null ? transferid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.transferid == null && other.transferid != null) || (this.transferid != null && !this.transferid.equals(other.transferid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "randwizop.Transfer[ transferid=" + transferid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
