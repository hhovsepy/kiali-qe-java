package com.redhat.qe.swsui.enums;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class EnumIntegerHelper extends EnumHelper {
    public static IEnumInteger fromValue(Integer value, IEnumInteger[] values) {
        if (value != null) {
            for (IEnumInteger type : values) {
                if (value.equals(type.getValue())) {
                    return type;
                }
            }
        }
        return null;
    }
}
