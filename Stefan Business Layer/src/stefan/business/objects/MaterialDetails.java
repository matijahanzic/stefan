/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.math.BigDecimal;

/**
 *
 * @author Matija
 */
public class MaterialDetails {
    
    private Integer idMaterialDetails;   
    private String materialName;    
    private BigDecimal density;   
    private Boolean niklanje;

    public MaterialDetails(Integer idMaterialDetails) {
        this.idMaterialDetails = idMaterialDetails;
    }

    public MaterialDetails() {
        
    }

    /**
     * @return the idMaterialDetails
     */
    public Integer getIdMaterialDetails() {
        return idMaterialDetails;
    }

    /**
     * @param idMaterialDetails the idMaterialDetails to set
     */
    public void setIdMaterialDetails(Integer idMaterialDetails) {
        this.idMaterialDetails = idMaterialDetails;
    }

    /**
     * @return the materialName
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * @param materialName the materialName to set
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * @return the density
     */
    public BigDecimal getDensity() {
        return density;
    }

    /**
     * @param density the density to set
     */
    public void setDensity(BigDecimal density) {
        this.density = density;
    }

    /**
     * @return the niklanje
     */
    public Boolean getNiklanje() {
        return niklanje;
    }

    /**
     * @param niklanje the niklanje to set
     */
    public void setNiklanje(Boolean niklanje) {
        this.niklanje = niklanje;
    }
    
}
