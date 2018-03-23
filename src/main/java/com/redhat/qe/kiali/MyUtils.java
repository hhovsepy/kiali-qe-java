package com.redhat.qe.kiali;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class MyUtils {

    public static boolean equalsCheck(Object obj1, Object obj2) {
        if (obj1 != null) {
            if (obj2 != null) {
                return obj1.equals(obj2);
            } else {
                return false;
            }
        } else {
            return obj2 == null;
        }
    }

}
