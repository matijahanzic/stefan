/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.data;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author robert
 */
@Entity
@Table(name = "businesspartner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Businesspartner.findAll", query = "SELECT b FROM Businesspartner b"),
    @NamedQuery(name = "Businesspartner.findById", query = "SELECT b FROM Businesspartner b WHERE b.id = :id"),
    @NamedQuery(name = "Businesspartner.findByName", query = "SELECT b FROM Businesspartner b WHERE b.name = :name"),
    @NamedQuery(name = "Businesspartner.findByDisplayName", query = "SELECT b FROM Businesspartner b WHERE b.displayName = :displayName"),
    @NamedQuery(name = "Businesspartner.findByPrintInd", query = "SELECT b FROM Businesspartner b WHERE b.printInd = :printInd"),
    @NamedQuery(name = "Businesspartner.findByPrintRow1", query = "SELECT b FROM Businesspartner b WHERE b.printRow1 = :printRow1"),
    @NamedQuery(name = "Businesspartner.findByPrintRow2", query = "SELECT b FROM Businesspartner b WHERE b.printRow2 = :printRow2"),
    @NamedQuery(name = "Businesspartner.findByPrintRow3", query = "SELECT b FROM Businesspartner b WHERE b.printRow3 = :printRow3")})
public class Businesspartner implements Serializable {
    @Basic(optional = false)
    @Column(name = "requireShippingDate")
    private boolean requireShippingDate;
    @OneToMany(mappedBy = "businessPartnerId")
    private Collection<Orders> ordersCollection;
    @Column(name = "city")
    private String city;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "displayName")
    private String displayName;
    @Basic(optional = false)
    @Column(name = "printInd")
    private boolean printInd;
    @Basic(optional = false)
    @Column(name = "printRow1")
    private String printRow1;
    @Column(name = "printRow2")
    private String printRow2;
    @Column(name = "printRow3")
    private String printRow3;
    @OneToMany(mappedBy = "businessPartnerId")
    private Collection<Bills> billsCollection;

    public Businesspartner() {
    }

    public Businesspartner(Integer id) {
        this.id = id;
    }

    public Businesspartner(Integer id, String name, String displayName, boolean printInd, String printRow1) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.printInd = printInd;
        this.printRow1 = printRow1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean getPrintInd() {
        return printInd;
    }

    public void setPrintInd(boolean printInd) {
        this.printInd = printInd;
    }

    public String getPrintRow1() {
        return printRow1;
    }

    public void setPrintRow1(String printRow1) {
        this.printRow1 = printRow1;
    }

    public String getPrintRow2() {
        return printRow2;
    }

    public void setPrintRow2(String printRow2) {
        this.printRow2 = printRow2;
    }

    public String getPrintRow3() {
        return printRow3;
    }

    public void setPrintRow3(String printRow3) {
        this.printRow3 = printRow3;
    }

    @XmlTransient
    public Collection<Bills> getBillsCollection() {
        return billsCollection;
    }

    public void setBillsCollection(Collection<Bills> billsCollection) {
        this.billsCollection = billsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businesspartner)) {
            return false;
        }
        Businesspartner other = (Businesspartner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stefan.data.Businesspartner[ id=" + id + " ]";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    public boolean getRequireShippingDate() {
        return requireShippingDate;
    }

    public void setRequireShippingDate(boolean requireShippingDate) {
        this.requireShippingDate = requireShippingDate;
    }
    
}
