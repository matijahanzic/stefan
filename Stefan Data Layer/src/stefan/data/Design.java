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
    @NamedQuery(name = "Design.findByNumber", query = "SELECT d FROM Design d WHERE d.isActive = 1 and  d.designNumber LIKE :designNumber ORDER BY d.designNumber"),
    @NamedQuery(name = "Design.findByNumberFopac", query = "SELECT d FROM Design d WHERE d.isActive = 1 and  d.code = 'FOPAC' and  d.designNumber LIKE :designNumber ORDER BY d.designNumber"),
    @NamedQuery(name = "Design.findByNumberWH", query = "SELECT d FROM Design d WHERE d.isActive = 1 and  d.code = 'WH' and  d.designNumber LIKE :designNumber ORDER BY d.designNumber"),
    @NamedQuery(name = "Design.findByFilters", query = "SELECT d FROM Design d WHERE d.isActive = 1  and d.designIdentity LIKE :designIdentity and d.classMark LIKE :classMark and d.designNumber LIKE :designNumber ORDER BY d.designNumber")})
public class Design implements Serializable {

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "dateModified")
    @Temporal(TemporalType.DATE)
    private Date dateModified;
    @Column(name = "revision")
    private String revision;
    @Basic(optional = false)
    @Column(name = "isTokarenje")
    private boolean isTokarenje;
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
    @Column(name = "8k")
    private BigDecimal k16;
    @Column(name = "16k")
    private BigDecimal k17;
    @Column(name = "32k")
    private BigDecimal k18;
    @Column(name = "64k")
    private BigDecimal k19;
    @Column(name = "128k")
    private BigDecimal k20;
    @Column(name = "isActive")
    private Boolean isActive;
    @Column(name = "niklanje")
    private Boolean niklanje;
    @Column(name = "isNlx")
    private Boolean isNlx;
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
    @Column(name = "code")
    private String code;
    
    @Column(name = "calcIsFerting")
    private Boolean calcIsFerting;
    @Column(name = "calcMinPoKom")
    private BigDecimal calcMinPoKom;
    @Column(name = "calcStezanjeTok")
    private BigDecimal calcStezanjeTok;
    @Column(name = "calcStezanjeGlod")
    private BigDecimal calcStezanjeGlod;
    @Column(name = "calcSatnica")
    private BigDecimal calcSatnica;
    @Column(name = "calcEurPoKom")
    private BigDecimal calcEurPoKom;
    @Column(name = "posao1k")
    private BigDecimal posao1k;
    @Column(name = "posao2k")
    private BigDecimal posao2k;
    @Column(name = "posao3k")
    private BigDecimal posao3k;
    @Column(name = "posao4k")
    private BigDecimal posao4k;
    @Column(name = "posao5k")
    private BigDecimal posao5k;
    @Column(name = "posao8k")
    private BigDecimal posao8k;
    @Column(name = "posao10k")
    private BigDecimal posao10k;
    @Column(name = "posao15k")
    private BigDecimal posao15k;
    @Column(name = "posao16k")
    private BigDecimal posao16k;
    @Column(name = "posao20k")
    private BigDecimal posao20k;
    @Column(name = "posao32k")
    private BigDecimal posao32k;
    @Column(name = "posao50k")
    private BigDecimal posao50k;
    @Column(name = "posao64k")
    private BigDecimal posao64k;
    @Column(name = "posao100k")
    private BigDecimal posao100k;
    @Column(name = "posao128k")
    private BigDecimal posao128k;
    @Column(name = "posao200k")
    private BigDecimal posao200k;
    @Column(name = "posao500k")
    private BigDecimal posao500k;
    
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

    public Material getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Material materialId) {
        this.materialId = materialId;
    }

    public Design(Date date, List<Orderitems> orderitemsList, Integer idDesign, String designNumber, String designIdentity, String classMark, String name, BigDecimal k, BigDecimal k1, BigDecimal k2, BigDecimal k3, BigDecimal k4, BigDecimal k5, BigDecimal k6, BigDecimal k7, BigDecimal k8, BigDecimal k9, BigDecimal k10, BigDecimal k11, BigDecimal k12, BigDecimal k13, BigDecimal k14, BigDecimal k15,BigDecimal k16,BigDecimal k17,BigDecimal k18,BigDecimal k19,BigDecimal k20, Material materialId) {
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
        this.k16 = k16;
        this.k17 = k17;
        this.k18 = k18;
        this.k19 = k19;
        this.k20 = k20;
        
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

    public Boolean getIsNlx() {
        return isNlx;
    }

    public void setIsNlx(Boolean isNlx) {
        this.isNlx = isNlx;
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
    
    public BigDecimal getK16() {
        return k16;
    }

    public void setK16(BigDecimal k16) {
        this.k16 = k16;
    }
    
    public BigDecimal getK17() {
        return k17;
    }

    public void setK17(BigDecimal k17) {
        this.k17 = k17;
    }
    
    public BigDecimal getK18() {
        return k18;
    }

    public void setK18(BigDecimal k18) {
        this.k18 = k18;
    }
    
    public BigDecimal getK19() {
        return k19;
    }

    public void setK19(BigDecimal k19) {
        this.k19 = k19;
    }
    
    public BigDecimal getK20() {
        return k20;
    }

    public void setK20(BigDecimal k20) {
        this.k20 = k20;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public boolean getIsTokarenje() {
        return isTokarenje;
    }

    public void setIsTokarenje(boolean isTokarenje) {
        this.isTokarenje = isTokarenje;
    }

    public String getCode(){
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public boolean getCalcIsFerting() {
        return calcIsFerting == null ? false : calcIsFerting;
    }

    public void setCalcIsFerting(boolean calcIsFerting) {
        this.calcIsFerting = calcIsFerting;
    }

    public BigDecimal getCalcMinPoKom() {
        return calcMinPoKom;
    }

    public void setCalcMinPoKom(BigDecimal calcMinPoKom) {
        this.calcMinPoKom = calcMinPoKom;
    }

    public BigDecimal getCalcStezanjeTok() {
        return calcStezanjeTok;
    }

    public void setCalcStezanjeTok(BigDecimal calcStezanjeTok) {
        this.calcStezanjeTok = calcStezanjeTok;
    }

    public BigDecimal getCalcStezanjeGlod() {
        return calcStezanjeGlod;
    }

    public void setCalcStezanjeGlod(BigDecimal calcStezanjeGlod) {
        this.calcStezanjeGlod = calcStezanjeGlod;
    }

    public BigDecimal getCalcSatnica() {
        return calcSatnica;
    }

    public void setCalcSatnica(BigDecimal calcSatnica) {
        this.calcSatnica = calcSatnica;
    }

    public BigDecimal getCalcEurPoKom() {
        return calcEurPoKom;
    }

    public void setCalcEurPoKom(BigDecimal calcEurPoKom) {
        this.calcEurPoKom = calcEurPoKom;
    }

    public BigDecimal getPosao1k() {
        return posao1k;
    }

    public void setPosao1k(BigDecimal posao1k) {
        this.posao1k = posao1k;
    }

    public BigDecimal getPosao2k() {
        return posao2k;
    }

    public void setPosao2k(BigDecimal posao2k) {
        this.posao2k = posao2k;
    }

    public BigDecimal getPosao3k() {
        return posao3k;
    }

    public void setPosao3k(BigDecimal posao3k) {
        this.posao3k = posao3k;
    }

    public BigDecimal getPosao4k() {
        return posao4k;
    }

    public void setPosao4k(BigDecimal posao4k) {
        this.posao4k = posao4k;
    }

    public BigDecimal getPosao5k() {
        return posao5k;
    }

    public void setPosao5k(BigDecimal posao5k) {
        this.posao5k = posao5k;
    }

    public BigDecimal getPosao8k() {
        return posao8k;
    }

    public void setPosao8k(BigDecimal posao8k) {
        this.posao8k = posao8k;
    }

    public BigDecimal getPosao10k() {
        return posao10k;
    }

    public void setPosao10k(BigDecimal posao10k) {
        this.posao10k = posao10k;
    }

    public BigDecimal getPosao15k() {
        return posao15k;
    }

    public void setPosao15k(BigDecimal posao15k) {
        this.posao15k = posao15k;
    }

    public BigDecimal getPosao16k() {
        return posao16k;
    }

    public void setPosao16k(BigDecimal posao16k) {
        this.posao16k = posao16k;
    }

    public BigDecimal getPosao20k() {
        return posao20k;
    }

    public void setPosao20k(BigDecimal posao20k) {
        this.posao20k = posao20k;
    }

    public BigDecimal getPosao32k() {
        return posao32k;
    }

    public void setPosao32k(BigDecimal posao32k) {
        this.posao32k = posao32k;
    }

    public BigDecimal getPosao50k() {
        return posao50k;
    }

    public void setPosao50k(BigDecimal posao50k) {
        this.posao50k = posao50k;
    }

    public BigDecimal getPosao64k() {
        return posao64k;
    }

    public void setPosao64k(BigDecimal posao64k) {
        this.posao64k = posao64k;
    }

    public BigDecimal getPosao100k() {
        return posao100k;
    }

    public void setPosao100k(BigDecimal posao100k) {
        this.posao100k = posao100k;
    }

    public BigDecimal getPosao128k() {
        return posao128k;
    }

    public void setPosao128k(BigDecimal posao128k) {
        this.posao128k = posao128k;
    }

    public BigDecimal getPosao200k() {
        return posao200k;
    }

    public void setPosao200k(BigDecimal posao200k) {
        this.posao200k = posao200k;
    }

    public BigDecimal getPosao500k() {
        return posao500k;
    }

    public void setPosao500k(BigDecimal posao500k) {
        this.posao500k = posao500k;
    }
}
