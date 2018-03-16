package com.redhat.qe.kiali.ui.components;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.ui.SwsDriverUI;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class MainMenu extends UIAbstract {
    private static final String MENU_ITEMS = ".//*[contains(@class, \"list-group-item-value\")]";
    private static final String MENU_ITEM = ".//*[contains(@class, \"list-group-item-value\") and text()=\"{0}\"]";
    private static final String MENU_ITEM_ACTIVE = ".//*[contains(@class, \"active\") and contains(@class, \"list-group-item\")]//*[contains(@class, \"list-group-item-value\")]";

    private String identifier = "//*[contains(@class, \"nav-pf-vertical-with-sub-menus\")"
            + " and contains(@class, \"nav-pf-persistent-secondary\")]";

    private Navbar navbar;

    public MainMenu(SwsDriverUI driver) {
        this(driver, null);
    }

    public MainMenu(SwsDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
        navbar = new Navbar(driver);
    }

    public void collapse() {
        if (!isCollapsed()) {
            navbar.toggle();
        }
    }

    public void expand() {
        if (isCollapsed()) {
            navbar.toggle();
        }
    }

    public boolean isCollapsed() {
        return element(identifier).getAttribute("class").contains("collapsed");
    }

    public List<String> items() {
        return children(identifier, MENU_ITEMS);
    }

    public Navbar navbar() {
        return navbar;
    }

    public void select(String menu) {
        element(identifier, MENU_ITEM, menu).click();
        waitForElement(identifier, MENU_ITEM, menu);
    }

    public String selected() {
        WebElement el = element(identifier, MENU_ITEM_ACTIVE);
        return el.getText();
    }
}
