/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

/**
 *
 * @author Administrator
 */
public class ExternaPartnerlDesignNumberDto {

    public ExternaPartnerlDesignNumberDto(String designNumber, String partnerDisplayName) {
        this.partnerDisplayName = partnerDisplayName;
        this.designNumber = designNumber;
    }
    private String partnerDisplayName;

    public String getPartnerDisplayName() {
        return partnerDisplayName;
    }
    private String designNumber;

    public String getDesignNumber() {
        return designNumber;
    }
}
