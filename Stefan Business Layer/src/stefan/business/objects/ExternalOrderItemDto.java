/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class ExternalOrderItemDto {

    private String designNumber;
    private BigDecimal pricePerPart;
    private Integer availablePcs;
    
    public ExternalOrderItemDto(String designNumber, BigDecimal pricePerPart, Integer availablePcs) {
        this.pricePerPart = pricePerPart;
        this.availablePcs = availablePcs;
        this.designNumber = designNumber;
    }
    
    
    
    
    public BigDecimal getPricePerPart(){
        return pricePerPart;
    }
    
    public Integer getAvailablePcs(){
        return availablePcs;
    }
    
    public void setAvailablePcs(Integer availablePcs){
        this.availablePcs = availablePcs;
    }
    
    public String getDesignNumber(){
        return designNumber;
    }
}
