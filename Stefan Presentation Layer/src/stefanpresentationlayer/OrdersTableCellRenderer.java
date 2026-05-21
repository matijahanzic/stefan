package stefanpresentationlayer;


import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.jdesktop.swingx.tree.DefaultXTreeCellRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrdersTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!(comp instanceof JComponent) || !table.getValueAt(row, MyTreeTableModel.INTERNAL_EXTERNAL_COLUMN_INDEX).equals("E"))
            return comp;

        JComponent jComp = (JComponent)comp;
        jComp.setBackground(new Color(153, 204, 255));
        jComp.setOpaque(true);

       return comp;
    }
}

// public class OrdersTableCellRenderer extends DefaultTableCellRenderer {
//     @Override
//     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//         Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//         if (!(table instanceof JXTreeTable))
//             return comp;
//
//         JXTreeTable treeTab = (JXTreeTable)table;
//         Object val = treeTab.getValueAt(row, column);
//
//         if (val instanceof MyTreeNode) {
//             MyTreeNode tn = (MyTreeNode)value;
//             if (tn.getIsBusinessPartnerExternal()) {
//                 comp.setBackground(new Color(192, 230, 245));
//                 if (comp instanceof JComponent) {
//                     JComponent jComp = (JComponent)comp;
//                     jComp.setOpaque(true);
//                 }
//             }
//         }
//
//         return comp;
//     }
// }
