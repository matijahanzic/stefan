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

    @Override
    public int compare(BillItem o1, BillItem o2) {
        PackageKey k1 = PackageKey.of(o1.getPackageNumber());
        PackageKey k2 = PackageKey.of(o2.getPackageNumber());

        // numeric first, then "p" packages, then invalid values
        int c = Integer.compare(k1.group, k2.group);
        if (c != 0) {
            return c;
        }

        if (k1.group == PackageKey.NUMERIC) {
            c = Long.compare(k1.weight, k2.weight);   // same order as old prvi/drugi + pluses
            if (c != 0) {
                return c;
            }
            c = Long.compare(k1.base, k2.base);       // tie-break for "3++" vs "4+"
            if (c != 0) {
                return c;
            }
        } else if (k1.group == PackageKey.INVALID) {
            c = k1.raw.compareTo(k2.raw);
            if (c != 0) {
                return c;
            }
        }

        c = compareNumber(o1.getOrderNumber(), o2.getOrderNumber());
        if (c != 0) {
            return c;
        }
        return compareNumber(o1.getPosition(), o2.getPosition());
    }

    /** Numbers ascending, unparseable values after them, ordered as strings. */
    private static int compareNumber(String a, String b) {
        Long na = tryParse(a);
        Long nb = tryParse(b);
        if (na != null && nb != null) {
            return Long.compare(na, nb);
        }
        if (na != null) {
            return -1;
        }
        if (nb != null) {
            return 1;
        }
        return String.valueOf(a).compareTo(String.valueOf(b));
    }

    private static Long tryParse(String s) {
        if (s == null) {
            return null;
        }
        try {
            return Long.parseLong(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static final class PackageKey {

        static final int NUMERIC = 0, P_PACKAGE = 1, INVALID = 2;
        final int group;
        final long base;    // number before the first '+'
        final long weight;  // old (base + 0.1 + pluses), scaled by 10 to avoid doubles
        final String raw;

        private PackageKey(int group, long base, long weight, String raw) {
            this.group = group;
            this.base = base;
            this.weight = weight;
            this.raw = raw;
        }

        static PackageKey of(String s) {
            String raw = s == null ? "" : s.trim();
            if (raw.toLowerCase().contains("p")) {
                return new PackageKey(P_PACKAGE, 0, 0, raw);
            }
            try {
                int plus = raw.indexOf('+');
                if (plus < 0) {
                    long base = Long.parseLong(raw);
                    return new PackageKey(NUMERIC, base, base * 10, raw);
                }
                long base = Long.parseLong(raw.substring(0, plus).trim());
                long pluses = 0;
                for (char ch : raw.toCharArray()) {
                    if (ch == '+') {
                        pluses++;
                    }
                }
                return new PackageKey(NUMERIC, base, (base + pluses) * 10 + 1, raw);
            } catch (NumberFormatException e) {
                return new PackageKey(INVALID, 0, 0, raw);
            }
        }
    }
}
/*
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
*/