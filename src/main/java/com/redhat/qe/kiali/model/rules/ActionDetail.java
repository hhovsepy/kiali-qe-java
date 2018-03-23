package com.redhat.qe.kiali.model.rules;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActionDetail {
    private Handler handler;
    private List<Instance> instances;
}
