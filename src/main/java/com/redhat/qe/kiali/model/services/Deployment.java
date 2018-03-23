package com.redhat.qe.kiali.model.services;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Builder
@Data
@ToString
public class Deployment {
    private String name;
    private Map<String, String> labels;
    private PodStatus podStatus;
}
