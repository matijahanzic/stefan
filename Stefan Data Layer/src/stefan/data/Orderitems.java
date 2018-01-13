/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.data;

import java.io.Serializable;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matija
 */
@Entity
@Table(name = "orderitems")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderitems.findAll", query = "SELECT o FROM Orderitems o"),
    @NamedQuery(name = "Orderitems.findByOrderItemId", query = "SELECT o FROM Orderitems o WHERE o.idOrderItems = :idOrderItems"),
    @NamedQuery(name = "Orderitems.findByOrderId", query = "SELECT o FROM Orderitems o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "Orderitems.findByPosition", query = "SELECT o FROM Orderitems o WHERE o.position = :position"),
    @NamedQuery(name = "Orderitems.findByQuantityOrdered", query = "SELECT o FROM Orderitems o WHERE o.quantityOrdered = :quantityOrdered"),
    @NamedQuery(name = "Orderitems.findUndelivered", query = "SELECT o FROM Orderitems o WHERE o.quantityOrdered > o.quantityDelivered"),
    @NamedQuery(name = "Orderitems.findByQuantityDelivered", query = "SELECT o FROM Orderitems o WHERE o.quantityDelivered = :quantityDelivered")})
public class Orderitems implements Serializable {
    @OneToMany(mappedBy = "idOrderItem")
    private List<Billitems> billitemsList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrderItems")
    private Integer idOrderItems;
    @Column(name = "position")
    private String position;
    @Column(name = "quantityOrdered")
    private Integer quantityOrdered;
    @Column(name = "quantityDelivered")
    private Integer quantityDelivered;
    @JoinColumn(name = "idOrder", referencedColumnName = "idOrder")
    @ManyToOne(optional = false)
    private Orders idOrder;
    @JoinColumn(name = "idDesign", referencedColumnName = "idDesign")
    @ManyToOne(optional = false)
    private Design idDesign;

    public Orderitems() {
    }

    public Orderitems(Integer idOrderItems) {
        this.idOrderItems = idOrderItems;
    }

    public Integer getIdOrderItems() {
        return idOrderItems;
    }

    public void setIdOrderItems(Integer idOrderItems) {
        this.idOrderItems = idOrderItems;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Integer getQuantityDelivered() {
        return quantityDelivered;
    }

    public void setQuantityDelivered(Integer quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    public Orders getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Orders idOrder) {
        this.idOrder = idOrder;
    }

    public Design getIdDesign() {
        return idDesign;
    }

    public void setIdDesign(Design idDesign) {
        this.idDesign = idDesign;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrderItems != null ? idOrderItems.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderitems)) {
            return false;
        }
        Orderitems other = (Orderitems) object;
        if ((this.idOrderItems == null && other.idOrderItems != null) || (this.idOrderItems != null && !this.idOrderItems.equals(other.idOrderItems))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stefan.data.Orderitems[ idOrderItems=" + idOrderItems + " ]";
    }

    @XmlTransient
    public List<Billitems> getBillitemsList() {
        return billitemsList;
    }

    public void setBillitemsList(List<Billitems> billitemsList) {
        this.billitemsList = billitemsList;
    }
    
}
