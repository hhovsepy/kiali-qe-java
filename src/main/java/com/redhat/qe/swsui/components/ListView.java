package com.redhat.qe.swsui.components;

import java.util.List;

import com.redhat.qe.swsui.SwsDriverUI;
import com.redhat.qe.swsui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public abstract class ListView<T> extends UIAbstract {
    protected static final String ITEMS = ".//*[contains(@class, \"list-group-item\")]//*[contains(@class, \"list-view-pf-body\")]";
    protected static final String ITEM_TEXT = ".//*[contains(@class, \"list-group-item-heading\")]/span";
    // private static final String ITEM_DESCRIPTION = ITEM_NAME + "/small";

    protected String identifier = "//*[contains(@class, \"list-view-pf\") and contains(@class, \"list-view-pf-view\")]";

    public ListView(SwsDriverUI driver) {
        this(driver, null);
    }

    public ListView(SwsDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    abstract public List<T> items();

    abstract public void open(String item);

    abstract public void open(T item);
}
