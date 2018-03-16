package com.redhat.qe.kiali.ui.components;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.ui.SwsDriverUI;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Dropdown extends UIAbstract {
    private static final String SELECT_BUTTON = ".//*[contains(@class, \"dropdown-toggle\")]";
    private static final String OPTIONS_LIST = ".//*[contains(@class, \"dropdown-menu\")]//*[contains(@role, \"menuitem\")]";
    private static final String OPTION = ".//*[contains(@class, \"dropdown-menu\")]//*[contains(@role, \"menuitem\") and text()=\"{0}\"]";
    //private static final String OPTION_SELECTED = ".//*[contains(@class, \"dropdown-menu\")]//*[contains(@class, \"selected\") or contains(@class, \"active\")]//*[contains(@role, \"menuitem\")]";

    private String identifier = "//*[contains(@class, \"form-group\")]/*[contains(@class, \"dropdown\")]/..";

    public Dropdown(SwsDriverUI driver) {
        this(driver, null);
    }

    public Dropdown(SwsDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    private void close() {
        // Temporary fix, check is element viable and close. On Filter page reloads and moved to default-input element
        if (!isElementPresent(identifier, SELECT_BUTTON)) {
            return;
        }
        WebElement button = element(identifier, SELECT_BUTTON);
        if (button.getAttribute("aria-expanded").equalsIgnoreCase("true")) {
            button.click();
        }
    }

    private void open() {
        WebElement button = element(identifier, SELECT_BUTTON);
        if (button.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
            button.click();
        }
    }

    public List<String> options() {
        open();
        List<String> options = children(identifier, OPTIONS_LIST);
        close();
        return options;
    }

    public void select(String option) {
        open();
        element(identifier, OPTION, option).click();
        close();
    }

    public String selected() {
        return element(identifier, SELECT_BUTTON).getText();
    }

}
