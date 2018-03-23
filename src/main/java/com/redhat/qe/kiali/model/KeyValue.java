package com.redhat.qe.kiali.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Data
@Builder
@ToString
public class KeyValue {
    private String key;
    private String value;

    @Override
    public boolean equals(Object obj) {
        KeyValue keyValue = (KeyValue) obj;
        if (!key.equals(keyValue.getKey())) {
            return false;
        }
        if (!value.equals(keyValue.getValue())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }
}
