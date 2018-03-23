package com.redhat.qe.kiali.ui.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.model.services.PodStatus;
import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ComponentsHelper extends UIAbstract {
    private static final String POD_STATUS = ".//strong[normalize-space(text())=\"Pod status:\"]/..";

    public ComponentsHelper(KialiWebDriver driver) {
        super(driver);
    }

    public Map<String, String> labels(List<WebElement> labelElements) {
        Map<String, String> labels = new HashMap<String, String>();
        for (WebElement el : labelElements) {
            labels.put(element(el, ".//*[name()=\"g\"]/*[name()=\"text\"][2]").getText(),
                    element(el, ".//*[name()=\"g\"]/*[name()=\"text\"][3]").getText());
        }
        return labels;
    }

    public PodStatus podStatus(WebElement el) {
        // get pod status
        String podUpTotalStr = element(el, POD_STATUS).getText();
        podUpTotalStr = normalizeSpace(podUpTotalStr, "").replaceFirst("Podstatus:", "");

        String statusString = null;
        String statusIcon = element(el, POD_STATUS + "/span").getAttribute("class");
        if (statusIcon.contains("pficon-ok")) {
            statusString = "ok";
        } else if (statusIcon.contains("pficon-warning-triangle-o")) {
            statusString = "warning";
        } else {
            statusString = statusIcon;
        }

        String[] podStatus = podUpTotalStr.split("/");
        PodStatus pod = null;
        if (podStatus.length == 2) {
            pod = PodStatus.builder()
                    .availableReplicas(Integer.valueOf(podStatus[0]))
                    .replicas(Integer.valueOf(podStatus[1]))
                    .status(statusString)
                    .build();
        } else {
            pod = PodStatus.builder()
                    .status(statusString)
                    .build();
        }
        return pod;
    }
}
