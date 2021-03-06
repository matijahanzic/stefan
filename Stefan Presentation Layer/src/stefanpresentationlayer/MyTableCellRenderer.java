/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefanpresentationlayer;

import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import stefan.business.objects.BillItem;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

    private List<Integer> selectedItems;
    private List<BillItem> billItems;
    SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
       
       
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

         String columName = table.getColumnName(vColIndex);
         if( value instanceof Date) {
            value = f.format(value);
        }
         else if (value instanceof BigDecimal)
         {
             value = ((BigDecimal)value).toPlainString().replace('.', ',');            
         }
         else if (value instanceof Boolean)
         {
             if ("Tokarenje".equals(columName) || "Is Tokarenje".equals(columName)){
                 value = ((Boolean)value) ? "T" : "G";
             }
             else {
                 value = ((Boolean)value) ? "X02" : "F";
             }
         }
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowIndex, vColIndex);

        if(billItems!=null && rowIndex<billItems.size() && isAlreadySelected(billItems.get(rowIndex)))
        {            
            comp.setBackground(Color.RED);
        }
        else if (isSelected)
        {
            comp.setBackground(new Color(51, 153, 255));
        }
        else if (rowIndex % 2==0)
        {          
            comp.setBackground(new Color(255, 255, 204));            
        }
        else
        {
            comp.setBackground(Color.WHITE);                        
        }
        
        return comp;
    }
    
    private boolean isAlreadySelected(BillItem item)
    {
        if (selectedItems==null)
        {
            return false;
        }
        if (selectedItems.contains(item.getOrderItemId()))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    /**
     * @return the selectedItems
     */
    public List<Integer> getSelectedItems() {
        return selectedItems;
    }

    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(List<Integer> selectedItems) {
        this.selectedItems = selectedItems;
    }

    /**
     * @return the billItems
     */
    public List<BillItem> getBillItems() {
        return billItems;
    }

    /**
     * @param billItems the billItems to set
     */
    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }
    
    
}