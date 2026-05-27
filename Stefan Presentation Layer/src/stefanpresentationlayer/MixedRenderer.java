/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefanpresentationlayer;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Administrator
 */
public class MixedRenderer implements TableCellRenderer {

    private JLabel label = new JLabel();
    private JTextArea textArea = new JTextArea();

    public MixedRenderer() {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(true);

        label.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        String text = value == null ? "" : value.toString();

        // EMPTY -> LABEL
        if (text.trim().isEmpty()) {

            label.setText("");

            if (isSelected) {
                label.setBackground(table.getSelectionBackground());
                label.setForeground(table.getSelectionForeground());
            } else {
                label.setBackground(table.getBackground());
                label.setForeground(table.getForeground());
            }

            return label;
        }

        // HAS TEXT -> TEXTAREA
        textArea.setText(text);

        if (isSelected) {
            textArea.setBackground(table.getSelectionBackground());
            textArea.setForeground(table.getSelectionForeground());
        } else {
            textArea.setBackground(table.getBackground());
            textArea.setForeground(table.getForeground());
        }
/*
        int prefHeight = textArea.getPreferredSize().height;

        if (table.getRowHeight(row) != prefHeight) {
            table.setRowHeight(row, prefHeight);
        }*/
        table.setRowHeight(row, 80);

        return textArea;
    }
}