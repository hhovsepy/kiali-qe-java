package com.redhat.qe.kiali.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private String name;
    private String namespace;
}
