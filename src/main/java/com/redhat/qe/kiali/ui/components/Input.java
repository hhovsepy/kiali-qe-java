package com.redhat.qe.kiali.ui.components;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Input extends UIAbstract {
    private String identifier = "//input";

    public Input(KialiWebDriver driver) {
        this(driver, null);
    }

    public Input(KialiWebDriver driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    public void set(String value) {
        WebElement input = element(identifier);
        input.clear();
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public String value() {
        return element(identifier).getAttribute("value");
    }
}
