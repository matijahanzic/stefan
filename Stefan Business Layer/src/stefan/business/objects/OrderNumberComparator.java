/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.util.Comparator;

/**
 *
 * @author Robert
 */
public class OrderNumberComparator implements Comparator<BillItem> {

    @Override
    public int compare(BillItem o1, BillItem o2) {
        try {
            if ((Long.parseLong(o1.getOrderNumber()) - Long.parseLong(o2.getOrderNumber())) == 0) {
                return (Integer.parseInt(o1.getPosition()) - Integer.parseInt(o2.getPosition()));
            } else {
                if (Long.parseLong(o1.getOrderNumber()) - Long.parseLong(o2.getOrderNumber()) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } catch (Exception e) {
            return 1;
        }

    }
}
