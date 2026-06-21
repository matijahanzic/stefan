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
public class ImportOrderItemDto {

    private OrderItem existingOrderItem;
    private OrderItem importedOrderItem;
    private Boolean selected = false;
    private String warningText;
    private BigDecimal pricePerPart;
    private BigDecimal total;
    private Boolean indShouldImport = true;
    private String tngDisplayName;

    public String getTNGDisplayName() {
        return tngDisplayName;
    }

    public void setTNGDisplayName(String tngDisplayName) {
        this.tngDisplayName = tngDisplayName;
    }

    public Boolean getIndDesignModified() {
        
        if(getTNGDisplayName() == null || getTNGDisplayName().trim().isEmpty())
            return false;
        
        OrderItem oi = getImportedOrderItem();
        if (oi == null) {
            return false;
        }

        Design d = oi.getDesign();
        if (d == null || d.getIsTokarenjeDisplayName() == null) {
            return false;
        }

        return !d.getIsTokarenjeDisplayName().equalsIgnoreCase(getTNGDisplayName());

    }

    public Boolean getIndShouldImport() {
        return indShouldImport;
    }

    public void setIndShouldImport(Boolean shouldImport) {
        indShouldImport = shouldImport;
    }

    public String getWarningText() {
        return this.warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public OrderItem getExistingOrderItem() {
        return existingOrderItem;
    }

    public void setExistingOrderItem(OrderItem existingOrderItem) {
        this.existingOrderItem = existingOrderItem;
    }

    public OrderItem getImportedOrderItem() {
        return importedOrderItem;
    }

    public void setImportedOrderItem(OrderItem importedOrderItem) {
        this.importedOrderItem = importedOrderItem;
    }

    public BigDecimal getPricePerPart() {
        return this.pricePerPart;
    }

    public void setPricePerPart(BigDecimal pricePerPart) {
        this.pricePerPart = pricePerPart;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
