package stefanpresentationlayer.dialogs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.application.Action;
import org.jdesktop.observablecollections.ObservableCollections;
import stefan.business.*;
import stefan.business.objects.*;
import stefanpresentationlayer.MyTableCellRenderer;


public class DesignJDialog extends javax.swing.JDialog {

    private List<Design> designs = ObservableCollections.observableList(new ArrayList<stefan.business.objects.Design>());
    private int parts;
    private Design selectedDesign;
    private boolean isShippingDateRequired; 
    private Date shippingDate;
    private static Date previousShippingDate;

    /** Creates new form DesignJDialog */
    public DesignJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        setTitle("Odabir Nacrta");
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        long start = System.currentTimeMillis();
        MyTableCellRenderer rendrer = new MyTableCellRenderer();

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(rendrer);       
        }

        DesignManager manager = new DesignManager();
        List<Design> temp = manager.GetTop100();
        if (temp.size() > 100) {
            designs = temp.subList(0, 100);
        } else {
            designs = temp;
        }
        long start1 = System.currentTimeMillis();
        long rez1 = start1 - start;
        this.firePropertyChange("designs", null, null);
        long start2 = System.currentTimeMillis();
        long rez2 = start2 - start1;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        designNumTextField = new javax.swing.JTextField();
        designClassTextField = new javax.swing.JTextField();
        designIdentityTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        createDesignButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        editDesignButton = new javax.swing.JButton();
        btnDeleteDesign = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getResourceMap(DesignJDialog.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        designNumTextField.setText(resourceMap.getString("designNumTextField.text")); // NOI18N
        designNumTextField.setName("designNumTextField"); // NOI18N
        designNumTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                designNumTextFieldKeyReleased(evt);
            }
        });

        designClassTextField.setText(resourceMap.getString("designClassTextField.text")); // NOI18N
        designClassTextField.setName("designClassTextField"); // NOI18N
        designClassTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                designClassTextFieldKeyReleased(evt);
            }
        });

        designIdentityTextField.setText(resourceMap.getString("designIdentityTextField.text")); // NOI18N
        designIdentityTextField.setName("designIdentityTextField"); // NOI18N
        designIdentityTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                designIdentityTextFieldKeyReleased(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getActionMap(DesignJDialog.class, this);
        jButton1.setAction(actionMap.get("odaberiNacrtClick")); // NOI18N
        jButton1.setText(resourceMap.getString("odaberiNacrtButton.text")); // NOI18N
        jButton1.setName("odaberiNacrtButton"); // NOI18N

        createDesignButton.setText(resourceMap.getString("dodajNacrt.text")); // NOI18N
        createDesignButton.setName("dodajNacrt"); // NOI18N
        createDesignButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createDesignButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setRowHeight(18);
        jTable1.setRowMargin(4);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${designs}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designNumber}"));
        columnBinding.setColumnName("Design Number");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${revision}"));
        columnBinding.setColumnName("Revision");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${designIdentity}"));
        columnBinding.setColumnName("Design Identity");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${classMark}"));
        columnBinding.setColumnName("Class Mark");
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
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${date}"));
        columnBinding.setColumnName("Date");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs1}"));
        columnBinding.setColumnName("Pcs1");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs2}"));
        columnBinding.setColumnName("Pcs2");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs3}"));
        columnBinding.setColumnName("Pcs3");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs4}"));
        columnBinding.setColumnName("Pcs4");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs5}"));
        columnBinding.setColumnName("Pcs5");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs6}"));
        columnBinding.setColumnName("Pcs6");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs10}"));
        columnBinding.setColumnName("Pcs10");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs15}"));
        columnBinding.setColumnName("Pcs15");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs20}"));
        columnBinding.setColumnName("Pcs20");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs30}"));
        columnBinding.setColumnName("Pcs30");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs40}"));
        columnBinding.setColumnName("Pcs40");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs50}"));
        columnBinding.setColumnName("Pcs50");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs100}"));
        columnBinding.setColumnName("Pcs100");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs200}"));
        columnBinding.setColumnName("Pcs200");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs500}"));
        columnBinding.setColumnName("Pcs500");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pcs1000}"));
        columnBinding.setColumnName("Pcs1000");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title21")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title10")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title13")); // NOI18N
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable1.columnModel.title15")); // NOI18N
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("jTable1.columnModel.title17")); // NOI18N
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(11).setHeaderValue(resourceMap.getString("jTable1.columnModel.title20")); // NOI18N
        jTable1.getColumnModel().getColumn(12).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(12).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(13).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(13).setHeaderValue(resourceMap.getString("jTable1.columnModel.title9")); // NOI18N
        jTable1.getColumnModel().getColumn(14).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(14).setHeaderValue(resourceMap.getString("jTable1.columnModel.title11")); // NOI18N
        jTable1.getColumnModel().getColumn(15).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(15).setHeaderValue(resourceMap.getString("jTable1.columnModel.title14")); // NOI18N
        jTable1.getColumnModel().getColumn(16).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(16).setHeaderValue(resourceMap.getString("jTable1.columnModel.title16")); // NOI18N
        jTable1.getColumnModel().getColumn(17).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(17).setHeaderValue(resourceMap.getString("jTable1.columnModel.title18")); // NOI18N
        jTable1.getColumnModel().getColumn(18).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(18).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(19).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(19).setHeaderValue(resourceMap.getString("jTable1.columnModel.title12")); // NOI18N
        jTable1.getColumnModel().getColumn(20).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(20).setHeaderValue(resourceMap.getString("jTable1.columnModel.title19")); // NOI18N
        jTable1.getColumnModel().getColumn(21).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(21).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N
        jTable1.getColumnModel().getColumn(22).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(22).setHeaderValue(resourceMap.getString("jTable1.columnModel.title22")); // NOI18N
        jTable1.getColumnModel().getColumn(23).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(23).setHeaderValue(resourceMap.getString("jTable1.columnModel.title23")); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        editDesignButton.setAction(actionMap.get("EditExistingDesign")); // NOI18N
        editDesignButton.setText(resourceMap.getString("editDesignButton.text")); // NOI18N
        editDesignButton.setName("editDesignButton"); // NOI18N

        btnDeleteDesign.setAction(actionMap.get("btnDeleteDesignAction")); // NOI18N
        btnDeleteDesign.setText(resourceMap.getString("btnDeleteDesign.text")); // NOI18N
        btnDeleteDesign.setName("btnDeleteDesign"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(designNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(designClassTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(designIdentityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4)))
                                .addGap(116, 116, 116))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(createDesignButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editDesignButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteDesign)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 680, Short.MAX_VALUE)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(designNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(designClassTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(designIdentityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createDesignButton)
                    .addComponent(editDesignButton)
                    .addComponent(jButton1)
                    .addComponent(btnDeleteDesign))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void designNumTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_designNumTextFieldKeyReleased
  
        DesignManager manager = new DesignManager();

        List<Design> temp;
        if (designIdentityTextField.getText().compareTo("") == 0 && designClassTextField.getText().compareTo("") == 0) {
            temp = manager.GetDesignsByNumber(designNumTextField.getText());
        } else {
            temp = manager.GetDesignsById(designNumTextField.getText(), designIdentityTextField.getText(), designClassTextField.getText());
        }
        if (temp.size() > 100) {
            designs = temp.subList(0, 100);
        } else {
            designs = temp;
        }
        this.firePropertyChange("designs", null, null);
    }//GEN-LAST:event_designNumTextFieldKeyReleased

    private void designClassTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_designClassTextFieldKeyReleased
        DesignManager manager = new DesignManager();
        List<Design> temp = manager.GetDesignsById(designNumTextField.getText(), designIdentityTextField.getText(), designClassTextField.getText());
        if (temp.size() > 100) {
            designs = temp.subList(0, 100);
        } else {
            designs = temp;
        }
        this.firePropertyChange("designs", null, null);
    }//GEN-LAST:event_designClassTextFieldKeyReleased

    private void designIdentityTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_designIdentityTextFieldKeyReleased
        DesignManager manager = new DesignManager();
        List<Design> temp = manager.GetDesignsById(designNumTextField.getText(), designIdentityTextField.getText(), designClassTextField.getText());
        if (temp.size() > 100) {
            designs = temp.subList(0, 100);
        } else {
            designs = temp;
        }
        this.firePropertyChange("designs", null, null);
    }//GEN-LAST:event_designIdentityTextFieldKeyReleased

    private void createDesignButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createDesignButtonActionPerformed
        // TODO add your handling code here:

        EditDesignJDialog dodajNacrtDialog = new EditDesignJDialog(null, true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dodajNacrtDialog.setLocation((dim.width / 2) - (dodajNacrtDialog.getSize().width / 2) , 50);
        dodajNacrtDialog.CreateNew();
        dodajNacrtDialog.setVisible(true);
        this.setVisible(true);
        RefreshTableData();
    }//GEN-LAST:event_createDesignButtonActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        int keyCode = evt.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            evt.consume();
            odaberiNacrtClick();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            odaberiNacrtClick();
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(DesignJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DesignJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DesignJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesignJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DesignJDialog dialog = new DesignJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnDeleteDesign;
    private javax.swing.JButton createDesignButton;
    private javax.swing.JTextField designClassTextField;
    private javax.swing.JTextField designIdentityTextField;
    private javax.swing.JTextField designNumTextField;
    private javax.swing.JButton editDesignButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the designs
     */
    public List<Design> getDesigns() {
        return designs;
    }

    public int getParts() {
        return parts;
    }

    public Date getShippingDate() {
        return shippingDate;
    }
    
    /**
     * @param designs the designs to set
     */
    public void setDesigns(List<Design> designs) {
        this.designs = designs;
    }

    public Design getSelectedDesign() {
        return selectedDesign;
    }

    @Action
    public void odaberiNacrtClick() {
        int rowIndex = jTable1.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Odaberite nacrt");
        } else {
            selectedDesign = designs.get(rowIndex);
            boolean isAnswerOk = false;

            while (!isAnswerOk) {
                String partsString = JOptionPane.showInputDialog(null, "Koliko komada?", "Broj komada", JOptionPane.QUESTION_MESSAGE);
                if (partsString == null) {
                    selectedDesign = null;
                    break;
                } else {
                    try {
                        parts = Integer.valueOf(partsString);
                        if (parts <= 0) {
                            JOptionPane.showMessageDialog(null, "Unesite više od 0 komada");
                        } else {
                            stefan.business.PresentationHelper helper = new stefan.business.PresentationHelper(selectedDesign, parts, null);
                            if (helper.getPricePerPart().compareTo(BigDecimal.ZERO) == -1) {
                                JOptionPane.showMessageDialog(null, "Za odabrani nacrt i kolicinu: " + parts + " ne postoji definirana cijena. Uredite odabrani nacrta i ponovno ga dodajte!");
                                EditDesignJDialog d = new EditDesignJDialog(null, true);

                                d.setResizable(false);
                                d.setLocation(0, 50);
                                List<Design> list = new ArrayList<Design>();
                                list.add(selectedDesign);
                                d.LoadData(list);
                                d.setVisible(true);
                                designNumTextFieldKeyReleased(null);
                                return;

                            }
                            
                            if (!this.isShippingDateRequired){
                                isAnswerOk = true;
                                this.dispose();
                            }
                            else {                               
                                shippingDate = odaberiDatum();
                                previousShippingDate = shippingDate;
                                isAnswerOk = true;
                                this.dispose();          
                            }                                                 
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Unesite samo brojeve");
                    }
                }
            }
        }
    }
    
    
    private Date odaberiDatum(){
        JXDatePicker jd = new JXDatePicker();
        jd.getMonthView().setPreferredColumnCount(2);
        jd.getMonthView().setPreferredRowCount(2);
        jd.getMonthView().setFirstDayOfWeek(2);
        jd.getMonthView().setShowingWeekNumber(true);
        jd.getMonthView().getSelectionModel().setMinimalDaysInFirstWeek(4);
        jd.setFormats(new String[] {"d.M.yyyy."});        
        if (previousShippingDate != null){
            jd.setDate(previousShippingDate);
        }
        
        String message ="Odaberite novi datum isporuke:\n";
        Object[] params = {message,jd};       
        
        while (true) {
            JOptionPane.showConfirmDialog(null,params,"Datum Isporuke", JOptionPane.PLAIN_MESSAGE);
            Date newShippingDate = jd.getDate();
            if (newShippingDate != null) {
                return newShippingDate;
            }
            JOptionPane.showMessageDialog(null, "Unesite ispravan datum isporuke");
        }
    }
    
    public void setIsShippingDateRequired(boolean isShippingDateRequired){
        this.isShippingDateRequired = isShippingDateRequired;
    }            

    private void RefreshTableData() {
        designNumTextFieldKeyReleased(null);
    }

    @Action
    public void EditExistingDesign() {
        int rowIndex = jTable1.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Odaberite nacrt");
        } else {
            selectedDesign = designs.get(rowIndex);
            EditDesignJDialog editNacrtDialog = new EditDesignJDialog(null, true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            editNacrtDialog.setLocation((dim.width / 2) - (editNacrtDialog.getSize().width / 2) , 50);  
            List<Design> selectedDesigns = new ArrayList<Design>();
            selectedDesigns.add(selectedDesign);
            editNacrtDialog.LoadData(selectedDesigns);
            editNacrtDialog.setVisible(true);
            this.setVisible(true);
            selectedDesign = null;
            RefreshTableData();
        }
    }

    @Action
    public void btnDeleteDesignAction() {
        int rowIndex = jTable1.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Odaberite nacrt");
        } else {
            Design deleted = designs.get(rowIndex);
            DesignManager manager = new DesignManager();
            manager.DeleteDesign(deleted);
            RefreshTableData();
        }
    }
}