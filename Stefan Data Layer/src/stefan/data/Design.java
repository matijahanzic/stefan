/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matija
 */
@Entity
@Table(name = "design")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Design.findAll", query = "SELECT d FROM Design d WHERE d.isActive = 1 ORDER BY d.designNumber"),
    @NamedQuery(name = "Design.findByIdDesign", query = "SELECT d FROM Design d WHERE d.idDesign = :idDesign"),
    @NamedQuery(name = "Design.findByDesignNumber", query = "SELECT d FROM Design d WHERE d.designNumber = :designNumber and d.isActive = 1 ORDER BY d.designNumber"),
    @NamedQuery(name = "Design.findByDesignIdentity", query = "SELECT d FROM Design d WHERE d.designIdentity = :designIdentity and d.isActive = 1 ORDER BY d.designNumber"),
    @NamedQuery(name = "Design.findByClassMark", query = "SELECT d FROM Design d WHERE d.classMark = :classMark and d.isActive = 1 ORDER BY d.designNumber"),
    @NamedQuery(name = "Design.findByName", query = "SELECT d FROM Design d WHERE d.name = :name"),
    @NamedQuery(name = "Design.findByDate", query = "SELECT d FROM Design d WHERE d.date = :date"),   
    @NamedQuery(name = "Design.findByK", query = "SELECT d FROM Design d WHERE d.k = :k"),
    @NamedQuery(name = "Design.findByK1", query = "SELECT d FROM Design d WHERE d.k1 = :k1"),
    @NamedQuery(name = "Design.findByK2", query = "SELECT d FROM Design d WHERE d.k2 = :k2"),
    @NamedQuery(name = "Design.findByK3", query = "SELECT d FROM Design d WHERE d.k3 = :k3"),
    @NamedQuery(name = "Design.findByK4", query = "SELECT d FROM Design d WHERE d.k4 = :k4"),
    @NamedQuery(name = "Design.findByK5", query = "SELECT d FROM Design d WHERE d.k5 = :k5"),
    @NamedQuery(name = "Design.findByK6", query = "SELECT d FROM Design d WHERE d.k6 = :k6"),
    @NamedQuery(name = "Design.findByK7", query = "SELECT d FROM Design d WHERE d.k7 = :k7"),
    @NamedQuery(name = "Design.findByK8", query = "SELECT d FROM Design d WHERE d.k8 = :k8"),
    @NamedQuery(name = "Design.findByK9", query = "SELECT d FROM Design d WHERE d.k9 = :k9"),
    @NamedQuery(name = "Design.findByK10", query = "SELECT d FROM Design d WHERE d.k10 = :k10"),
    @NamedQuery(name = "Design.findByK11", query = "SELECT d FROM Design d WHERE d.k11 = :k11"),
    @NamedQuery(name = "Design.findByK12", query = "SELECT d FROM Design d WHERE d.k12 = :k12"),
    @NamedQuery(name = "Design.findByK13", query = "SELECT d FROM Design d WHERE d.k13 = :k13"),
    @NamedQuery(name = "Design.findByK14", query = "SELECT d FROM Design d WHERE d.k14 = :k14"),
    @NamedQuery(name = "Design.findByK15", query = "SELECT d FROM Design d WHERE d.k15 = :k15"),
    @NamedQuery(name = "Design.findByNumber", query = "SELECT d FROM Design d WHERE d.isActive = 1 and  d.designNumber LIKE :designNumber ORDER BY d.designNumber" ),
    @NamedQuery(name = "Design.findByFilters", query = "SELECT d FROM Design d WHERE d.isActive = 1 and d.designIdentity LIKE :designIdentity and d.classMark LIKE :classMark and d.designNumber LIKE :designNumber ORDER BY d.designNumber" )})
public class Design implements Serializable {
    @Column(name =     "date")
    @Temporal(TemporalType.DATE)
    private Date date;
// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
        @Column(name = "1k")
    private BigDecimal k;
    @Column(name = "2k")
    private BigDecimal k1;
    @Column(name = "3k")
    private BigDecimal k2;
    @Column(name = "4k")
    private BigDecimal k3;
    @Column(name = "5k")
    private BigDecimal k4;
    @Column(name = "6k")
    private BigDecimal k5;
    @Column(name = "10k")
    private BigDecimal k6;
    @Column(name = "15k")
    private BigDecimal k7;
    @Column(name = "20k")
    private BigDecimal k8;
    @Column(name = "30k")
    private BigDecimal k9;
    @Column(name = "40k")
    private BigDecimal k10;
    @Column(name = "50k")
    private BigDecimal k11;
    @Column(name = "100k")
    private BigDecimal k12;
    @Column(name = "200k")
    private BigDecimal k13;
    @Column(name = "500k")
    private BigDecimal k14;
    @Column(name = "1000k")
    private BigDecimal k15;
    @Column(name =     "dateModified")
    @Temporal(TemporalType.DATE)
    private Date dateModified;
    @Column(name = "isActive")
    private Boolean isActive;
    @Column(name = "niklanje")
    private Boolean niklanje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDesign")
    private List<Orderitems> orderitemsList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDesign")
    private Integer idDesign;
    @Basic(optional = false)
    @Column(name = "designNumber")
    private String designNumber;
    @Column(name = "designIdentity")
    private String designIdentity;
    @Column(name = "classMark")
    private String classMark;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "materialId", referencedColumnName = "idShape")
    @ManyToOne    
    private Material materialId;

    public Design() {
    }

    public Design(Integer idDesign) {
        this.idDesign = idDesign;
    }

    public Design(Integer idDesign, String designNumber) {
        this.idDesign = idDesign;
        this.designNumber = designNumber;
    }

    public Integer getIdDesign() {
        return idDesign;
    }

    public void setIdDesign(Integer idDesign) {
        this.idDesign = idDesign;
    }

    public String getDesignNumber() {
        return designNumber;
    }

    public void setDesignNumber(String designNumber) {
        this.designNumber = designNumber;
    }

    public String getDesignIdentity() {
        return designIdentity;
    }

    public void setDesignIdentity(String designIdentity) {
        this.designIdentity = designIdentity;
    }

    public String getClassMark() {
        return classMark;
    }

    public void setClassMark(String classMark) {
        this.classMark = classMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    public Material getMaterialId(){
        return materialId;
    }
    
    public void setMaterialId(Material materialId){
        this.materialId = materialId;
    }

    public Design(Date date, List<Orderitems> orderitemsList, Integer idDesign, String designNumber, String designIdentity, String classMark, String name, BigDecimal k, BigDecimal k1, BigDecimal k2, BigDecimal k3, BigDecimal k4, BigDecimal k5, BigDecimal k6, BigDecimal k7, BigDecimal k8, BigDecimal k9, BigDecimal k10, BigDecimal k11, BigDecimal k12, BigDecimal k13, BigDecimal k14, BigDecimal k15, Material materialId) {
        this.date = date;
        this.orderitemsList = orderitemsList;
        this.idDesign = idDesign;
        this.designNumber = designNumber;
        this.designIdentity = designIdentity;
        this.classMark = classMark;
        this.name = name;
        this.k = k;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
        this.k5 = k5;
        this.k6 = k6;
        this.k7 = k7;
        this.k8 = k8;
        this.k9 = k9;
        this.k10 = k10;
        this.k11 = k11;
        this.k12 = k12;
        this.k13 = k13;
        this.k14 = k14;
        this.k15 = k15;
        this.materialId = materialId;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDesign != null ? idDesign.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Design)) {
            return false;
        }
        Design other = (Design) object;
        if ((this.idDesign == null && other.idDesign != null) || (this.idDesign != null && !this.idDesign.equals(other.idDesign))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stefan.data.Design[ idDesign=" + idDesign + " ]";
    }

    @XmlTransient
    public List<Orderitems> getOrderitemsList() {
        return orderitemsList;
    }

    public void setOrderitemsList(List<Orderitems> orderitemsList) {
        this.orderitemsList = orderitemsList;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getNiklanje() {
        return niklanje;
    }

    public void setNiklanje(Boolean niklanje) {
        this.niklanje = niklanje;
    }   

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getK() {
        return k;
    }

    public void setK(BigDecimal k) {
        this.k = k;
    }

    public BigDecimal getK1() {
        return k1;
    }

    public void setK1(BigDecimal k1) {
        this.k1 = k1;
    }

    public BigDecimal getK2() {
        return k2;
    }

    public void setK2(BigDecimal k2) {
        this.k2 = k2;
    }

    public BigDecimal getK3() {
        return k3;
    }

    public void setK3(BigDecimal k3) {
        this.k3 = k3;
    }

    public BigDecimal getK4() {
        return k4;
    }

    public void setK4(BigDecimal k4) {
        this.k4 = k4;
    }

    public BigDecimal getK5() {
        return k5;
    }

    public void setK5(BigDecimal k5) {
        this.k5 = k5;
    }

    public BigDecimal getK6() {
        return k6;
    }

    public void setK6(BigDecimal k6) {
        this.k6 = k6;
    }

    public BigDecimal getK7() {
        return k7;
    }

    public void setK7(BigDecimal k7) {
        this.k7 = k7;
    }

    public BigDecimal getK8() {
        return k8;
    }

    public void setK8(BigDecimal k8) {
        this.k8 = k8;
    }

    public BigDecimal getK9() {
        return k9;
    }

    public void setK9(BigDecimal k9) {
        this.k9 = k9;
    }

    public BigDecimal getK10() {
        return k10;
    }

    public void setK10(BigDecimal k10) {
        this.k10 = k10;
    }

    public BigDecimal getK11() {
        return k11;
    }

    public void setK11(BigDecimal k11) {
        this.k11 = k11;
    }

    public BigDecimal getK12() {
        return k12;
    }

    public void setK12(BigDecimal k12) {
        this.k12 = k12;
    }

    public BigDecimal getK13() {
        return k13;
    }

    public void setK13(BigDecimal k13) {
        this.k13 = k13;
    }

    public BigDecimal getK14() {
        return k14;
    }

    public void setK14(BigDecimal k14) {
        this.k14 = k14;
    }

    public BigDecimal getK15() {
        return k15;
    }

    public void setK15(BigDecimal k15) {
        this.k15 = k15;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
    
}
