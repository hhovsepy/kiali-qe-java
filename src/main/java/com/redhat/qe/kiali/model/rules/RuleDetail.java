package com.redhat.qe.kiali.model.rules;

import java.util.List;

import com.redhat.qe.kiali.model.Namespace;

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
public class RuleDetail {
    private String name;
    private Namespace namespace;
    private String match;
    private List<ActionDetail> actions;
}
