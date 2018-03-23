package com.redhat.qe.kiali.ui.enums;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ServicePageEnum {

    public enum TAB implements IEnumString {
        INFO("Info"),
        METRICS("Metrics");

        public static TAB fromText(String text) {
            return (TAB) EnumStringHelper.fromText(text, values());
        }

        public static TAB get(int id) {
            return (TAB) EnumHelper.get(id, values());
        }

        private final String name;

        private TAB(String name) {
            this.name = name;
        }

        @Override
        public String getText() {
            return this.name;
        }
    }

}
