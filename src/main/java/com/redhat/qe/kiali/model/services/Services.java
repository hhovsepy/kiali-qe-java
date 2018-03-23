package com.redhat.qe.kiali.model.services;

import java.util.List;

import com.redhat.qe.kiali.model.Namespace;

import lombok.Data;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Data
@ToString
public class Services {
    private Namespace namespace;
    private List<Service> services;
}
