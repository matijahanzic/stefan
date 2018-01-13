/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefanpresentationlayer;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import stefan.business.objects.CalculationForm;
/**
 *
 * @author Robert
 */
public class ComboBoxCalculationRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof CalculationForm) {
            CalculationForm m  = (CalculationForm) value;
            setText(m.getCalculationFormName());
        }
        return this;
    }
}
