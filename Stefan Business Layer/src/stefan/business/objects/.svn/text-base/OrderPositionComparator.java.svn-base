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
public class OrderPositionComparator implements Comparator<OrderItem> {

    @Override
    public int compare(OrderItem o1, OrderItem o2) {
        try {
            return Integer.parseInt(o1.getPosition()) - Integer.parseInt(o2.getPosition());
        } catch (Exception e) {
            return 1;
        }
    }
    
}
