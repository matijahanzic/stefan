/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefanpresentationlayer;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import stefan.business.objects.MaterialDetails;
/**
 *
 * @author Robert
 */
public class ComboBoxMaterialDetailsRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof MaterialDetails) {
            MaterialDetails m  = (MaterialDetails) value;
            setText(m.getMaterialName());
        }
        return this;
    }
}
