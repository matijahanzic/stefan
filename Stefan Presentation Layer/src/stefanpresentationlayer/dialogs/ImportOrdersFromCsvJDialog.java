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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import javax.swing.*;

import javax.swing.table.TableColumn;
import org.jdesktop.observablecollections.ObservableCollections;
import stefan.business.BusinessPartnerManager;
import stefan.business.DesignManager;
import stefan.business.OrderManager;
import stefan.business.QueryManager;
import stefan.business.objects.BusinessPartner;
import stefan.business.objects.Design;
import stefan.business.objects.ImportOrderItemDto;
import stefan.business.objects.Order;
import stefan.business.objects.OrderItem;
import stefan.data.Orderitems;
import stefan.data.Orders;
import stefanpresentationlayer.MyTableCellRenderer;

/**
 *
 * @author Administrator
 */
public class ImportOrdersFromCsvJDialog extends javax.swing.JDialog {

    private Order importOrder;
    public List<ImportOrderItemDto> importOrderItems = ObservableCollections.observableList(new ArrayList<ImportOrderItemDto>());
    private BigDecimal _total = BigDecimal.ZERO;
    public List<Integer> _deletedOrderItems = new ArrayList<Integer>();

    public List<ImportOrderItemDto> getImportOrderItems() {
        return importOrderItems;
    }

    public void setImportOrderItems(List<ImportOrderItemDto> importOrderItems) {
        this.importOrderItems = importOrderItems;
    }

    /** Creates new form ImportOrdersFromCsvJDialog */
    public ImportOrdersFromCsvJDialog(java.awt.Frame parent, String filePath) throws FileNotFoundException, IOException, NoSuchElementException, ParseException {
        super(parent, true);
        initComponents();

        setTitle("Učitavanje narudžbi iz csv");

        jXDatePickerDesignDate.setDate(new Date());
        jXDatePickerDesignDate.getMonthView().setFirstDayOfWeek(2);
        jXDatePickerDesignDate.getMonthView().setShowingWeekNumber(true);
        jXDatePickerDesignDate.getMonthView().getSelectionModel().setMinimalDaysInFirstWeek(4);


        MyTableCellRenderer rendrer = new MyTableCellRenderer();
        for (int i = 1; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(rendrer);
        }

        TableColumn tngColumn = jTable1.getColumnModel().getColumn(11);
        JComboBox<String> tngComboBox = new JComboBox<String>(new String[]{"T", "G", "N"});
        tngColumn.setCellEditor(new DefaultCellEditor(tngComboBox));


        ImportFromFile(filePath);
    }

    private void ImportFromFile(String filePath) throws FileNotFoundException, IOException, NoSuchElementException, ParseException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), java.nio.charset.StandardCharsets.UTF_8));
        String line;

        //Order order = new Order();
        importOrder = new Order();
        //Map<String, ImportOrderItemDto> itemMap = new HashMap<String, ImportOrderItemDto>();

        // Context-tracking variables for dynamic header mapping
        List<String> currentHeaders = null;
        String currentDescriptorType = "";
        String currentDescriptorSubtype = "";
        ImportOrderItemDto currentItemScope = null;
        Boolean _isDeleted = false;

        String _designCode = "FOPAC";

        DesignManager designManager = new DesignManager();
        OrderManager orderManager = new OrderManager();
        BusinessPartnerManager bpManager = new BusinessPartnerManager();
        List<BusinessPartner> bPartners = bpManager.getInternalBusinessPartners();
        Boolean _isNew = true;

        String deletedInvoicedItemsErrorMessages = null;


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
                    importOrder.setOrderNumber(rowData.get("order_number"));
                    brojNarudzbeText.setText(rowData.get("order_number"));
                } // 2. Order Address Row
                else if ("head".equals(rowType) && "delivery_address".equals(subType)) {

                    String bpName = rowData.get("name1");
                    String bpCity = CleanString(rowData.get("city"));

                    bpCity = bpCity.replace("ß", "ss");


                    for (BusinessPartner bPartner : bPartners) {
                        if (bPartner.getCity().equalsIgnoreCase(bpCity)) {
                            importOrder.setBusinessPartnerName(bPartner.getName());
                            importOrder.setBusinessPartnerId(bPartner.getId());
                            partnerText.setText(bPartner.getDisplayName());
                        }
                    }

                    if (importOrder.getBusinessPartnerId() == null) {
                        throw new NoSuchElementException("Nije pronađen poslovni partner naveden na narudžbi");
                    }

                    if (importOrder.getBusinessPartnerName().toLowerCase().startsWith("w")) {
                        _designCode = "WH";
                    }
                } // 3. Item Core Details Row
                else if ("position".equals(rowType) && "".equals(subType)) {

                    _isDeleted = false;

                    String posNum = tokens.get(2); // Always 5 characters at position index 2
                    String designNumber = CleanString(rowData.get("ZZNUMMER"));

                    if ("".equals(designNumber) && _designCode.equalsIgnoreCase("WH")) {
                        designNumber = CleanString(rowData.get("material_number"));
                        //WH broj nacrta upisuje u material_number jer ZZNUMBER ne postoji
                        //ti nacrti bi trebali biti 8 znamenkasti ali zasad ne dodajemo tu provjeru
                        if (designNumber == null) {
                            designNumber = "";
                        }
                    }


                    if (designNumber != null) {
                        String[] designParts = designNumber.split(" ");
                        designNumber = designParts[0];
                    }


                    String isDeleted = CleanString(rowData.get("is_deleted"));
                    if ("1".equalsIgnoreCase(isDeleted)) {
                        _isDeleted = true;

                        Orders existingOrder = orderManager.getOrderByNumberAndBussPartner(
                                importOrder.getOrderNumber(),
                                importOrder.getBusinessPartnerId());

                        if (existingOrder == null) {
                            continue;
                        }

                        Orderitems existingItem = null;
                        for (Orderitems oi : existingOrder.getOrderitemsList()) {
                            if (oi.getIdDesign().getDesignNumber().equals(designNumber) && oi.getPosition().equals(posNum)) {
                                existingItem = oi;
                                break;
                            }
                        }

                        Integer quantityDelivered = existingItem != null ? existingItem.getQuantityDelivered() : null;

                        if (quantityDelivered != null && quantityDelivered > 0) {
                            deletedInvoicedItemsErrorMessages = concatWithNewLine(
                                    deletedInvoicedItemsErrorMessages,
                                    "Stavka s pozicijom " + posNum
                                    + " i brojem nacrta " + designNumber
                                    + " je fakturirana u sustavu, a obrisana u CSV-u");

                            currentItemScope.setIndShouldImport(false);
                        }

                        if (existingItem != null) {
                            _deletedOrderItems.add(existingItem.getIdOrderItems());
                        }




                        continue;
                    }

                    ImportOrderItemDto importOrderItem = new ImportOrderItemDto();
                    OrderItem item = new OrderItem();
                    item.setPosition(posNum);

                    try {
                        NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
                        int quantity = nf.parse(rowData.get("quantity")).intValue();
                        item.setQuantityOrdered(quantity);
                        item.setQuantityDelivered(0);
                    } catch (ParseException e) {
                        //throw new ParseException("Količina nije ispravan cijeli broj", 0);
                        importOrderItem.setWarningText(ConcatWarning(importOrderItem.getWarningText(), "Količina u csv datoteci nije ispravan cijeli broj"));
                        importOrderItem.setIndShouldImport(false);
                    }


                    String rev = CleanString(rowData.get("rev_lev"));
                    String designName = CleanString(rowData.get("material_name"));
                    String designClassMark = CleanString(rowData.get("EXTWG"));

                    if (!"".equals(designNumber)) {

                        List<Design> designs = designManager.GetDesignsByNumberAndCode(designNumber, _designCode);

                        if (designs != null) {

                            for (Design design : designs) {
                                if (CleanString(design.getDesignNumber()).equalsIgnoreCase(designNumber)) {
                                    item.setDesign(design);
                                    item.setDesignId(design.getIdDesign());
                                }
                            }
                        }
                    }

                    if (item.getDesign() == null) {
                        importOrderItem.setIndShouldImport(false);
                        importOrderItem.setWarningText(ConcatWarning(importOrderItem.getWarningText(), "Nije pronađen dizajn " + _designCode + " sa brojem " + designNumber));
                    } else {
                        //WH ne sadrži reviziju
                        if (!_designCode.equalsIgnoreCase("WH") && !rev.equalsIgnoreCase(CleanString(item.getDesign().getRevision()))) {
                            importOrderItem.setWarningText(ConcatWarning(importOrderItem.getWarningText(), "Revizija nacrta u csv: " + rev + ", revizija upisanog nacrta: " + CleanString(item.getDesign().getRevision())));
                        }

                        if (!designName.equalsIgnoreCase(CleanString(item.getDesign().getName()))) {
                            importOrderItem.setWarningText(ConcatWarning(importOrderItem.getWarningText(), "Naziv nacrta u csv: " + designName + ", naziv upisanog nacrta: " + CleanString(item.getDesign().getName())));
                        }

                        //WH ne sadrži klasu nacrta
                        if (!_designCode.equalsIgnoreCase("WH") && !designClassMark.equalsIgnoreCase(CleanString(item.getDesign().getClassMark()))) {
                            importOrderItem.setWarningText(ConcatWarning(importOrderItem.getWarningText(), "Klasa nacrta u csv: " + designClassMark + ", klasa upisanog nacrta: " + CleanString(item.getDesign().getClassMark())));
                        }

                        stefan.business.PresentationHelper helper = new stefan.business.PresentationHelper(item.getDesign(), item.getQuantityOrdered(), null);

                        if (helper.getPricePerPart().compareTo(BigDecimal.ZERO) == -1) {
                            importOrderItem.setWarningText(ConcatWarning(importOrderItem.getWarningText(), "Za količinu " + item.getQuantityOrdered() + " ne postoji definirana cijena u nacrtu s brojem: " + designNumber));
                            importOrderItem.setIndShouldImport(false);
                        }

                        try {
                            NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
                            if (nf instanceof DecimalFormat) {
                                ((DecimalFormat) nf).setParseBigDecimal(true);
                            }
                            BigDecimal price = (BigDecimal) nf.parse(rowData.get("price_per_unit"));

                            importOrderItem.setPricePerPart(price);

                            if (helper.getPricePerPart().compareTo(price) != 0) {
                                importOrderItem.setPricePerPart(helper.getPricePerPart());
                                importOrderItem.setWarningText(ConcatWarning(importOrderItem.getWarningText(), "Cijena za " + item.getQuantityOrdered() + " komada csv:" + price + " a u nacrtu:" + helper.getPricePerPart()));
                            }

                        } catch (ParseException e) {
                            //throw new ParseException("Cijena nije ispravano upisana", 0);
                            importOrderItem.setWarningText(ConcatWarning(importOrderItem.getWarningText(), "Cijena nije ispravano upisana"));
                            importOrderItem.setIndShouldImport(false);
                        }

                        BigDecimal price = importOrderItem.getPricePerPart().multiply(BigDecimal.valueOf(item.getQuantityOrdered())).setScale(2, RoundingMode.HALF_UP);

                        importOrderItem.setTotal(price);
                        _total = _total.add(price);



                    }

                    if (item != null && item.getDesign() != null) {
                        importOrderItem.setTNGDisplayName(item.getDesign().getIsTokarenjeDisplayName());
                    }


                    importOrderItem.setImportedOrderItem(item);

                    currentItemScope = importOrderItem; // Store scope context for immediate subsequent disposition rows

                    importOrderItems.add(importOrderItem);
                } // 4. Item Delivery/Disposition Row
                else if ("position".equals(rowType) && "disposition".equals(subType) && currentItemScope != null && !_isDeleted) {
                    Date date = null;
                    try {
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        date = f.parse(rowData.get("delivery_date"));

                    } catch (ParseException e) {
                        currentItemScope.setWarningText(ConcatWarning(currentItemScope.getWarningText(), "Neispravan datum isporuke " + rowData.get("delivery_date")));
                    }


                    currentItemScope.getImportedOrderItem().setShippingDate(date);
                    /*
                    if (!_isNew && currentItemScope.getExistingOrderItem() != null
                    && ((date == null && currentItemScope.getExistingOrderItem().getShippingDate() != null)
                    || (date != null && currentItemScope.getExistingOrderItem().getShippingDate() == null))
                    || !date.equals(currentItemScope.getExistingOrderItem().getShippingDate())) {
                    currentItemScope.setWarningText(ConcatWarning(currentItemScope.getWarningText(), "Upisani datum isporuke:" + (currentItemScope.getExistingOrderItem().getShippingDate() != null ? currentItemScope.getExistingOrderItem().getShippingDate() : "") + ", za import:" + (date != null ? date : "")));
                    }
                     * */
                }
            }
        }
        br.close();

        priceLbl.setText(_total.toPlainString().replace('.', ','));

        if (deletedInvoicedItemsErrorMessages == null || deletedInvoicedItemsErrorMessages.isEmpty()) {
            return;
        }

        Object[] options = new Object[]{"Razumijem, nastavi s uvozom", "Odustani"};
        int option = JOptionPane.showOptionDialog(
                null,
                deletedInvoicedItemsErrorMessages + "\n\n"
                + "Nastavkom uvoza ove stavke će se ignorirati (neće biti uvezene iz CSV-a).",
                "Upozorenje uvoza",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (option == 0) {
            return;
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                ImportOrdersFromCsvJDialog.this.dispose();
            }
        });
    }

    private String ConcatWarning(String currentWarning, String warningMessage) {
        if (currentWarning == null) {
            currentWarning = "";
        }

        return currentWarning + " ;" + warningMessage;
    }

    private String concatWithNewLine(String sourceStr, String toConcat) {
        if (sourceStr == null) {
            sourceStr = "";
        } else {
            sourceStr += '\n';
        }

        return sourceStr + toConcat;
    }

    private String CleanString(String value) {
        return value == null || value.trim().length() == 0 ? "" : value.trim();
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
        jLabel3 = new javax.swing.JLabel();
        jXDatePickerDesignDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        partnerText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        priceLbl = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        createOrderBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stefanpresentationlayer.StefanPresentationLayerApp.class).getContext().getResourceMap(ImportOrdersFromCsvJDialog.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        brojNarudzbeText.setEditable(false);
        brojNarudzbeText.setText(resourceMap.getString("brojNarudzbeText.text")); // NOI18N
        brojNarudzbeText.setName("brojNarudzbeText"); // NOI18N

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

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        partnerText.setEditable(false);
        partnerText.setText(resourceMap.getString("partnerText.text")); // NOI18N
        partnerText.setName("partnerText"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setName("jTable1"); // NOI18N
        jTable1.setRowHeight(24);
        jTable1.setRowMargin(4);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${importOrderItems}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${selected}"));
        columnBinding.setColumnName("Selected");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${warningText}"));
        columnBinding.setColumnName("Upozorenje");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${indShouldImport}"));
        columnBinding.setColumnName("Ind Should Import");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.position}"));
        columnBinding.setColumnName("Imported Order Item.position");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.design.date}"));
        columnBinding.setColumnName("Imported Order Item.design.date");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.design.designNumber}"));
        columnBinding.setColumnName("Imported Order Item.design.design Number");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.design.revision}"));
        columnBinding.setColumnName("Imported Order Item.design.revision");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.design.name}"));
        columnBinding.setColumnName("Imported Order Item.design.name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.design.designIdentity}"));
        columnBinding.setColumnName("Imported Order Item.design.design Identity");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.design.classMark}"));
        columnBinding.setColumnName("Imported Order Item.design.class Mark");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.design.niklanje}"));
        columnBinding.setColumnName("Imported Order Item.design.niklanje");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${TNGDisplayName}"));
        columnBinding.setColumnName("TNGDisplay Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.shippingDate}"));
        columnBinding.setColumnName("Imported Order Item.shipping Date");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${importedOrderItem.quantityOrdered}"));
        columnBinding.setColumnName("Imported Order Item.quantity Ordered");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pricePerPart}"));
        columnBinding.setColumnName("Price Per Part");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${total}"));
        columnBinding.setColumnName("Total");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title14")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title15")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(11).setHeaderValue(resourceMap.getString("jTable1.columnModel.title9")); // NOI18N
        jTable1.getColumnModel().getColumn(12).setHeaderValue(resourceMap.getString("jTable1.columnModel.title10")); // NOI18N
        jTable1.getColumnModel().getColumn(13).setHeaderValue(resourceMap.getString("jTable1.columnModel.title11")); // NOI18N
        jTable1.getColumnModel().getColumn(14).setHeaderValue(resourceMap.getString("jTable1.columnModel.title12")); // NOI18N
        jTable1.getColumnModel().getColumn(15).setHeaderValue(resourceMap.getString("jTable1.columnModel.title13")); // NOI18N

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        priceLbl.setFont(resourceMap.getFont("priceLbl.font")); // NOI18N
        priceLbl.setName("priceLbl"); // NOI18N

        cancelBtn.setText(resourceMap.getString("cancelBtn.text")); // NOI18N
        cancelBtn.setName("cancelBtn"); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        createOrderBtn.setText(resourceMap.getString("createOrderBtn.text")); // NOI18N
        createOrderBtn.setName("createOrderBtn"); // NOI18N
        createOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrderBtnActionPerformed(evt);
            }
        });

        deleteBtn.setIcon(resourceMap.getIcon("deleteBtn.icon")); // NOI18N
        deleteBtn.setName("deleteBtn"); // NOI18N
        deleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(brojNarudzbeText, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jXDatePickerDesignDate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(partnerText, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(createOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(partnerText)
                            .addComponent(jXDatePickerDesignDate, 0, 0, Short.MAX_VALUE)
                            .addComponent(brojNarudzbeText)))
                    .addComponent(deleteBtn, 0, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(priceLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

private void jXDatePickerDesignDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePickerDesignDateActionPerformed
}//GEN-LAST:event_jXDatePickerDesignDateActionPerformed

private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
    this.dispose();
}//GEN-LAST:event_cancelBtnActionPerformed

private void createOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrderBtnActionPerformed


    if (importOrder.getOrderNumber() == null) {
        JOptionPane.showMessageDialog(null, "Neispravni podaci za narudžbu. Molimo, zatvorite ekran i pokušajte ponovno", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    importOrder.setDate(jXDatePickerDesignDate.getDate());
    if (importOrder.getDate() == null) {
        JOptionPane.showMessageDialog(null, "Nije upisan datum narudžbe.", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (importOrder.getOrderNumber().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nije upisan broj narudžbe.", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }


    if (importOrder.getBusinessPartnerId() == null) {
        JOptionPane.showMessageDialog(null, "Firma nije postavljena", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    List<Design> designsToUpdate = new ArrayList<Design>();

    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    for (ImportOrderItemDto iItem : importOrderItems) {

        if (iItem.getIndDesignModified()) {
            Design d = new Design();
            d.setIdDesign(iItem.getImportedOrderItem().getDesign().getIdDesign());
            d.setIsTokarenjeDisplayName(iItem.getTNGDisplayName());
            designsToUpdate.add(d);
        }

        if (iItem.getIndShouldImport() == false) {
            continue;
        }
        orderItems.add(iItem.getImportedOrderItem());
    }

    if (orderItems.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Niti jedna stavka se ne može učitati", "Upozorenje", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    importOrder.setOrderitemsList(orderItems);

    OrderManager orderManager = new OrderManager();
    Orders existingOrder = orderManager.getOrderByNumberAndBussPartner(
            importOrder.getOrderNumber(),
            importOrder.getBusinessPartnerId());

    TryUpdateDesigns(designsToUpdate);

    if (existingOrder == null) {
        orderManager.SaveOrder(this.importOrder);
        this.dispose();
        return;
    }

    EntityManager em = QueryManager.getEntityManagerInstance();
    em.getTransaction().begin();

    for (OrderItem oi : importOrder.getOrderitemsList()) {
        boolean found = false;

        for (Orderitems existingItem : existingOrder.getOrderitemsList()) {
            if (oi.getPosition().equals(existingItem.getPosition())
                    && oi.getDesign().getIdDesign().equals(existingItem.getIdDesign().getIdDesign())) {
                found = true;

                if (existingItem.getQuantityDelivered().equals(oi.getQuantityOrdered())) {
                    _deletedOrderItems.add(existingItem.getIdOrderItems());
                    break;
                } else if (existingItem.getQuantityDelivered() > oi.getQuantityOrdered()) {

                    JOptionPane.showMessageDialog(null, "Stavka sa nacrtom " + existingItem.getIdDesign().getDesignNumber() + " je fakturirana u većoj količini (" + existingItem.getQuantityDelivered() + ") od upisane u csv (" + oi.getQuantityOrdered() + "). Učitavanje stavke će se preskočiti", "Obavijest", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                existingItem.setShippingDate(oi.getShippingDate());
                existingItem.setQuantityOrdered(oi.getQuantityOrdered());

                QueryManager.getEntityManagerInstance().persist(existingItem);
                break;
            }
        }

        if (!found) {
            DesignManager dm = new DesignManager();

            Orderitems createdOi = new Orderitems();
            createdOi.setIdOrder(existingOrder);
            createdOi.setQuantityOrdered(oi.getQuantityOrdered());
            createdOi.setQuantityDelivered(0);
            createdOi.setIdDesign(dm.GetDesignsByDBId(oi.getDesignId()));
            createdOi.setPosition(oi.getPosition());
            createdOi.setShippingDate(oi.getShippingDate());

            existingOrder.getOrderitemsList().add(createdOi);
        }
    }

    for (Integer toDeleteId : _deletedOrderItems) {
        Orderitems foundItem = null;

        for (Orderitems existingItem : existingOrder.getOrderitemsList()) {
            if (existingItem.getIdOrderItems().equals(toDeleteId)) {
                foundItem = existingItem;
                break;
            }
        }

        if (foundItem != null) {
            em.remove(foundItem);
            existingOrder.getOrderitemsList().remove(foundItem);
        }
    }


    try {
        em.persist(existingOrder);
        em.getTransaction().commit();

        this.dispose();
    } catch (Exception ex) {
        em.getTransaction().rollback();

        JOptionPane.showMessageDialog(null, "Narudžbu nije bilo moguće ažurirati", "Greška pri ažuriranju narudžbe", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_createOrderBtnActionPerformed

    private void TryUpdateDesigns(List<Design> designs) {
        if (designs == null) {
            return;
        }

        DesignManager m = new DesignManager();
        m.UpdateNxlTokarenje(designs);
    }

private void deleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMouseClicked
    int[] selectedRows = jTable1.getSelectedRows();
    if (selectedRows == null || selectedRows.length <= 0) {
        return;
    }

    Object[] options = {"Da", "Ne"};

    int showConfirmDialog = JOptionPane.showOptionDialog(null, "Jeste li sigurni da želite ukloniti odabrane stavke?", "Pitanje", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    if (showConfirmDialog == 0) {
        Iterator<ImportOrderItemDto> iterator = importOrderItems.iterator();
        while (iterator.hasNext()) {
            ImportOrderItemDto item = iterator.next();
            if (item != null && item.getSelected()) {
                iterator.remove();
            }
        }
    }

}//GEN-LAST:event_deleteBtnMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField brojNarudzbeText;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton createOrderBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerDesignDate;
    private javax.swing.JTextField partnerText;
    private javax.swing.JLabel priceLbl;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
