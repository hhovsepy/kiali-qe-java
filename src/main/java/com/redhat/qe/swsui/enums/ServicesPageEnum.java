package com.redhat.qe.swsui.enums;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ServicesPageEnum {

    public enum FILTER implements IEnumString {
        SERVICE_NAME("Service Name"),
        NAMESPACE("Namespace");

        public static FILTER fromText(String text) {
            return (FILTER) EnumStringHelper.fromText(text, values());
        }

        public static FILTER get(int id) {
            return (FILTER) EnumHelper.get(id, values());
        }

        private final String name;

        private FILTER(String name) {
            this.name = name;
        }

        @Override
        public String getText() {
            return this.name;
        }
    }

    public enum PERPAGE implements IEnumInteger {
        FIVE(5),
        TEN(10),
        FIFTEEN(15);

        public static PERPAGE fromValue(Integer value) {
            return (PERPAGE) EnumIntegerHelper.fromValue(value, values());
        }

        public static PERPAGE get(int id) {
            return (PERPAGE) EnumHelper.get(id, values());
        }

        private final Integer value;

        private PERPAGE(Integer value) {
            this.value = value;
        }

        @Override
        public Integer getValue() {
            return this.value;
        }
    }

    public enum SORT implements IEnumString {
        NAMESPACE("Namespace"),
        SERVICE_NAME("Service Name");

        public static SORT fromText(String text) {
            return (SORT) EnumStringHelper.fromText(text, values());
        }

        public static SORT get(int id) {
            return (SORT) EnumHelper.get(id, values());
        }

        private final String name;

        private SORT(String name) {
            this.name = name;
        }

        @Override
        public String getText() {
            return this.name;
        }
    }
}
