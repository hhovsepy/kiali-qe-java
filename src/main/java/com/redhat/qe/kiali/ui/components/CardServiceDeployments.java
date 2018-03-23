package com.redhat.qe.kiali.ui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.model.services.Deployment;
import com.redhat.qe.kiali.model.services.PodStatus;
import com.redhat.qe.kiali.ui.KialiWebDriver;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class CardServiceDeployments extends Card {

    private static final String ROW = "/*[@class=\"row\"]";
    private static final String DEPLOYMENT = "//p/strong[normalize-space(text())=\"{0}\"]/../..";

    private ComponentsHelper componentsHelper = null;

    public CardServiceDeployments(KialiWebDriver driver) {
        this(driver, null);
    }

    public CardServiceDeployments(KialiWebDriver driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
        componentsHelper = new ComponentsHelper(driver);
    }

    private Deployment get(WebElement el) {
        // get deployment name
        String name = element(el, ".//p/strong").getText();

        // get labels
        Map<String, String> labels = componentsHelper.labels(elements(el, ".//*[name()=\"svg\"]"));

        // get pod status
        PodStatus pod = componentsHelper.podStatus(el);

        return Deployment.builder()
                .name(name)
                .labels(labels)
                .podStatus(pod)
                .build();
    }

    public List<Deployment> all() {
        List<Deployment> deployments = new ArrayList<Deployment>();
        List<WebElement> rootEls = elements(identifier, BODY + ROW);
        for (WebElement el : rootEls) {
            deployments.add(get(el));
        }
        return deployments;
    }

    public Deployment get(String deploymentName) {
        if (isElementPresent(identifier, BODY + ROW + DEPLOYMENT, deploymentName)) {
            WebElement el = element(identifier, BODY + ROW + DEPLOYMENT, deploymentName);
            return get(el);
        } else {
            return null;
        }
    }
}
