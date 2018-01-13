/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.data;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Robert
 */
@Entity
@Table(name = "material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByIdShape", query = "SELECT m FROM Material m WHERE m.idShape = :idShape"),
    @NamedQuery(name = "Material.findByName", query = "SELECT m FROM Material m WHERE m.name = :name"),
    @NamedQuery(name = "Material.findByEuroPerKg", query = "SELECT m FROM Material m WHERE m.euroPerKg = :euroPerKg")})
public class Material implements Serializable {
    @OneToMany(mappedBy = "materialId")
    private List<Design> designList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idShape")
    private Integer idShape;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "euroPerKg")
    private BigDecimal euroPerKg;
    @JoinColumn(name = "idMaterialDetails", referencedColumnName = "idMaterialDetails")
    @ManyToOne
    private Materialdetails idMaterialDetails;
    @JoinColumn(name = "idCalculationForm", referencedColumnName = "idcalculationform")
    @ManyToOne(optional = false)
    private Calculationform idCalculationForm;

    public Material() {
    }

    public Material(Integer idShape) {
        this.idShape = idShape;
    }

    public Material(Integer idShape, String name, BigDecimal euroPerKg) {
        this.idShape = idShape;
        this.name = name;
        this.euroPerKg = euroPerKg;
    }

    public Integer getIdShape() {
        return idShape;
    }

    public void setIdShape(Integer idShape) {
        this.idShape = idShape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getEuroPerKg() {
        return euroPerKg;
    }

    public void setEuroPerKg(BigDecimal euroPerKg) {
        this.euroPerKg = euroPerKg;
    }

    public Materialdetails getIdMaterialDetails() {
        return idMaterialDetails;
    }

    public void setIdMaterialDetails(Materialdetails idMaterialDetails) {
        this.idMaterialDetails = idMaterialDetails;
    }

    public Calculationform getIdCalculationForm() {
        return idCalculationForm;
    }

    public void setIdCalculationForm(Calculationform idCalculationForm) {
        this.idCalculationForm = idCalculationForm;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idShape != null ? idShape.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.idShape == null && other.idShape != null) || (this.idShape != null && !this.idShape.equals(other.idShape))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stefan.data.Material[ idShape=" + idShape + " ]";
    }

    @XmlTransient
    public List<Design> getDesignList() {
        return designList;
    }

    public void setDesignList(List<Design> designList) {
        this.designList = designList;
    }
}
