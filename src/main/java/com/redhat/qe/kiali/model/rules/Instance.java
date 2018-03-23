package com.redhat.qe.kiali.model.rules;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

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
public class Instance {
    private String name;
    private String template;
    private String spec;

    @JsonSetter("spec")
    public void setSpecJson(JsonNode spec) {
        this.spec = spec.toString();
    }
}
