/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewMaterialJDialog.java
 *
 * Created on 09.02.2012., 16:37:51
 */
package stefanpresentationlayer.dialogs;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.application.Action;
import stefan.business.MaterialManager;
import stefan.business.objects.MaterialDetails;

/**
 *
 * @author Robert
 */
public class EditeMaterialJDialog extends javax.swing.JDialog {

    private MaterialDetails selectedMaterial;
    DecimalFormat priceFormatter = new DecimalFormat("#0.##");

    public EditeMaterialJDialog(java.awt.Frame parent, boolean modal){
        super(parent,modal);
        initComponents();
        setTitle("Promjena Materijala");
    }
    public EditeMaterialJDialog(java.awt.Frame parent, boolean modal, int materialId) {
        super(parent, modal);
        initComponents();
        setTitle("Promjena Materijala");
        MaterialManager manager = new MaterialManager();
        selectedMaterial = manager.GetMaterialDetailsById(materialId);
        FillData();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        editMaterialNameInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        editMaterialDensityInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        editMaterijalNiklanjeDaRadio = new javax.swing.JRadioButton();
        editMaterialNiklanjeNeRadio = new javax.swing.JRadioButton();
        editMaterialBtn = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getResourceMap(EditeMaterialJDialog.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        editMaterialNameInput.setText(resourceMap.getString("editMaterialNameInput.text")); // NOI18N
        editMaterialNameInput.setName("editMaterialNameInput"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        editMaterialDensityInput.setText(resourceMap.getString("editMaterialDensityInput.text")); // NOI18N
        editMaterialDensityInput.setName("editMaterialDensityInput"); // NOI18N
        editMaterialDensityInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editMaterialDensityInputKeyTyped(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        buttonGroup1.add(editMaterijalNiklanjeDaRadio);
        editMaterijalNiklanjeDaRadio.setText(resourceMap.getString("editMaterijalNiklanjeDaRadio.text")); // NOI18N
        editMaterijalNiklanjeDaRadio.setName("editMaterijalNiklanjeDaRadio"); // NOI18N

        buttonGroup1.add(editMaterialNiklanjeNeRadio);
        editMaterialNiklanjeNeRadio.setSelected(true);
        editMaterialNiklanjeNeRadio.setText(resourceMap.getString("editMaterialNiklanjeNeRadio.text")); // NOI18N
        editMaterialNiklanjeNeRadio.setName("editMaterialNiklanjeNeRadio"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getActionMap(EditeMaterialJDialog.class, this);
        editMaterialBtn.setAction(actionMap.get("editBtnClicked")); // NOI18N
        editMaterialBtn.setText(resourceMap.getString("editMaterialBtn.text")); // NOI18N
        editMaterialBtn.setName("editMaterialBtn"); // NOI18N

        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(editMaterialDensityInput, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(editMaterialNameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(editMaterialBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancel)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(editMaterijalNiklanjeDaRadio)
                        .addGap(32, 32, 32)
                        .addComponent(editMaterialNiklanjeNeRadio)
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(editMaterialNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(editMaterialDensityInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editMaterialNiklanjeNeRadio)
                            .addComponent(editMaterijalNiklanjeDaRadio))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancel)
                            .addComponent(editMaterialBtn)))
                    .addComponent(jLabel3))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editMaterialDensityInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editMaterialDensityInputKeyTyped
        ValidateTextField(evt);
    }//GEN-LAST:event_editMaterialDensityInputKeyTyped

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(EditeMaterialJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditeMaterialJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditeMaterialJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditeMaterialJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                EditeMaterialJDialog dialog = new EditeMaterialJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton editMaterialBtn;
    private javax.swing.JTextField editMaterialDensityInput;
    private javax.swing.JTextField editMaterialNameInput;
    private javax.swing.JRadioButton editMaterialNiklanjeNeRadio;
    private javax.swing.JRadioButton editMaterijalNiklanjeDaRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    private boolean IsMoreThenOneDotInField(JTextField field) {
        if (field.getText().contains(",")) {
            return true;
        } else {
            return false;
        }
    }

    private void ValidateTextField(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            super.processKeyEvent(evt);
        } else if ((",".indexOf(c) > -1) && (!IsMoreThenOneDotInField((JTextField) evt.getSource()))) {
            super.processKeyEvent(evt);
        } else {
            evt.consume();
            return;
        }
    }

    private void FillData() {
        editMaterialNameInput.setText(selectedMaterial.getMaterialName());
        editMaterialDensityInput.setText((priceFormatter.format(selectedMaterial.getDensity())));

        if (selectedMaterial.getNiklanje()) {
            editMaterijalNiklanjeDaRadio.setSelected(true);
        } else {
            editMaterialNiklanjeNeRadio.setSelected(true);
        }
    }

    @Action
    public void editBtnClicked() {
        if (editMaterialDensityInput.getText().length() == 0 || editMaterialNameInput.getText().length() == 0) {
            ShowFillAllMessage();
        } else {
            MaterialManager manager = new MaterialManager();
            Boolean niklanje = true;
            if (editMaterialNiklanjeNeRadio.isSelected()) {
                niklanje = false;
            }
            try {
                manager.UpdateMaterial(selectedMaterial.getIdMaterialDetails(), editMaterialNameInput.getText().trim(), BigDecimal.valueOf(Double.parseDouble(editMaterialDensityInput.getText().trim().replace(",", "."))), niklanje);
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }


        }
    }

    private void ShowFillAllMessage() {
        JOptionPane.showMessageDialog(null, "Popunite sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
