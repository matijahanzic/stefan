/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImportOrdersFromCsvJDialog.java
 *
 * Created on May 27, 2026, 9:37:22 PM
 */
package stefanpresentationlayer.dialogs;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.ListSelectionModel;
import org.jdesktop.observablecollections.ObservableCollections;
import stefan.business.OrderManager;
import stefan.business.objects.ImportOrderDto;
import stefan.business.objects.ImportOrderItemDto;
import stefan.business.objects.Order;
import stefan.business.objects.OrderItem;
import stefanpresentationlayer.MixedRenderer;
import stefanpresentationlayer.MultiLineCellRenderer;
import stefanpresentationlayer.MyTableCellRenderer;

/**
 *
 * @author Administrator
 */
public class ImportOrdersFromCsvJDialog extends javax.swing.JDialog {

    private Order importOrder;
    public List<ImportOrderItemDto> importOrderItems = ObservableCollections.observableList(new ArrayList<ImportOrderItemDto>());

    public List<ImportOrderItemDto> getImportOrderItems() {
        return importOrderItems;
    }

    public void setImportOrderItems(List<ImportOrderItemDto> importOrderItems) {
        this.importOrderItems = importOrderItems;
    }

    /** Creates new form ImportOrdersFromCsvJDialog */
    public ImportOrdersFromCsvJDialog(java.awt.Frame parent, String filePath) throws FileNotFoundException, IOException {
        super(parent, true);
        initComponents();

        setTitle("Učitavanje narudžbi iz csv");

        jXDatePickerDesignDate.setDate(new Date());
        jXDatePickerDesignDate.getMonthView().setFirstDayOfWeek(2);
        jXDatePickerDesignDate.getMonthView().setShowingWeekNumber(true);
        jXDatePickerDesignDate.getMonthView().getSelectionModel().setMinimalDaysInFirstWeek(4);

        jTable1.getColumnModel().getColumn(1).setCellRenderer(new MixedRenderer());

        ImportFromFile(filePath);

    }

    private void ImportFromFile(String filePath) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        Order order = new Order();
        Map<String, ImportOrderItemDto> itemMap = new HashMap<String, ImportOrderItemDto>();

        // Context-tracking variables for dynamic header mapping
        List<String> currentHeaders = null;
        String currentDescriptorType = "";
        String currentDescriptorSubtype = "";
        OrderItem currentItemScope = null;

        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0) {
                continue;
            }

            List<String> tokens = parseCsvLine(line);
            String rowType = tokens.get(0);

            if ("descriptor".equals(rowType)) {
                // Keep track of the current schema block
                currentDescriptorType = tokens.size() > 1 ? tokens.get(1) : "";
                currentDescriptorSubtype = tokens.size() > 2 ? tokens.get(2) : "";
                currentHeaders = tokens;
            } else if ("head".equals(rowType) || "position".equals(rowType)) {
                String subType = tokens.size() > 1 ? tokens.get(1) : "";

                // Construct a column-name to data-value map for the current row
                Map<String, String> rowData = new HashMap<String, String>();
                if (currentHeaders != null) {
                    for (int i = 0; i < tokens.size(); i++) {
                        if (i < currentHeaders.size() && !currentHeaders.get(i).equals("")) {
                            rowData.put(currentHeaders.get(i), tokens.get(i));
                        }
                    }
                }

                // 1. Core Order Details Row
                if ("head".equals(rowType) && "".equals(subType)) {
                    order.setOrderNumber(rowData.get("order_number"));
                } // 2. Order Address Row
                else if ("head".equals(rowType) && "delivery_address".equals(subType)) {
                    String n1 = rowData.get("name1");
                    String n12 = rowData.get("address");
                    String n13 = rowData.get("city");

                    order.setBusinessPartnerName(n1);

                } // 3. Item Core Details Row
                else if ("position".equals(rowType) && "".equals(subType)) {
                    String posNum = tokens.get(2); // Always 5 characters at position index 2

                    ImportOrderItemDto importOrderItem = new ImportOrderItemDto();
                    OrderItem item = new OrderItem();
                    item.setPosition(posNum);
                    
                    try {
                        NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
                        
                        int quantity = nf.parse(rowData.get("quantity")).intValue();
                        item.setQuantityOrdered(quantity);
                    } catch (ParseException e) {
                        importOrderItem.setWarningText("Količina nije ispravan cijeli broj");
                    }
                     
                    /* TODO provjeriti cijenu
                    item.pricePerUnit = rowData.get("price_per_unit");
                     *  item.cost = rowData.get("costs");
                     
                     */
                   /* TODO provjeriti reviziju
                    item.revLev = rowData.get("rev_lev");
                    item.materialName = rowData.get("material_name");
                    item.zznumber = rowData.get("ZZNUMMER");
                    item.extwg = rowData.get("EXTWG");
*/
                    importOrderItem.setImportedOrderItem(item);
                    itemMap.put(posNum, importOrderItem);
                    
                    List<OrderItem> orderItems = order.getOrderitemsList();
                    if(orderItems == null)
                        orderItems = new ArrayList<OrderItem>();
                    orderItems.add(item);
                    order.setOrderitemsList(orderItems);
                    
                    currentItemScope = item; // Store scope context for immediate subsequent disposition rows
                } // 4. Item Delivery/Disposition Row
                else if ("position".equals(rowType) && "disposition".equals(subType)) {
                    if (currentItemScope != null) {
                        /*
                        currentItemScope.deliveryDate = rowData.get("delivery_date");
                         * 
                         */
                        try{
                            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = f.parse(rowData.get("delivery_date"));
                        }catch(ParseException e){
                            System.out.println();
                        }
                    }
                }
            }
        }
        br.close();
        

    }

    private List<String> parseCsvLine(String line) {
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes; // Toggle quote block state
            } else if (c == ',' && !inQuotes) {
                result.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        result.add(sb.toString().trim());
        return result;
    }

    private void LoadOrder() {
        OrderManager manager = new OrderManager();
        Order o = manager.GetOrderByOrderNumberFullyMapped("4101562212");


        for (int i = 0; i < o.getOrderitemsList().size(); i++) {
            ImportOrderItemDto it = new ImportOrderItemDto();


            it.setImportedOrderItem(o.getOrderitemsList().get(i));

            importOrderItems.add(it);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel1 = new javax.swing.JLabel();
        brojNarudzbeText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        partnerText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jXDatePickerDesignDate = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getResourceMap(ImportOrdersFromCsvJDialog.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        brojNarudzbeText.setText(resourceMap.getString("brojNarudzbeText.text")); // NOI18N
        brojNarudzbeText.setName("brojNarudzbeText"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        partnerText.setText(resourceMap.getString("partnerText.text")); // NOI18N
        partnerText.setName("partnerText"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jXDatePickerDesignDate.setFormats(new String[] {"d.M.yyyy."});
        jXDatePickerDesignDate.setMaximumSize(new java.awt.Dimension(140, 22));
        jXDatePickerDesignDate.setMinimumSize(new java.awt.Dimension(140, 22));
        jXDatePickerDesignDate.setName("jXDatePickerDesignDate"); // NOI18N
        jXDatePickerDesignDate.setPreferredSize(new java.awt.Dimension(140, 22));
        jXDatePickerDesignDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePickerDesignDateActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setName("jTable1"); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${importOrderItems}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${selected}"));
        columnBinding.setColumnName("Selected");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${warningText}"));
        columnBinding.setColumnName("Warning Text");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.design.designNumber}"));
        columnBinding.setColumnName("Imported Order Item.design.design Number");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brojNarudzbeText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePickerDesignDate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(partnerText, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(brojNarudzbeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jXDatePickerDesignDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(partnerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

private void jXDatePickerDesignDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePickerDesignDateActionPerformed
}//GEN-LAST:event_jXDatePickerDesignDateActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField brojNarudzbeText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerDesignDate;
    private javax.swing.JTextField partnerText;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
