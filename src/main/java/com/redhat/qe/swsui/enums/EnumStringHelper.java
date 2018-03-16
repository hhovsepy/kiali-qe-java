package com.redhat.qe.swsui.enums;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class EnumStringHelper extends EnumHelper {
    public static IEnumString fromText(String text, IEnumString[] values) {
        if (text != null) {
            for (IEnumString type : values) {
                if (text.equalsIgnoreCase(type.getText())) {
                    return type;
                }
            }
        }
        return null;
    }
}
