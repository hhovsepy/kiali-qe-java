package com.redhat.qe.kiali.model.services;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Builder
@Data
@ToString
public class PodStatus {
    private Integer availableReplicas;
    private Integer replicas;
    private String status;
}
