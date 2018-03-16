package com.redhat.qe.kiali.ui.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.model.KeyValue;
import com.redhat.qe.kiali.ui.KialiDriverUI;
import com.redhat.qe.kiali.ui.UIAbstract;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Slf4j
public class Filter extends UIAbstract {
    private static final String FILTER_DROPDOWN = "//*[contains(@class, \"dropdown\")]";
    private static final String VALUE_INPUT = ".//input";
    private static final String VALUE_DROPDOWN = ".//*[contains(@class, \"filter-pf-select\")]";

    // active filter
    private static final String AF_IDENTIFIER = "//*[contains(@class, \"toolbar-pf-results\")]";
    private static final String AF_ITEMS = ".//*[contains(@class, \"list-inline\")]//*[contains(@class, \"label\")]";
    private static final String AF_CLEAR = ".//*[contains(@class, \"list-inline\")]//*[contains(@class, \"label\") and contains(text(), \"{0}: {1}\")]//*[contains(@class, \"pficon-close\")]";
    private static final String AF_CLEAR_ALL = ".//a[text()=\"Clear All Filters\"]";

    private String identifier = "//*[contains(@class, \"toolbar-pf-actions\")]//*[contains(@class, \"toolbar-pf-filter\")]";

    private Dropdown filters;

    public Filter(KialiDriverUI driver) {
        this(driver, null);
    }

    public Filter(KialiDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
        filters = new Dropdown(driver, this.identifier + FILTER_DROPDOWN);
    }

    public List<KeyValue> activeFilters() {
        ArrayList<KeyValue> filters = new ArrayList<KeyValue>();
        if (!isElementPresent(AF_IDENTIFIER)) {
            return filters;
        }
        try {
            for (WebElement el : elements(AF_IDENTIFIER, AF_ITEMS)) {
                String[] text = el.getText().split("\\n")[0].split(":", 2);
                filters.add(KeyValue.builder()
                        .key(text[0].trim())
                        .value(text[1].trim())
                        .build());
            }
        } catch (Exception ex) {
            _logger.error("Exception,", ex);
        }

        return filters;
    }

    public void apply(KeyValue filter) {
        select(filter.getKey());
        if (isElementPresent(identifier, VALUE_INPUT)) {
            Input valueInput = new Input(driver, identifier + "/" + VALUE_INPUT);
            valueInput.set(filter.getValue());
        } else if (isElementPresent(identifier, VALUE_DROPDOWN)) {
            Dropdown valueDropdown = new Dropdown(driver, identifier + "/" + VALUE_DROPDOWN);
            valueDropdown.select(filter.getValue());
        } else {
            throw new RuntimeException("Input [or] Dropdown option not found!");
        }
    }

    public void clearAll() {
        element(AF_IDENTIFIER, AF_CLEAR_ALL).click();
    }

    public List<String> filters() {
        return filters.options();
    }

    public List<String> options(String filter) {
        select(filter);
        if (isElementPresent(identifier, VALUE_DROPDOWN)) {
            Dropdown valueDropdown = new Dropdown(driver, identifier + "/" + VALUE_DROPDOWN);
            return valueDropdown.options();
        }
        return new ArrayList<String>();
    }

    public void remove(KeyValue filter) {
        element(AF_IDENTIFIER, AF_CLEAR, filter.getKey(), filter.getValue()).click();
    }

    public void select(String filter) {
        filters.select(filter);
    }
}
