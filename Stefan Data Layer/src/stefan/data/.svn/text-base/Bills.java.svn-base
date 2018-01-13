/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matija
 */
@Entity
@Table(name = "bills")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bills.findAll", query = "SELECT b FROM Bills b"),
    @NamedQuery(name = "Bills.findByIdBill", query = "SELECT b FROM Bills b WHERE b.idBill = :idBill"),
    @NamedQuery(name = "Bills.findByDate", query = "SELECT b FROM Bills b WHERE b.date = :date"),
    @NamedQuery(name = "Bills.findByBillNumber", query = "SELECT b FROM Bills b WHERE b.billNumber = :billNumber")})
public class Bills implements Serializable {
    @Column(name =     "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBill")
    private Integer idBill;
    @Column(name = "billNumber")
    private String billNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBill")
    private List<Billitems> billitemsList;

    public Bills() {
    }

    public Bills(Integer idBill) {
        this.idBill = idBill;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    @XmlTransient
    public List<Billitems> getBillitemsList() {
        return billitemsList;
    }

    public void setBillitemsList(List<Billitems> billitemsList) {
        this.billitemsList = billitemsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBill != null ? idBill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bills)) {
            return false;
        }
        Bills other = (Bills) object;
        if ((this.idBill == null && other.idBill != null) || (this.idBill != null && !this.idBill.equals(other.idBill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stefan.data.Bills[ idBill=" + idBill + " ]";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
