package com.redhat.qe.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Getter
@Builder
@ToString
public class KeyValue {
    private String key;
    private String value;

    public boolean equals(KeyValue keyValue) {
        if (!key.equals(keyValue.getKey())) {
            return false;
        }
        if (!value.equals(keyValue.getValue())) {
            return false;
        }
        return true;
    }
}
