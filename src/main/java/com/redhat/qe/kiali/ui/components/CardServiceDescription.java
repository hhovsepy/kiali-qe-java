package com.redhat.qe.kiali.ui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.ui.KialiWebDriver;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class CardServiceDescription extends Card {

    private static final String COLUMN = "//*[contains(@class, \"progress-description\")]//*[normalize-space(text())=\"{0}\"]/../..";
    private ComponentsHelper componentsHelper;

    public CardServiceDescription(KialiWebDriver driver) {
        this(driver, null);
    }

    public CardServiceDescription(KialiWebDriver driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
        componentsHelper = new ComponentsHelper(driver);
    }

    public Map<String, String> labels() {
        return componentsHelper.labels(elements(identifier, BODY + COLUMN + "//*[name()=\"svg\"]", "Labels"));
    }

    public String type() {
        WebElement el = element(identifier, BODY + COLUMN + "//strong[normalize-space(text())=\"Type\"]/..", "Labels");
        return normalizeSpace(el.getText(), "").replaceFirst("Type", "");
    }

    public String ip() {
        WebElement el = element(identifier, BODY + COLUMN + "//strong[normalize-space(text())=\"IP\"]/..", "Labels");
        return normalizeSpace(el.getText(), "").replaceFirst("IP", "");
    }

    public List<String> ports() {
        List<String> ports = new ArrayList<String>();
        List<WebElement> portEls = elements(identifier, BODY + COLUMN + "//li", "Ports");
        for (WebElement el : portEls) {
            ports.add(el.getText());
        }
        return ports;
    }

    public List<String> endpoints() {
        List<String> endpoints = new ArrayList<String>();
        List<WebElement> epEls = elements(identifier, BODY + COLUMN + "//li", "Endpoints");
        for (WebElement el : epEls) {
            endpoints.add(el.getText());
        }
        return endpoints;
    }

    public String health() {
        WebElement el = element(identifier, BODY + COLUMN
                + "//*[name()=\"svg\"]//*[contains(@class, \"c3-chart-arcs-title\")]", "Health");
        return normalizeSpace(el.getText(), "");
    }
}
