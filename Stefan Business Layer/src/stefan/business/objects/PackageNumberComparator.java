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
public class PackageNumberComparator implements Comparator<BillItem> {

    public int compare(BillItem o1, BillItem o2) {
        double prvi = 0, drugi = 0;
        double prviPlus = 0, drugiPlus = 0;
        try {
            if (o1.getPackageNumber().toLowerCase().contains("p") && o2.getPackageNumber().toLowerCase().contains("p") )
            {
                if ((Long.parseLong(o1.getOrderNumber()) - Long.parseLong(o2.getOrderNumber())) == 0) {
                    return (Integer.parseInt(o1.getPosition()) - Integer.parseInt(o2.getPosition()));
                } else {
                    if (Long.parseLong(o1.getOrderNumber()) - Long.parseLong(o2.getOrderNumber()) > 0) {
                        return 1;
                    } else {
                        return -1;
                    }

                }
            }
            else{
                if (o1.getPackageNumber().toLowerCase().contains("p")) {
                 return 1;
             } else if (o2.getPackageNumber().toLowerCase().contains("p")) {
                 return -1;
              }
            }
            if ((o1.getPackageNumber()).contains("+")) {
                prviPlus = CountPluses(o1.getPackageNumber());
                prvi = Integer.parseInt((o1.getPackageNumber()).substring(0, (o1.getPackageNumber()).indexOf("+"))) + 0.1;
            } else {
                prvi = Integer.parseInt(o1.getPackageNumber());
            }
            if ((o2.getPackageNumber()).contains("+")) {
                drugiPlus = CountPluses(o2.getPackageNumber());
                drugi = Integer.parseInt((o2.getPackageNumber()).substring(0, (o2.getPackageNumber()).indexOf("+"))) + 0.1;

            } else {
                drugi = Integer.parseInt(o2.getPackageNumber());
            }

            if (((prvi - drugi) == 0) && (prviPlus == drugiPlus)) {

                if ((Long.parseLong(o1.getOrderNumber()) - Long.parseLong(o2.getOrderNumber())) == 0) {
                    return (Integer.parseInt(o1.getPosition()) - Integer.parseInt(o2.getPosition()));
                } else {
                    if (Long.parseLong(o1.getOrderNumber()) - Long.parseLong(o2.getOrderNumber()) > 0) {
                        return 1;
                    } else {
                        return -1;
                    }

                }
            } else {
                prvi += prviPlus;
                drugi += drugiPlus;
                if (prvi > drugi) {
                    return 1;
                } else {
                    return -1;
                }
            }

        } catch (Exception e) {
            return 1;
        }

    }

    private double CountPluses(String packageNumber) {
        char[] pluses = packageNumber.toCharArray();
        double broj = 0;
        for (int i = 0; i < packageNumber.length(); i++) {
            if (pluses[i] == '+') {
                broj += 0.1;
            }
        }
        return broj;
    }
}
