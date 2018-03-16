package com.redhat.qe.kiali.ui.components;

import com.redhat.qe.kiali.ui.SwsDriverUI;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Navbar extends UIAbstract {
    private static final String TOGGLE_NAVIGATION = ".//*[contains(@class, \"navbar-toggle\")]";
    private static final String NAVBAR_RIGHT_MENU = "//*[contains(@class, \"navbar-right\")]//*[contains(@class, \"dropdown\")]";

    private String identifier = "//*[contains(@class, \"navbar\")]";

    private Dropdown menuRight;

    public Navbar(SwsDriverUI driver) {
        this(driver, null);
    }

    public Navbar(SwsDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
        menuRight = new Dropdown(driver, this.identifier + NAVBAR_RIGHT_MENU);
    }

    public About about() {
        menuRight().select("About");
        return new About(driver);
    }

    public Dropdown menuRight() {
        return menuRight;
    }

    public void toggle() {
        element(identifier, TOGGLE_NAVIGATION).click();
    }
}
