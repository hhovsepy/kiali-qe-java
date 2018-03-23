package com.redhat.qe.kiali.ui.components;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class PageHeader extends UIAbstract {
    private String identifier = "//h2";

    public PageHeader(KialiWebDriver driver) {
        this(driver, null);
    }

    public PageHeader(KialiWebDriver driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    public String text() {
        return element(identifier).getText();
    }
}
