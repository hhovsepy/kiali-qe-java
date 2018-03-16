package com.redhat.qe.kiali.ui.enums;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class EnumHelper {
    public static IEnum get(int id, IEnum[] values) {
        for (IEnum type : values) {
            if (type.ordinal() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }
}
