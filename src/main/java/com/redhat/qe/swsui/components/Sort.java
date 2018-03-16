package com.redhat.qe.swsui.components;

import com.redhat.qe.swsui.SwsDriverUI;
import com.redhat.qe.swsui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Sort extends UIAbstract {
    private static final String ORDER_BY_ASC = ".//*[contains(@class, \"sort-direction\") and contains(@class, \"fa-sort-alpha-asc\")]";
    private static final String ORDER_BY_DESC = ".//*[contains(@class, \"sort-direction\") and contains(@class, \"fa-sort-alpha-desc\")]";

    private String identifier = "//*[contains(@class, \"form-group\")]/button/*[contains(@class, \"sort-direction\")]/..";

    public Sort(SwsDriverUI driver) {
        this(driver, null);
    }

    public Sort(SwsDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    public void ascending() {
        orderBy(true);
    }

    public void descending() {
        orderBy(false);
    }

    public boolean isAscending() {
        try {
            element(identifier, ORDER_BY_ASC);
            return true;
        } catch (Exception ex) {
            element(identifier, ORDER_BY_DESC);
            return false;
        }
    }

    private void orderBy(boolean ascensing) {
        if (isAscending() != ascensing) {
            element(identifier).click();
        }
    }

}
