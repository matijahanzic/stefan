/*
 * StefanPresentationLayerView.java
 */
package stefanpresentationlayer;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import stefanpresentationlayer.dialogs.AllOrdersDialog;
import stefanpresentationlayer.dialogs.EditDesignJDialog;
import stefanpresentationlayer.dialogs.ImportDesignsFromExcelJDialog;
import stefanpresentationlayer.dialogs.MaterialManagmentJDialog;
import stefanpresentationlayer.dialogs.NewBill;
import stefanpresentationlayer.dialogs.NewOrderJDialog;
import stefanpresentationlayer.dialogs.PriceChangeJDialog;
import stefanpresentationlayer.dialogs.PriceManagmentJDialog;

/**
 * The application's main frame.
 */
public class StefanPresentationLayerView extends FrameView {

 

    public StefanPresentationLayerView(SingleFrameApplication app) {
        super(app);
        initComponents();      


        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = StefanPresentationLayerApp.getApplication().getMainFrame();
            aboutBox = new StefanPresentationLayerAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        StefanPresentationLayerApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        newOrderBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        allOrdersBtn = new javax.swing.JButton();
        btnDesigns = new javax.swing.JButton();
        btnChangeDesignPrice = new javax.swing.JButton();
        btnDesignsFromExcel = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getResourceMap(StefanPresentationLayerView.class);
        newOrderBtn.setIcon(resourceMap.getIcon("newOrderBtn.icon")); // NOI18N
        newOrderBtn.setText(resourceMap.getString("newOrderBtn.text")); // NOI18N
        newOrderBtn.setName("newOrderBtn"); // NOI18N
        newOrderBtn.setSelectedIcon(resourceMap.getIcon("newOrderBtn.selectedIcon")); // NOI18N
        newOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        allOrdersBtn.setIcon(resourceMap.getIcon("allOrdersBtn.icon")); // NOI18N
        allOrdersBtn.setText(resourceMap.getString("allOrdersBtn.text")); // NOI18N
        allOrdersBtn.setName("allOrdersBtn"); // NOI18N
        allOrdersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allOrdersBtnActionPerformed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getActionMap(StefanPresentationLayerView.class, this);
        btnDesigns.setAction(actionMap.get("materialBtnActionPerformed")); // NOI18N
        btnDesigns.setIcon(resourceMap.getIcon("btnDesigns.icon")); // NOI18N
        btnDesigns.setText(resourceMap.getString("btnDesigns.text")); // NOI18N
        btnDesigns.setName("btnDesigns"); // NOI18N
        btnDesigns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesignsActionPerformed(evt);
            }
        });

        btnChangeDesignPrice.setAction(actionMap.get("materialBtnActionPerformed")); // NOI18N
        btnChangeDesignPrice.setIcon(resourceMap.getIcon("btnChangeDesignPrice.icon")); // NOI18N
        btnChangeDesignPrice.setText(resourceMap.getString("btnChangeDesignPrice.text")); // NOI18N
        btnChangeDesignPrice.setName("btnChangeDesignPrice"); // NOI18N
        btnChangeDesignPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeDesignPriceActionPerformed(evt);
            }
        });

        btnDesignsFromExcel.setIcon(resourceMap.getIcon("btnDesignsFromExcel.icon")); // NOI18N
        btnDesignsFromExcel.setText(resourceMap.getString("btnDesignsFromExcel.text")); // NOI18N
        btnDesignsFromExcel.setName("btnDesignsFromExcel"); // NOI18N
        btnDesignsFromExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesignsFromExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(btnDesigns, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChangeDesignPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDesignsFromExcel))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(newOrderBtn)
                        .addGap(18, 18, 18)
                        .addComponent(allOrdersBtn))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newOrderBtn)
                    .addComponent(allOrdersBtn))
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(32, 32, 32)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDesigns, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangeDesignPrice)
                    .addComponent(btnDesignsFromExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1194, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1024, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void newOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderBtnActionPerformed
            
        NewOrderJDialog orderDialog= new NewOrderJDialog(this.getFrame(), true); 
              
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();     
            orderDialog.setSize(dim.width, (dim.height - 100));     
            orderDialog.setLocation(0, 50);        
            orderDialog.setVisible(true);   
            //dohvati vrijednosti koje su unesene u ovaj dialog      
            //this.firePropertyChange("items", null, null);    
  
    }//GEN-LAST:event_newOrderBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         NewBill newDlg= new NewBill(this.getFrame(), true);          
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         newDlg.setSize(dim.width, (dim.height - 100)); 
         newDlg.setLocation(0, 50);     
         newDlg.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void allOrdersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allOrdersBtnActionPerformed
        AllOrdersDialog orderDialog= new AllOrdersDialog(this.getFrame(), true); 
       
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
        orderDialog.setSize(dim.width, (dim.height - 100)); 
        orderDialog.setLocation(0, 50);       
        orderDialog.setVisible(true);   
    }//GEN-LAST:event_allOrdersBtnActionPerformed

    private void btnDesignsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesignsActionPerformed
        EditDesignJDialog dialog= new EditDesignJDialog(this.getFrame(), true);    
        dialog.setResizable(false);
        dialog.setLocation(0, 50);       
        dialog.setVisible(true);   
    }//GEN-LAST:event_btnDesignsActionPerformed

    private void btnChangeDesignPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeDesignPriceActionPerformed
        PriceChangeJDialog dialog=new PriceChangeJDialog(null, true);
        dialog.setResizable(false);
        dialog.setLocation(0, 50);       
        dialog.setVisible(true);
    }//GEN-LAST:event_btnChangeDesignPriceActionPerformed

private void btnDesignsFromExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesignsFromExcelActionPerformed

    if (ShowImportFileDialog()) {
        String filePath =  importFilePath + "\\" + importFileName;
        ImportDesignsFromExcelJDialog designDialog = new ImportDesignsFromExcelJDialog(null, true, filePath);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
        designDialog.setSize(dim.width, (dim.height - 100)); 
        designDialog.setLocation(0, 50);  
        designDialog.setVisible(true);
    }
    else
    {
        return;
    }
    
}//GEN-LAST:event_btnDesignsFromExcelActionPerformed

private String importFilePath;
private String importFileName;
private boolean ShowImportFileDialog() {

        JFileChooser chooser = new JFileChooser("C:\\doo\\fakture\\");  
        int rVal = chooser.showOpenDialog(this.getFrame());

        if (rVal == JFileChooser.APPROVE_OPTION) {
            importFileName = chooser.getSelectedFile().getName();
            importFilePath = chooser.getCurrentDirectory().toString();
            return true;
        } else {
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allOrdersBtn;
    private javax.swing.JButton btnChangeDesignPrice;
    private javax.swing.JButton btnDesigns;
    private javax.swing.JButton btnDesignsFromExcel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton newOrderBtn;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;

    /**
     * @return the items
     */
  
}