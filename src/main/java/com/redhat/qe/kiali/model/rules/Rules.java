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
public class Rules {
    private Namespace namespace;
    private List<Rule> rules;
}
