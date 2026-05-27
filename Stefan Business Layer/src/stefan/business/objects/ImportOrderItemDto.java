/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

/**
 *
 * @author Administrator
 */
public class ImportOrderItemDto {
     private OrderItem existingOrderItem;    
      private OrderItem importedOrderItem;    
      private Boolean selected = true;
      private String warningText;
      
      public String getWarningText(){
    return this.warningText;
}

public void setWarningText(String warningText){
    this.warningText = warningText;
}

public Boolean getSelected(){
    return this.selected;
}

public void setSelected(Boolean selected){
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
}
