package com.redhat.qe.kiali.ui.tests;

import com.redhat.qe.kiali.rest.KialiRestClient;
import com.redhat.qe.kiali.ui.KialiDriverUI;

import lombok.Builder;

import lombok.Getter;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */
@Getter
@Builder
public class Driver {
    private KialiDriverUI driverUI;
    private KialiRestClient restClient;

    public void tearDown() {
        if (driverUI != null) {
            driverUI.quit();
        }
    }
}
