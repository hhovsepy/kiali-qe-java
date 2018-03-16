package com.redhat.qe.kiali.model;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Getter
@ToString
public class Services {
    private Namespace namespace;
    private List<Service> services;
}
