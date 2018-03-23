package com.redhat.qe.kiali.ui.tests;

import com.redhat.qe.kiali.rest.KialiRestClient;
import com.redhat.qe.kiali.ui.KialiWebDriver;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */
@Getter
@Builder
public class Driver {
    private String kialiHostname;
    private KialiWebDriver webDriver;
    private KialiRestClient restClient;

    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
