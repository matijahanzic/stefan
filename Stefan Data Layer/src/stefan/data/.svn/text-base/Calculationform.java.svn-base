/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.data;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robert
 */
@Entity
@Table(name = "calculationform")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calculationform.findAll", query = "SELECT c FROM Calculationform c"),
    @NamedQuery(name = "Calculationform.findByIdcalculationform", query = "SELECT c FROM Calculationform c WHERE c.idcalculationform = :idcalculationform"),
    @NamedQuery(name = "Calculationform.findByCalculationformname", query = "SELECT c FROM Calculationform c WHERE c.calculationformname = :calculationformname")})
public class Calculationform implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcalculationform")
    private Integer idcalculationform;
    @Basic(optional = false)
    @Column(name = "calculationformname")
    private String calculationformname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCalculationForm")
    private List<Material> materialList;

    public Calculationform() {
    }

    public Calculationform(Integer idcalculationform) {
        this.idcalculationform = idcalculationform;
    }

    public Calculationform(Integer idcalculationform, String calculationformname) {
        this.idcalculationform = idcalculationform;
        this.calculationformname = calculationformname;
    }

    public Integer getIdcalculationform() {
        return idcalculationform;
    }

    public void setIdcalculationform(Integer idcalculationform) {
        this.idcalculationform = idcalculationform;
    }

    public String getCalculationformname() {
        return calculationformname;
    }

    public void setCalculationformname(String calculationformname) {
        this.calculationformname = calculationformname;
    }

    @XmlTransient
    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalculationform != null ? idcalculationform.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calculationform)) {
            return false;
        }
        Calculationform other = (Calculationform) object;
        if ((this.idcalculationform == null && other.idcalculationform != null) || (this.idcalculationform != null && !this.idcalculationform.equals(other.idcalculationform))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stefan.data.Calculationform[ idcalculationform=" + idcalculationform + " ]";
    }
    
}
