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
public class OrderOrderNumberComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        try {
            if ((Long.parseLong(o1.getOrderNumber().trim()) - Long.parseLong(o2.getOrderNumber().trim())) == 0) {
                return 1;
            } else {
                if (Long.parseLong(o1.getOrderNumber().trim()) - Long.parseLong(o2.getOrderNumber().trim()) > 0) {
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
