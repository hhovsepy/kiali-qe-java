package com.redhat.qe.kiali.ui.enums;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class PaginationEnum {

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

}
