/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewOrderJDialog.java
 *
 * Created on Feb 4, 2012, 4:38:17 PM
 */
package stefanpresentationlayer.dialogs;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.calendar.DateSelectionModel;
import stefan.business.BillManager;
import stefan.business.OrderManager;
import stefan.business.PresentationHelper;
import stefan.business.objects.*;
import stefanpresentationlayer.MyTableCellRenderer;



/**
 *
 * @author Matija
 */
public class NewOrderJDialog extends javax.swing.JDialog implements TableModelListener {

    private List<stefan.business.PresentationHelper> items = ObservableCollections.observableList(new ArrayList<stefan.business.PresentationHelper>());    
    private List<BusinessPartner> bpItems = ObservableCollections.observableList(new ArrayList<BusinessPartner>());
    private int position=10;
    
    private BigDecimal totalPrice;
    /** Creates new form NewOrderJDialog */
    public NewOrderJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();   
        setTitle("Nova Narudžba");
        totalPrice=new BigDecimal("0.0000");
        deleteBtn.setEnabled(false);
        createOrderBtn.setEnabled(false);
        jXOrderDatePicker.setDate(new Date());
        MaterialsTable.getModel().addTableModelListener(this);  
        
        MyTableCellRenderer rendrer=new MyTableCellRenderer();
        for (int i = 0; i < MaterialsTable.getModel().getColumnCount(); i++) {
             MaterialsTable.getColumnModel().getColumn(i).setCellRenderer(rendrer);
        }
        
       
        jXOrderDatePicker.getMonthView().setFirstDayOfWeek(2);
        jXOrderDatePicker.getMonthView().setShowingWeekNumber(true);        
        jXOrderDatePicker.getMonthView().getSelectionModel().setMinimalDaysInFirstWeek(4); 
        jXOrderDatePicker.setFormats(new String[] {"d.M.yyyy."});
      

        JXMonthView monthView = new JXMonthView();         
        monthView.setPreferredColumnCount(2);
        monthView.setPreferredRowCount(2);
        monthView.setFirstDayOfWeek(2);
        monthView.getSelectionModel().setMinimalDaysInFirstWeek(4); 
        monthView.setShowingWeekNumber(true);
        jXShippingDatePicker.setMonthView(monthView);
        jXShippingDatePicker.setFormats(new String[] {"d.M.yyyy."});        
        
        loadBusinessPartners();
      
    }
    
         /**
     * @return the bpItems
     */
    public List<BusinessPartner> getBpItems() {
        return bpItems;
    }

    /**
     * @param bpItems the bpItems to set
     */
    public void setBpItems(List<BusinessPartner> bpItems) {
        this.bpItems = bpItems;
    }

    private void loadBusinessPartners() {
        BillManager manager = new BillManager();
        bpItems = manager.getBusinessPartners();
        this.firePropertyChange("bpItems", null, null);
    }
    
    private boolean isShippingDateRequired()
    {
        int selectedFirmaIndex = cbxZaFirmu.getSelectedIndex();
        if(selectedFirmaIndex < 0){            
            return false;
        }
               
        BusinessPartner bp = bpItems.get(selectedFirmaIndex);
        if(bp == null){            
            return false;
        }
        return bp.getRequireShippingDate();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        MaterialsTable = new javax.swing.JTable();
        Nacrti = new javax.swing.JButton();
        orderNumberTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jXOrderDatePicker = new org.jdesktop.swingx.JXDatePicker();
        deleteBtn = new javax.swing.JButton();
        createOrderBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        priceLbl = new javax.swing.JLabel();
        addToExistingOrderBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbxZaFirmu = new javax.swing.JComboBox();
        jXShippingDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jLabelShippingDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        MaterialsTable.setAutoCreateRowSorter(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getResourceMap(NewOrderJDialog.class);
        MaterialsTable.setFont(resourceMap.getFont("MaterialsTable.font")); // NOI18N
        MaterialsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MaterialsTable.setName("MaterialsTable"); // NOI18N
        MaterialsTable.setRowHeight(24);
        MaterialsTable.setRowMargin(4);
        MaterialsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${items}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, MaterialsTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${position}"));
        columnBinding.setColumnName("Position");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designNumber}"));
        columnBinding.setColumnName("Design Number");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${revision}"));
        columnBinding.setColumnName("Revision");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designName}"));
        columnBinding.setColumnName("Design Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designIdentity}"));
        columnBinding.setColumnName("Design Identity");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designClass}"));
        columnBinding.setColumnName("Design Class");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${niklanje}"));
        columnBinding.setColumnName("Niklanje");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${isTokarenje}"));
        columnBinding.setColumnName("Is Tokarenje");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designDate}"));
        columnBinding.setColumnName("Design Date");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${parts}"));
        columnBinding.setColumnName("Parts");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pricePerPart}"));
        columnBinding.setColumnName("Price Per Part");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalPrice}"));
        columnBinding.setColumnName("Total Price");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(MaterialsTable);
        MaterialsTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title4")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title0")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        MaterialsTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title10")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title1")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title3")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title2")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(6).setPreferredWidth(30);
        MaterialsTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title9")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(7).setPreferredWidth(30);
        MaterialsTable.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title11")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title5")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title6")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title7")); // NOI18N
        MaterialsTable.getColumnModel().getColumn(11).setHeaderValue(resourceMap.getString("MaterialsTable.columnModel.title8")); // NOI18N

        Nacrti.setIcon(resourceMap.getIcon("Nacrti.icon")); // NOI18N
        Nacrti.setText(resourceMap.getString("Nacrti.text")); // NOI18N
        Nacrti.setName("Nacrti"); // NOI18N
        Nacrti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NacrtiMouseClicked(evt);
            }
        });
        Nacrti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NacrtiActionPerformed(evt);
            }
        });

        orderNumberTextField.setText(resourceMap.getString("orderNumberTextField.text")); // NOI18N
        orderNumberTextField.setName("orderNumberTextField"); // NOI18N
        orderNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderNumberTextFieldActionPerformed(evt);
            }
        });
        orderNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                orderNumberTextFieldKeyTyped(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jXOrderDatePicker.setName("jXOrderDatePicker"); // NOI18N

        deleteBtn.setIcon(resourceMap.getIcon("deleteBtn.icon")); // NOI18N
        deleteBtn.setName("deleteBtn"); // NOI18N
        deleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteBtnMouseClicked(evt);
            }
        });

        createOrderBtn.setText(resourceMap.getString("createOrderBtn.text")); // NOI18N
        createOrderBtn.setName("createOrderBtn"); // NOI18N
        createOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrderBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText(resourceMap.getString("cancelBtn.text")); // NOI18N
        cancelBtn.setName("cancelBtn"); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        priceLbl.setFont(resourceMap.getFont("priceLbl.font")); // NOI18N
        priceLbl.setText(resourceMap.getString("priceLbl.text")); // NOI18N
        priceLbl.setName("priceLbl"); // NOI18N

        addToExistingOrderBtn.setText(resourceMap.getString("addToExistingOrderBtn.text")); // NOI18N
        addToExistingOrderBtn.setName("addToExistingOrderBtn"); // NOI18N
        addToExistingOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToExistingOrderBtnActionPerformed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        cbxZaFirmu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxZaFirmu.setName("cbxZaFirmu"); // NOI18N

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bpItems}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cbxZaFirmu);
        bindingGroup.addBinding(jComboBoxBinding);

        cbxZaFirmu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxZaFirmuActionPerformed(evt);
            }
        });

        jXShippingDatePicker.setName("jXShippingDatePicker"); // NOI18N

        jLabelShippingDate.setText(resourceMap.getString("jLabelShippingDate.text")); // NOI18N
        jLabelShippingDate.setName("jLabelShippingDate"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orderNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXOrderDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cbxZaFirmu, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXShippingDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelShippingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(Nacrti, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addToExistingOrderBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 547, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createOrderBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabelShippingDate))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orderNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXOrderDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxZaFirmu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXShippingDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nacrti, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createOrderBtn)
                    .addComponent(cancelBtn)
                    .addComponent(addToExistingOrderBtn))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NacrtiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NacrtiMouseClicked

         
        DesignJDialog designDialog = new DesignJDialog(null, true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
         designDialog.setSize(dim.width, (dim.height - 100)); 
         designDialog.setLocation(0, 50);  
         designDialog.setVisible(true);
         //dohvati vrijednosti koje su unesene u ovaj dialog  
         if (designDialog.getSelectedDesign() != null) {
             stefan.business.PresentationHelper helper = new PresentationHelper(designDialog.getSelectedDesign(), designDialog.getParts()); 
             helper.setPosition(String.format("%05d", position));
             position+=10;
             items.add(helper);    	
             totalPrice=totalPrice.add(helper.getTotalPrice());            
             priceLbl.setText(totalPrice.setScale(2, RoundingMode.HALF_UP).toPlainString().replace('.',','));
             if (!items.isEmpty())
             {
                deleteBtn.setEnabled(true);   
                createOrderBtn.setEnabled(true);
             }
             this.firePropertyChange("items", null, null); 
             MaterialsTable.getSelectionModel().setSelectionInterval(0, items.size()-1);
             MaterialsTable.scrollRectToVisible(new Rectangle(MaterialsTable.getCellRect(items.size()-1,0, true)));
         } else {
             
         }           
		 }//GEN-LAST:event_NacrtiMouseClicked

    private void orderNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderNumberTextFieldActionPerformed

    private void deleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMouseClicked
       int rowIndex = MaterialsTable.getSelectedRow();
       if (rowIndex!=-1)
       {
           totalPrice=totalPrice.min(items.get(rowIndex).getTotalPrice());            
           priceLbl.setText(totalPrice.setScale(2, RoundingMode.HALF_UP).toPlainString().replace('.',','));
           items.remove(rowIndex);           
           this.firePropertyChange("items", null, null); 
           if (items.isEmpty())
           {
               deleteBtn.setEnabled(false);   
               createOrderBtn.setEnabled(false);
           }
       }
    }//GEN-LAST:event_deleteBtnMouseClicked

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void createOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrderBtnActionPerformed

        if (orderNumberTextField.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Unesite broj narudžbe.","Upozorenje",javax.swing.JOptionPane.WARNING_MESSAGE);      
            return;
        }
        
        int selectedFirmaIndex = cbxZaFirmu.getSelectedIndex();
        if(selectedFirmaIndex < 0){
            JOptionPane.showMessageDialog(null, "Odaberite firmu", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
               
        BusinessPartner bp = bpItems.get(selectedFirmaIndex);
        if(bp == null){
            JOptionPane.showMessageDialog(null, "Odaberite firmu", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
                
        if(isShippingDateRequired() && jXShippingDatePicker.getDate() == null){
            JOptionPane.showMessageDialog(null, "Odaberite datum isporuke", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Order order=new Order();
        order.setDate(jXOrderDatePicker.getDate());
        order.setIsDelivered(false);
        order.setOrderNumber(orderNumberTextField.getText());
        order.setBusinessPartnerId(bp.getId());
        if (isShippingDateRequired()) {
            order.setShippingDate(jXShippingDatePicker.getDate());
        }
        
        List<OrderItem> orderItems=new ArrayList<OrderItem>();       
        for (PresentationHelper item : items) 
        {
            OrderItem oi=new OrderItem();
            
            oi.setPosition(item.getPosition());
            oi.setQuantityDelivered(0);
            oi.setQuantityOrdered(item.getParts());           
            oi.setDesignId(item.getDesingDBid());
            
            orderItems.add(oi);
        }
        order.setOrderitemsList(orderItems);
        
        OrderManager orderManager=new OrderManager();
        orderManager.SaveOrder(order);
        this.dispose();
    }//GEN-LAST:event_createOrderBtnActionPerformed

    private void NacrtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NacrtiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NacrtiActionPerformed

    private void orderNumberTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orderNumberTextFieldKeyTyped
       ValidateTextField(evt);
    }//GEN-LAST:event_orderNumberTextFieldKeyTyped

    private void addToExistingOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToExistingOrderBtnActionPerformed
        if (orderNumberTextField.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Unesite broj narudžbe.","Upozorenje",javax.swing.JOptionPane.WARNING_MESSAGE);      
            return;
        }
        
        if(!OrderExists(orderNumberTextField.getText().trim())){
            JOptionPane.showMessageDialog(null, "Narudžba s ovim brojem ne postoji","Upozorenje",javax.swing.JOptionPane.WARNING_MESSAGE);   
        }
        OrderManager manager = new OrderManager();
        Order existingOrder = manager.GetOrderByOrderNumber(orderNumberTextField.getText().trim());
        
        List<OrderItem> orderItems=new ArrayList<OrderItem>();       
        for (PresentationHelper item : items) 
        {
            OrderItem oi=new OrderItem();
            
            oi.setPosition(item.getPosition());
            oi.setQuantityDelivered(0);
            oi.setQuantityOrdered(item.getParts());           
            oi.setDesignId(item.getDesingDBid());
            
            orderItems.add(oi);
        }
        existingOrder.setOrderitemsList(orderItems);
        try {
            manager.UpdateOrder(existingOrder);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }//GEN-LAST:event_addToExistingOrderBtnActionPerformed

private void cbxZaFirmuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxZaFirmuActionPerformed

    boolean isShippingDateRequired = isShippingDateRequired();
    jXShippingDatePicker.setVisible(isShippingDateRequired);
    jLabelShippingDate.setVisible(isShippingDateRequired);
}//GEN-LAST:event_cbxZaFirmuActionPerformed

     public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        //TableModel model = (TableModel)e.getSource();
        //String columnName = model.getColumnName(column);
       // Object data = model.getValueAt(row, column);    
        if (row!=-1 && column==6)
        {  
            List<PresentationHelper> tempItems=new ArrayList<PresentationHelper>();
            for (PresentationHelper presentationHelper : items) {
                PresentationHelper p=new PresentationHelper();
                p.setDesignClass(presentationHelper.getDesignClass());
                p.setDesignDate(presentationHelper.getDesignDate());
                p.setDesignIdentity(presentationHelper.getDesignIdentity());
                p.setDesignName(presentationHelper.getDesignName());
                p.setDesignNumber(presentationHelper.getDesignNumber());
                p.setDesingDBid(presentationHelper.getDesingDBid());
                p.setParts(presentationHelper.getParts());               
                p.setPosition((presentationHelper.getPosition()));
                
                tempItems.add(p);
            }
            items.clear();
            items.addAll(tempItems);
            this.firePropertyChange("items", null, null); 
            updateTotalPrice();
        }

        
       
    }
     
     private void updateTotalPrice()
     {
         totalPrice=new BigDecimal("0.0000");
         for (PresentationHelper presentationHelper : items) {
             totalPrice=totalPrice.add(presentationHelper.getTotalPrice());
         }
         priceLbl.setText(totalPrice.setScale(2, RoundingMode.HALF_UP).toPlainString().replace('.',','));         
     }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewOrderJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewOrderJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewOrderJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewOrderJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                NewOrderJDialog dialog = new NewOrderJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
      public List<stefan.business.PresentationHelper> getItems() {
        return items;
    }

      private void ValidateTextField(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            super.processKeyEvent(evt);
        } else {
            evt.consume();
            return;
        }
    }
    /**
     * @param items the items to set
     */
    public void setItems(List<stefan.business.PresentationHelper> items) {
        this.items = items;
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MaterialsTable;
    private javax.swing.JButton Nacrti;
    private javax.swing.JButton addToExistingOrderBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox cbxZaFirmu;
    private javax.swing.JButton createOrderBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelShippingDate;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXDatePicker jXOrderDatePicker;
    private org.jdesktop.swingx.JXDatePicker jXShippingDatePicker;
    private javax.swing.JTextField orderNumberTextField;
    private javax.swing.JLabel priceLbl;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private boolean OrderExists(String orderNum) {
        OrderManager m = new OrderManager();
        try {
            Order o = m.GetOrderByOrderNumber(orderNum);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
