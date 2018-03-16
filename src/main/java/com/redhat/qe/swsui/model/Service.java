package com.redhat.qe.swsui.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Data
@Builder
@ToString
public class Service {
    private String name;
    private String namespace;
}
