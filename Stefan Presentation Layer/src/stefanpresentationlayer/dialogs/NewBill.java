/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewBill.java
 *
 * Created on Feb 7, 2012, 7:46:20 PM
 */
package stefanpresentationlayer.dialogs;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdesktop.observablecollections.ObservableCollections;
import stefan.business.BillManager;
import stefan.business.objects.BillItem;
import stefan.business.DesignManager;
import stefan.business.ExcelManager;
import stefan.business.OrderManager;
import stefan.business.objects.Bill;
import stefan.business.objects.BusinessPartner;
import stefan.business.objects.PackageNumberComparator;
import stefan.business.objects.OrderNumberComparator;
import stefan.business.objects.DesignNumberComparator;
import stefan.business.objects.ItemOrderNumberComparator;
import stefan.business.objects.Order;
import stefan.business.objects.TotalPriceComparator;
import stefan.data.Orderitems;
import stefanpresentationlayer.MyTableCellRenderer;

/**
 *
 * @author Matija
 */
public class NewBill extends javax.swing.JDialog implements TableModelListener {

    private List<BillItem> items = ObservableCollections.observableList(new ArrayList<BillItem>());
    private List<Bill> bills = ObservableCollections.observableList(new ArrayList<Bill>());
    private List<BillItem> itemsBackup = ObservableCollections.observableList(new ArrayList<BillItem>());
    private List<BusinessPartner> bpItems = ObservableCollections.observableList(new ArrayList<BusinessPartner>());
    private boolean billChanged = false;
    private boolean pageNumChanged = false;
    private int indexOfData = 0;
    private String fileName, filePath;
    private int bolzenKom = 0, welleKom = 0, totalKom = 0;
    private String billDate;
    private double bolzenCijena = 0, welleCijena = 0;
    private BigDecimal totalSum = new BigDecimal("0.00");
    private BigDecimal totalSumKn = new BigDecimal("0.00");
    private BigDecimal exchangeRate = new BigDecimal("0.00");
    private int otpremnicaCurrentRow = 0;

    /** Creates new form NewBill */
    public NewBill(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        sortByCombo.removeAllItems();
        sortByCombo.addItem("Broju nacrta");
        sortByCombo.addItem("Broju narudžbe");
        sortByCombo.addItem("Broju kartona");
        sortByCombo.addItem("Ukupnoj cijeni");
        sortByCombo.addItem("Rednom broju");
        sortByCombo.setSelectedIndex(0);
        deleteBtn.setEnabled(false);
        jXDatePicker1.setDate(new Date());
        saveBill.setEnabled(false);
        ItemsTable.getModel().addTableModelListener(this);
        setTitle("Novi Račun");

        MyTableCellRenderer rendrer = new MyTableCellRenderer();
        for (int i = 0; i < ItemsTable.getModel().getColumnCount(); i++) {
            ItemsTable.getColumnModel().getColumn(i).setCellRenderer(rendrer);
        }

        refreshBillList();
        _btnDeleteBill.setEnabled(false);
    }

    private BillItem copyBillItem(BillItem bi) {
        BillItem p = new BillItem();
        p.setDesignClass(bi.getDesignClass());
        p.setDesignId(bi.getDesignId());
        p.setDesignIdentity(bi.getDesignIdentity());
        p.setDesignName(bi.getDesignName());
        p.setDesignNumber(bi.getDesignNumber());
        p.setOrderId(bi.getOrderId());
        p.setOrderItemId(bi.getOrderItemId());
        p.setOrderNumber(bi.getOrderNumber());
        p.setOrderDate(bi.getOrderDate());
        p.setParts(bi.getParts());
        p.setPricePerPart(bi.getPricePerPart());
        p.setPosition(bi.getPosition());
        p.setQuantityDelivered(bi.getQuantityDelivered());
        p.setQuantityOrdered(bi.getQuantityOrdered());
        p.setPackageNumber(bi.getPackageNumber());
        p.setNiklanje(bi.getNiklanje());
        p.setItemOrderNumber(bi.getItemOrderNumber());
        return p;
    }

    private void refreshBillList() {
        BillManager manager = new BillManager();
        bills = manager.getSavedBills();
        Bill b = new Bill();
        b.setBillNumber("Novi Račun");
        bills.add(0, b);
        this.firePropertyChange("bills", null, null);
        bpItems = manager.getBusinessPartners();
        this.firePropertyChange("bpItems", null, null);
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
        billNumberTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane2 = new javax.swing.JScrollPane();
        ItemsTable = new javax.swing.JTable();
        addBillItemBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        saveBill = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        priceLbl = new javax.swing.JLabel();
        cbxSavedBills = new javax.swing.JComboBox();
        _btnSaveBill = new javax.swing.JButton();
        _btnDeleteBill = new javax.swing.JButton();
        sortByCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbxZaFirmu = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getResourceMap(NewBill.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        billNumberTextField.setName("billNumberTextField"); // NOI18N
        billNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                billNumberTextFieldKeyTyped(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jXDatePicker1.setName("jXDatePicker1"); // NOI18N
        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        ItemsTable.setFont(resourceMap.getFont("ItemsTable.font")); // NOI18N
        ItemsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ItemsTable.setName("ItemsTable"); // NOI18N
        ItemsTable.setRowHeight(24);
        ItemsTable.setRowMargin(4);
        ItemsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${items}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, ItemsTable, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${itemOrderNumber}"));
        columnBinding.setColumnName("Item Order Number");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designNumber}"));
        columnBinding.setColumnName("Design Number");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designClass}"));
        columnBinding.setColumnName("Design Class");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designIdentity}"));
        columnBinding.setColumnName("Design Identity");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designName}"));
        columnBinding.setColumnName("Design Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${orderNumber}"));
        columnBinding.setColumnName("Order Number");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${position}"));
        columnBinding.setColumnName("Position");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${packageNumber}"));
        columnBinding.setColumnName("Package Number");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${niklanje}"));
        columnBinding.setColumnName("Niklanje");
        columnBinding.setColumnClass(Boolean.class);
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
        jScrollPane2.setViewportView(ItemsTable);
        ItemsTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title0")); // NOI18N
        ItemsTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title1")); // NOI18N
        ItemsTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title2")); // NOI18N
        ItemsTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title3")); // NOI18N
        ItemsTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title4")); // NOI18N
        ItemsTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title5")); // NOI18N
        ItemsTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title9")); // NOI18N
        ItemsTable.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title6")); // NOI18N
        ItemsTable.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title7")); // NOI18N
        ItemsTable.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title8")); // NOI18N
        ItemsTable.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title11")); // NOI18N
        ItemsTable.getColumnModel().getColumn(11).setHeaderValue(resourceMap.getString("ItemsTable.columnModel.title11")); // NOI18N

        addBillItemBtn.setIcon(resourceMap.getIcon("addBillItemBtn.icon")); // NOI18N
        addBillItemBtn.setName("addBillItemBtn"); // NOI18N
        addBillItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBillItemBtnActionPerformed(evt);
            }
        });

        deleteBtn.setIcon(resourceMap.getIcon("deleteBtn.icon")); // NOI18N
        deleteBtn.setName("deleteBtn"); // NOI18N
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        saveBill.setText(resourceMap.getString("saveBill.text")); // NOI18N
        saveBill.setName("saveBill"); // NOI18N
        saveBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBillActionPerformed(evt);
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
        priceLbl.setName("priceLbl"); // NOI18N

        cbxSavedBills.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSavedBills.setName("cbxSavedBills"); // NOI18N

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bills}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cbxSavedBills);
        bindingGroup.addBinding(jComboBoxBinding);

        cbxSavedBills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSavedBillsActionPerformed(evt);
            }
        });

        _btnSaveBill.setText(resourceMap.getString("_btnSaveBill.text")); // NOI18N
        _btnSaveBill.setName("_btnSaveBill"); // NOI18N
        _btnSaveBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnSaveBillActionPerformed(evt);
            }
        });

        _btnDeleteBill.setText(resourceMap.getString("_btnDeleteBill.text")); // NOI18N
        _btnDeleteBill.setName("_btnDeleteBill"); // NOI18N
        _btnDeleteBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnDeleteBillActionPerformed(evt);
            }
        });

        sortByCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sortByCombo.setName("sortByCombo"); // NOI18N
        sortByCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByComboActionPerformed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField1.setText(resourceMap.getString("_exchangeRate.text")); // NOI18N
        jTextField1.setName("_exchangeRate"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        cbxZaFirmu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxZaFirmu.setName("cbxZaFirmu"); // NOI18N

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bpItems}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cbxZaFirmu);
        bindingGroup.addBinding(jComboBoxBinding);

        cbxZaFirmu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxZaFirmuActionPerformed(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(billNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(sortByCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbxZaFirmu, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(addBillItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxSavedBills, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_btnSaveBill, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(_btnDeleteBill, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cancelBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveBill, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(billNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sortByCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxZaFirmu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(addBillItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveBill)
                            .addComponent(cbxSavedBills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(_btnSaveBill)
                            .addComponent(_btnDeleteBill)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cancelBtn)))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBillActionPerformed

        if (billNumberTextField.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Unesite broj računa!", "Greška", JOptionPane.ERROR_MESSAGE);
        } else {
            String billNumber = billNumberTextField.getText().trim();
            billDate = jXDatePicker1.getDate().toLocaleString().substring(0, 11);
            
            if (jTextField1.getText().trim().isEmpty()) 
            {
                JOptionPane.showMessageDialog(null, "Unesite trenutni tečaj za euro.", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            try
            {
                exchangeRate = new BigDecimal(jTextField1.getText().trim().replace(',', '.'));
            }
            catch(Exception e)
            {         
                JOptionPane.showMessageDialog(null, "Greška kod upisa tečaja! Provjeriti unesene podatke.", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
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

            try {                
                int pageNum = 1;
                if (items.size() > 10) {
                    pageNum = (items.size() / 10);
                    if (items.size() % 10 != 0)
                    {
                        pageNum = pageNum + 1;
                    }
                } else {
                    pageNum = 1;
                }

                Collections.sort(items);

                if (ShowSaveFileDialog()) {

                    Workbook workbook = new HSSFWorkbook();
                    ExcelManager manager = new ExcelManager(workbook);
                    FileOutputStream file = manager.CreateNewFile(fileName, filePath);
                    indexOfData = 0;
                    for (int i = 1; i <= pageNum; i++) {
                        AddItemsToSheet(i, manager.CreateNewBillSheet(workbook, i - 1, billDate, billNumber, false,bp), pageNum, manager, workbook,bp);
                    }
                    if (pageNumChanged) {
                        pageNum++;
                    }
                    WriteAllPageNumbers(pageNum, manager, workbook);
                    workbook.write(file);
                    file.close();

                    CreateOtpremnica(bp);

                    //update delvered quantity in DB
                    OrderManager orderManager = new OrderManager();
                    for (BillItem billItem : items) {
                        orderManager.UpdateOrderItem(billItem);
                    }
                    //delete bill from DB
                    int index = cbxSavedBills.getSelectedIndex();
                    if (index != -1 && index != 0) {
                        BillManager billManager = new BillManager();
                        billManager.deleteBill(bills.get(index).getIdBill());
                    }
                    
                    ArrayList orderNumbers = new ArrayList();
                    for(BillItem billItem : items){
                        try{
                        Orderitems it = orderManager.getOrderItemById(billItem.getOrderItemId());
                        if(it.getQuantityDelivered()>=it.getQuantityOrdered()){
                            if(!orderNumbers.contains(it.getIdOrder().getOrderNumber())){
                                orderNumbers.add(it.getIdOrder().getOrderNumber());
                            }
                            orderManager.deleteOrderItem(billItem.getOrderItemId());
                        }
                        }catch(Exception e){
                        
                        }
                    }
                    
                    for(int oni=0; oni<orderNumbers.size();oni++){
                        try{
                        Order ord = orderManager.GetOrderByOrderNumberFullyMapped(orderNumbers.get(oni).toString());
                        if(ord.getOrderitemsList() == null || ord.getOrderitemsList().isEmpty()){
                            orderManager.deleteOrder(ord.getIdOrder());
                        }
                        }catch(Exception e){
                            
                        }
                    }
                    //open created documents 
                    Desktop dt = Desktop.getDesktop();
                    String billPath, otpremnicaPath;
                    billPath = filePath + "\\" + fileName + ".xls";
                    otpremnicaPath = filePath + "\\" + "Otpremnica.xls";
                    dt.open(new File(billPath));
                    dt.open(new File(otpremnicaPath));
                    this.dispose();
                } else {
                }

            } catch (Exception e) {
                totalKom = 0;
                totalPrice = BigDecimal.ZERO;
                totalSum = BigDecimal.ZERO;
                billChanged = false;
                pageNumChanged = false;
                indexOfData = 0;
                bolzenCijena = 0;
                welleCijena = 0;
                totalSum = new BigDecimal("0.00");
                totalSumKn = new BigDecimal("0.00");
                otpremnicaCurrentRow = 0;
                bolzenKom = 0;
                welleKom = 0;
                itemsBackup.clear();
                JOptionPane.showMessageDialog(null, "Pogreška pri stvaranju dokumenta " + e.toString(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
	}//GEN-LAST:event_saveBillActionPerformed

    private void AddItemsToOtpremnicaSheet(int i, Sheet sheet, int pageNum, ExcelManager manager, boolean shouldPrint) {
        int dodano = 0;
        if (i == 1) {
            while ((dodano <= 12) && (!itemsBackup.isEmpty())) {
                BillItem b = itemsBackup.remove(0);
                otpremnicaCurrentRow = manager.AddOtpremnicaBillItems(i, dodano, sheet, b,shouldPrint);
                dodano++;
            }
        } else {
            while ((dodano <= 13) && (!itemsBackup.isEmpty())) {
                BillItem b = itemsBackup.remove(0);
                otpremnicaCurrentRow = manager.AddOtpremnicaBillItems(i, dodano, sheet, b,shouldPrint);
                dodano++;
            }
        }
        dodano = 0;
        // manager.AddPageNumber(sheet, i, pageNum);
    }

    private void AddItemsToSheet(int i, Sheet sheet, int pageNum, ExcelManager manager, Workbook wb, BusinessPartner bp) {
        int currentRow = 0, dodano = 0;

        List<Double> data = new ArrayList<Double>();
        if (i == 1) {
            while ((dodano <= 9) && (indexOfData < items.size())) {

                BillItem b = items.get(indexOfData++);
                itemsBackup.add(b);

                totalSum = totalSum.add(b.getPricePerPart().multiply(BigDecimal.valueOf((double) b.getParts()))).setScale(2, RoundingMode.HALF_UP);
                BigDecimal totalPriceKN = b.getTotalPrice().multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
                totalSumKn = totalSumKn.add(totalPriceKN).setScale(2, RoundingMode.HALF_UP);                

                data = manager.AddBillItems(i, dodano, sheet, b, exchangeRate,bp.getPrintInd());
                currentRow = (int) ((double) data.get(0));
                bolzenKom += (int) ((double) data.get(1));
                bolzenCijena += (double) data.get(2);
                welleKom += (int) ((double) data.get(3));
                welleCijena += (double) data.get(4);
                totalKom += b.getParts();
                dodano++;
            }
            WriteCalculatedData(sheet, currentRow, i, pageNum, manager, wb,bp);

        } else {
            manager.AddMissingData(sheet, totalSum, totalSumKn);
            while ((dodano <= 9) && (indexOfData < items.size())) {
                BillItem b = items.get(indexOfData++);
                itemsBackup.add(b);

                totalSum = totalSum.add(b.getPricePerPart().multiply(BigDecimal.valueOf((double) b.getParts()))).setScale(2, RoundingMode.HALF_UP);
                BigDecimal totalPriceKN = b.getTotalPrice().multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
                totalSumKn = totalSumKn.add(totalPriceKN).setScale(2, RoundingMode.HALF_UP);

                data = manager.AddBillItems(i, dodano, sheet, b, exchangeRate,bp.getPrintInd());
                currentRow = (int) ((double) data.get(0));
                bolzenKom += (int) ((double) data.get(1));
                bolzenCijena += (double) data.get(2);
                welleKom += (int) ((double) data.get(3));
                welleCijena += (double) data.get(4);
                totalKom += b.getParts();
                dodano++;
            }
            WriteCalculatedData(sheet, currentRow, i, pageNum, manager, wb,bp);
        }
        dodano = 0;
        currentRow = 0;
    }

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed

        if (billChanged) {
            Object[] options = {"Da", "Ne"};

            int n = JOptionPane.showOptionDialog(null, "Želite li snimiti trenutni račun?", "Izlaz", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 0) {
                if (billNumberTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Unesite broj računa.", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                _btnSaveBillActionPerformed(null);
            }

        }
        this.dispose();
		}//GEN-LAST:event_cancelBtnActionPerformed

    private void addBillItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBillItemBtnActionPerformed

        BillItemsJDialog newDlg = new BillItemsJDialog(null, true);
        newDlg.setAlreadySelectedItems(items);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        newDlg.setSize(dim.width, (dim.height - 100));
        newDlg.setLocation(0, 50);
        newDlg.setVisible(true);
        //dohvati vrijednosti koje su unesene u ovaj dialog  
        if (newDlg.getSelectedBillItem() != null) {
            BillItem bi = newDlg.getSelectedBillItem();
            DesignManager manager = new DesignManager();
            stefan.data.Design design = manager.GetDesignsByDBId(bi.getDesignId());
            bi.CalculatePrice(manager.mapData(design)); 
            bi.setItemOrderNumber(items.size() + 1);
                      
            items.add(bi);
           // Collections.sort(items, new OrderNumberComparator());
            sortByComboActionPerformed(null);
            updateTotalPrice();
            
            this.firePropertyChange("items", null, null);
            ItemsTable.getSelectionModel().setSelectionInterval(0, items.size()-1);
            ItemsTable.scrollRectToVisible(new Rectangle(ItemsTable.getCellRect(items.size()-1,0, true)));
            if (!items.isEmpty()) {
                deleteBtn.setEnabled(true);
                saveBill.setEnabled(true);
            }
            billChanged = true;
        }
    }//GEN-LAST:event_addBillItemBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed

        int rowIndex = ItemsTable.getSelectedRow();
        if (rowIndex != -1) {
                        
            BillItem itemToRemove = items.get(rowIndex);            
            List<BillItem> tempItems = ObservableCollections.observableList(new ArrayList<BillItem>());
            for (BillItem bi : items) {
                BillItem p = copyBillItem(bi);    
                if (p.getItemOrderNumber() > itemToRemove.getItemOrderNumber())
                {
                    p.setItemOrderNumber(p.getItemOrderNumber()-1);
                    tempItems.add(p);
                }  
                else if (p.getItemOrderNumber() < itemToRemove.getItemOrderNumber())
                {
                    tempItems.add(p);
                }         
            }
            items.clear();
            items = tempItems;            
            
            updateTotalPrice();
            this.firePropertyChange("items", null, null);
            if (items.isEmpty()) {
                deleteBtn.setEnabled(false);
                saveBill.setEnabled(false);
            }
            billChanged = true;
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void _btnSaveBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__btnSaveBillActionPerformed
        if (billNumberTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Unesite broj računa.", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
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

        Bill bill = new Bill();
        bill.setBillNumber(billNumberTextField.getText());
        bill.setDate(jXDatePicker1.getDate());
        bill.setBillitemsList(items);
        bill.setBusinessPartnerId(bp.getId());

        int index = cbxSavedBills.getSelectedIndex();
        if (index != -1 && index != 0) {
            bill.setIdBill(bills.get(index).getIdBill());
        }

        BillManager billManager = new BillManager();
        billManager.SaveBill(bill);
        refreshBillList();
        cbxSavedBills.setSelectedIndex(bills.size() - 1);
        billChanged = false;
    }//GEN-LAST:event__btnSaveBillActionPerformed

    private void _btnDeleteBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__btnDeleteBillActionPerformed
        int index = cbxSavedBills.getSelectedIndex();
        if (index != -1 && index != 0) {
            BillManager manager = new BillManager();
            manager.deleteBill(bills.get(index).getIdBill());
            bills.remove(index);
            this.firePropertyChange("bills", null, null);
            cbxSavedBills.setSelectedIndex(0);
            billChanged = false;
        }
    }//GEN-LAST:event__btnDeleteBillActionPerformed

    private void cbxSavedBillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSavedBillsActionPerformed

        int index = cbxSavedBills.getSelectedIndex();
        if (index != -1 && index != 0) {       
        
            _btnDeleteBill.setEnabled(true);
            items.clear();
            this.firePropertyChange("items", null, null);
            billNumberTextField.setText(bills.get(index).getBillNumber());
            jXDatePicker1.setDate(bills.get(index).getDate());
            
            Integer businessPartnerId = bills.get(index).getBusinessPartnerId();
            BusinessPartner selectedPartner = null;
            for(BusinessPartner p : bpItems){
                if(p.getId() == businessPartnerId){
                    selectedPartner = p;
                    break;
                }
            }
            if(selectedPartner != null){
                cbxZaFirmu.setSelectedItem(selectedPartner);
            }
            
            
            for (BillItem bi : bills.get(index).getBillitemsList()) {
                BillItem p = copyBillItem(bi); 
                items.add(p);
            }

            if (items.size() > 0) {
                deleteBtn.setEnabled(true);
                saveBill.setEnabled(true);
            } else {
                deleteBtn.setEnabled(false);
                saveBill.setEnabled(false);
            }
            billChanged = false;
            this.firePropertyChange("items", null, null);
        } else {
            _btnDeleteBill.setEnabled(false);
            clearAllFields();
        }
    }//GEN-LAST:event_cbxSavedBillsActionPerformed

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        billChanged = true;
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    private void billNumberTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_billNumberTextFieldKeyTyped
        billChanged = true;
    }//GEN-LAST:event_billNumberTextFieldKeyTyped

private void sortByComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortByComboActionPerformed
   
        switch(sortByCombo.getSelectedIndex()){
            case 0: Collections.sort(items, new DesignNumberComparator()); break;
            case 1: Collections.sort(items, new OrderNumberComparator()); break;  
            case 2: Collections.sort(items, new PackageNumberComparator()); break;
            case 3: Collections.sort(items, new TotalPriceComparator()); break;
            case 4: Collections.sort(items, new ItemOrderNumberComparator()); break;
        }
        this.firePropertyChange("items", null, null);
}//GEN-LAST:event_sortByComboActionPerformed

private void cbxZaFirmuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxZaFirmuActionPerformed
billChanged = true;
}//GEN-LAST:event_cbxZaFirmuActionPerformed

    private void clearAllFields() {
        items.clear();
        this.firePropertyChange("items", null, null);
        deleteBtn.setEnabled(false);
        jXDatePicker1.setDate(new Date());
        saveBill.setEnabled(false);
        billNumberTextField.setText("");
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
            java.util.logging.Logger.getLogger(NewBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                NewBill dialog = new NewBill(new javax.swing.JFrame(), true);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ItemsTable;
    private javax.swing.JButton _btnDeleteBill;
    private javax.swing.JButton _btnSaveBill;
    private javax.swing.JButton addBillItemBtn;
    private javax.swing.JTextField billNumberTextField;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox cbxSavedBills;
    private javax.swing.JComboBox cbxZaFirmu;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel priceLbl;
    private javax.swing.JButton saveBill;
    private javax.swing.JComboBox sortByCombo;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the items
     */
    public List<BillItem> getItems() {
        return items;
    }

    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        //TableModel model = (TableModel)e.getSource();
        //String columnName = model.getColumnName(column);
        // Object data = model.getValueAt(row, column);    
        if (row != -1 && column == 8 || column ==9) {
            List<BillItem> tempItems = ObservableCollections.observableList(new ArrayList<BillItem>());
            for (BillItem bi : items) {
                BillItem p = copyBillItem(bi);             
                tempItems.add(p);
            }
            items.clear();
            items = tempItems;
            this.firePropertyChange("items", null, null);
        }

        billChanged = true;
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice = new BigDecimal("0.0000");
        for (BillItem bi : items) {
            totalPrice = totalPrice.add(bi.getTotalPrice());
        }
        priceLbl.setText(totalPrice.setScale(2, RoundingMode.HALF_UP).toPlainString().replace('.', ','));
    }
    private BigDecimal totalPrice = new BigDecimal("0.0000");

    /**
     * @param items the items to set
     */
    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    private boolean ShowSaveFileDialog() {

        JFileChooser chooser = new JFileChooser("C:\\doo\\fakture\\");
        String fName = billNumberTextField.getText().trim() + "-F";
        chooser.setSelectedFile(new File(fName));
        int rVal = chooser.showSaveDialog(null);

        if (rVal == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getName();
            filePath = chooser.getCurrentDirectory().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean HasPlace(int currentRow) {
        if ((64 - currentRow) >= 16) {
            return true;
        }
        return false;
    }

    private void WriteCalculatedData(Sheet sheet, int currentRow, int i, int pageNum, ExcelManager manager, Workbook wb, BusinessPartner bp) {
        //nije zadnja stranica
        if (i != pageNum) {
            manager.AddSum(sheet, totalSum, totalSumKn, currentRow);
        } else {
            if (i != 1) {
                manager.AddSum(sheet, totalSum, totalSumKn, currentRow);
            }

            if (HasPlace(currentRow)) {
                manager.AddTotalSum(sheet, totalSum, totalSumKn, currentRow);
                manager.AddAditionalData(sheet, bolzenKom, bolzenCijena, welleKom, welleCijena, totalKom);
                manager.WriteFooter(currentRow + 1, sheet, totalSum, bp);
            } else {
                manager.AddSum(sheet, totalSum, totalSumKn, currentRow);
                Sheet newSheet = manager.CreateNewBillSheet(wb, i,
                        jXDatePicker1.getDate().toLocaleString().substring(0, 11),
                        billNumberTextField.getText().trim(), false,bp);

                manager.AddMissingData(newSheet, totalSum, totalSumKn);
                manager.AddTopAndBottomBorder(newSheet);
                manager.AddTotalSum(newSheet, totalSum, totalSumKn, 24);
                manager.AddAditionalData(newSheet, bolzenKom, bolzenCijena, welleKom, welleCijena, totalKom);
                manager.WriteFooter(26, newSheet, totalSum, bp);
                pageNumChanged = true;
            }
        }
    }

    /**
     * @return the bills
     */
    public List<Bill> getBills() {
        return bills;
    }

     /**
     * @return the bpItems
     */
    public List<BusinessPartner> getBpItems() {
        return bpItems;
    }
    /**
     * @param bills the bills to set
     */
    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
    
    /**
     * @param bpItems the bpItems to set
     */
    public void setBpItems(List<BusinessPartner> bpItems) {
        this.bpItems = bpItems;
    }

    private void CreateOtpremnica(BusinessPartner bp) throws FileNotFoundException, IOException {
        int pageNum = 1;
        if (itemsBackup.size() > 13) {
            pageNum = (itemsBackup.size() / 14) + 1;
        } else {
            pageNum = 1;
        }

        Collections.sort(itemsBackup, new PackageNumberComparator());
        Workbook workbook = new HSSFWorkbook();
        ExcelManager manager = new ExcelManager(workbook);
        FileOutputStream file = manager.CreateNewFile("Otpremnica", filePath);

        for (int i = 1; i <= pageNum; i++) {
            AddItemsToOtpremnicaSheet(i, manager.CreateNewOtpremnicaSheet(workbook, i - 1, billDate, billNumberTextField.getText().trim(),bp), pageNum, manager,bp.getPrintInd());
        }
        //ima mjesta - dodaj na isti sheet
        if ((64 - otpremnicaCurrentRow) >= 6) {
            manager.WriteOtpremnicaFooter(otpremnicaCurrentRow + 1, workbook.getSheetAt(pageNum - 1));
        } //nema mjesta - dodaj noci sheet
        else {
            pageNum++;
            manager.AddTopAndBottomBorder(manager.CreateNewOtpremnicaSheet(workbook, pageNum - 1, billDate, billNumberTextField.getText().trim(),bp));
            manager.WriteOtpremnicaFooter(25, workbook.getSheetAt(pageNum - 1));
        }

        //dodaj page numbers
        WriteAllPageNumbers(pageNum, manager, workbook);
        workbook.write(file);
        file.close();
    }

    private void WriteAllPageNumbers(int pageNum, ExcelManager manager, Workbook workbook) {

        for (int i = 1; i <= pageNum; i++) {
            Sheet sheet = workbook.getSheetAt(i - 1);
            manager.AddPageNumber(sheet, i, pageNum);
        }
    }
}
