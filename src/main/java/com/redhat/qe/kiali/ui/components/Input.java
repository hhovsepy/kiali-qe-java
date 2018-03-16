package com.redhat.qe.kiali.ui.components;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.ui.KialiDriverUI;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Input extends UIAbstract {
    private String identifier = "//input";

    public Input(KialiDriverUI driver) {
        this(driver, null);
    }

    public Input(KialiDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    public void set(String value) {
        WebElement input = element(identifier);
        input.clear();
        input.sendKeys(value + "\n");
    }

    public String value() {
        return element(identifier).getAttribute("value");
    }
}
