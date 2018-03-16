package com.redhat.qe.swsui.components;

import org.openqa.selenium.WebElement;

import com.redhat.qe.swsui.SwsDriverUI;
import com.redhat.qe.swsui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Input extends UIAbstract {
    private String identifier = "//input";

    public Input(SwsDriverUI driver) {
        this(driver, null);
    }

    public Input(SwsDriverUI driver, String identifier) {
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