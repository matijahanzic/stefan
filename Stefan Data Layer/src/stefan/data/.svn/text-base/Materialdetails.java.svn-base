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
@Table(name = "materialdetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materialdetails.findAll", query = "SELECT m FROM Materialdetails m"),
    @NamedQuery(name = "Materialdetails.findByIdMaterialDetails", query = "SELECT m FROM Materialdetails m WHERE m.idMaterialDetails = :idMaterialDetails"),
    @NamedQuery(name = "Materialdetails.findByMaterialName", query = "SELECT m FROM Materialdetails m WHERE m.materialName = :materialName"),
    @NamedQuery(name = "Materialdetails.findByDensity", query = "SELECT m FROM Materialdetails m WHERE m.density = :density"),
    @NamedQuery(name = "Materialdetails.findByNiklanje", query = "SELECT m FROM Materialdetails m WHERE m.niklanje = :niklanje"),
    @NamedQuery(name = "Materialdetails.updateDetails", query = "UPDATE Materialdetails m SET m.materialName = :materialName, m.density = :density, m.niklanje = :niklanje WHERE m.idMaterialDetails = :idMaterialDetails")
        })
public class Materialdetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMaterialDetails")
    private Integer idMaterialDetails;
    @Basic(optional = false)
    @Column(name = "materialName")
    private String materialName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "density")
    private BigDecimal density;
    @Column(name = "niklanje")
    private Boolean niklanje;
    @OneToMany(mappedBy = "idMaterialDetails")
    private List<Material> materialList;

    public Materialdetails() {
    }

    public Materialdetails(Integer idMaterialDetails) {
        this.idMaterialDetails = idMaterialDetails;
    }

    public Materialdetails(Integer idMaterialDetails, String materialName, BigDecimal density) {
        this.idMaterialDetails = idMaterialDetails;
        this.materialName = materialName;
        this.density = density;
    }

    public Integer getIdMaterialDetails() {
        return idMaterialDetails;
    }

    public void setIdMaterialDetails(Integer idMaterialDetails) {
        this.idMaterialDetails = idMaterialDetails;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public BigDecimal getDensity() {
        return density;
    }

    public void setDensity(BigDecimal density) {
        this.density = density;
    }

    public Boolean getNiklanje() {
        return niklanje;
    }

    public void setNiklanje(Boolean niklanje) {
        this.niklanje = niklanje;
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
        hash += (idMaterialDetails != null ? idMaterialDetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materialdetails)) {
            return false;
        }
        Materialdetails other = (Materialdetails) object;
        if ((this.idMaterialDetails == null && other.idMaterialDetails != null) || (this.idMaterialDetails != null && !this.idMaterialDetails.equals(other.idMaterialDetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stefan.data.Materialdetails[ idMaterialDetails=" + idMaterialDetails + " ]";
    }
    
}
