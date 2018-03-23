package com.redhat.qe.kiali.ui.components;

import java.util.List;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Tab extends UIAbstract {

    private String identifier = "//*[contains(@class, \"nav-tabs\")]";

    private static final String ITEMS = ".//*[contains(@role, \"presentation\")]";
    private static final String ITEM = ".//*[contains(@role, \"presentation\")]//*[text()=\"{0}\"]";
    private static final String ITEM_SELECTED = ".//*[contains(@role, \"presentation\") and contains(@class, \"active\")]";

    public Tab(KialiWebDriver driver) {
        this(driver, null);
    }

    public Tab(KialiWebDriver driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    public List<String> items() {
        return children(identifier, ITEMS);
    }

    public void select(String item) {
        if (!item.equals(selected())) {
            element(identifier, ITEM, item).click();
        }
    }

    public String selected() {
        return element(identifier, ITEM_SELECTED).getText();
    }

}
