/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefanpresentationlayer;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import stefan.business.objects.Material;
/**
 *
 * @author Robert
 */
public class ComboBoxMaterialRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Material) {
            Material m  = (Material) value;
            setText(m.getName());
        }
        return this;
    }
}
