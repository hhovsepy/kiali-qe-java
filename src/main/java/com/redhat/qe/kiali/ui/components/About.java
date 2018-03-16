package com.redhat.qe.kiali.ui.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.model.Version;
import com.redhat.qe.kiali.ui.SwsDriverUI;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class About extends UIAbstract {

    private static final String HEADER = ".//*[contains(@class, \"modal-header\")]";
    private static final String APP_NAME = ".//*[contains(@class, \"modal-body\")]/h1";
    private static final String VERSION = ".//*[contains(@class, \"modal-body\")]//*[contains(@class, \"product-versions-pf\")]//li";
    private static final String VERSION_NAME = "./strong";
    private static final String CLOSE = HEADER + "//*[contains(@class, \"close\")]";

    private String identifier = "//*[contains(@class, \"about-modal-pf\")]";

    public About(SwsDriverUI driver) {
        this(driver, null);
    }

    public About(SwsDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    public String applicationName() {
        return element(identifier, APP_NAME).getText();
    }

    public void close() {
        element(identifier, CLOSE).click();
    }

    public String header() {
        return element(identifier, HEADER).getText();
    }

    public List<Version> versions() {
        ArrayList<Version> versions = new ArrayList<Version>();
        List<WebElement> elements = elements(identifier, VERSION);
        for (WebElement el : elements) {
            String versionWithName = el.getText();
            String name = element(el, VERSION_NAME).getText();
            versions.add(Version.builder()
                    .name(name.trim())
                    .version(versionWithName.replaceFirst(name, "").trim())
                    .build());
        }
        return versions;
    }

}
