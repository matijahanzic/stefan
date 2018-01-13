/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.util.Comparator;

/**
 *
 * @author Matija
 */
public class DesignNumberComparator implements Comparator<BillItem> {

    @Override
    public int compare(BillItem o1, BillItem o2) {
        return (o1.getDesignNumber().compareTo(o2.getDesignNumber()));
    }
    
}
