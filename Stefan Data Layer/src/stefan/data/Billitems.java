/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.data;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matija
 */
@Entity
@Table(name = "billitems")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billitems.findAll", query = "SELECT b FROM Billitems b"),
    @NamedQuery(name = "Billitems.findByIdBillItem", query = "SELECT b FROM Billitems b WHERE b.idBillItem = :idBillItem"),
    @NamedQuery(name = "Billitems.findByParts", query = "SELECT b FROM Billitems b WHERE b.parts = :parts")})
public class Billitems implements Serializable {
    @JoinColumn(name = "idOrderItem", referencedColumnName = "idOrderItems")
    @ManyToOne
    private Orderitems idOrderItem;
    @Column(name = "packageNumber")
    private String packageNumber;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBillItem")
    private Integer idBillItem;
    @Column(name = "parts")
    private Integer parts;
    @JoinColumn(name = "idBill", referencedColumnName = "idBill")
    @ManyToOne
    private Bills idBill;
     @Column(name = "niklanje")
    private Boolean niklanje;
    @Column(name = "pricePerUnit")
    private BigDecimal pricePerUnit;

    public Billitems() {
    }

    public Billitems(Integer idBillItem) {
        this.idBillItem = idBillItem;
    }

    public Integer getIdBillItem() {
        return idBillItem;
    }

    public void setIdBillItem(Integer idBillItem) {
        this.idBillItem = idBillItem;
    }

    public Integer getParts() {
        return parts;
    }

    public void setParts(Integer parts) {
        this.parts = parts;
    }
    
     public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Bills getIdBill() {
        return idBill;
    }

    public void setIdBill(Bills idBill) {
        this.idBill = idBill;
    }
    
     public Boolean getNiklanje() {
        return niklanje;
    }

    public void setNiklanje(Boolean niklanje) {
        this.niklanje = niklanje;
    }   


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBillItem != null ? idBillItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billitems)) {
            return false;
        }
        Billitems other = (Billitems) object;
        if ((this.idBillItem == null && other.idBillItem != null) || (this.idBillItem != null && !this.idBillItem.equals(other.idBillItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stefan.data.Billitems[ idBillItem=" + idBillItem + " ]";
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    public Orderitems getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(Orderitems idOrderItem) {
        this.idOrderItem = idOrderItem;
    }
    
}
