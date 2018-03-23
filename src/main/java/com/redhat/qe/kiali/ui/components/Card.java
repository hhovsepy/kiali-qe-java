package com.redhat.qe.kiali.ui.components;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Card extends UIAbstract {

    protected String identifier = "//*[contains(@class, \"card-pf\")]";

    protected static final String TITLE = ".//*[contains(@class, \"card-pf-title\")]";
    protected static final String BODY = ".//*[contains(@class, \"card-pf-body\")]";

    public Card(KialiWebDriver driver) {
        this(driver, null);
    }

    public Card(KialiWebDriver driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    public String title() {
        return element(identifier, TITLE).getText();
    }

    public String body() {
        return element(identifier, BODY).getText();
    }
}
