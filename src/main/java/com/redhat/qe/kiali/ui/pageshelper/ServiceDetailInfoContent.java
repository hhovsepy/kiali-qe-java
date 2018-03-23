package com.redhat.qe.kiali.ui.pageshelper;

import java.text.MessageFormat;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.components.Card;
import com.redhat.qe.kiali.ui.components.CardServiceDeployments;
import com.redhat.qe.kiali.ui.components.CardServiceDescription;
import com.redhat.qe.kiali.ui.components.Tab;
import com.redhat.qe.kiali.ui.enums.ServicePageEnum.TAB;
import com.redhat.qe.kiali.ui.pages.RootPage;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ServiceDetailInfoContent extends RootPage {

    private static final String CARDS_ROOT = "//*[@id=\"basic-tabs\"]//*[contains(@class, \"tab-content\")]//*[@id=\"basic-tabs-pane-1\"]//*[@class=\"card-pf\"]";
    private static final String CARD = CARDS_ROOT
            + "//*[contains(@class, \"card-pf-title\") and normalize-space(text())=\"{0}\"]/../..";
    private static final int EXPECTED_CARD_SIZE = 5;

    private Tab rootTab;

    private CardServiceDescription description;
    private CardServiceDeployments deployments;
    private Card sourceServices;
    private Card istioRouteRules;

    public ServiceDetailInfoContent(KialiWebDriver driver, Tab rootTab) {
        super(driver);
        this.rootTab = rootTab;
        loadThisPage(true);
    }

    private void loadThisPage(boolean force) {
        if (force || !rootTab.selected().equals(TAB.INFO.getText())) {
            rootTab.select(TAB.INFO.getText());
            List<WebElement> cards = elements(CARDS_ROOT);
            if (cards.size() != EXPECTED_CARD_SIZE) {
                throw new RuntimeException("Number of cards mismatch! Expected:" + EXPECTED_CARD_SIZE
                        + ", Available:" + cards.size());
            }
            description = new CardServiceDescription(driver, CARDS_ROOT);
            deployments = new CardServiceDeployments(driver, MessageFormat.format(CARD, "Deployments"));
            sourceServices = new Card(driver, MessageFormat.format(CARD, "Source Services"));
            istioRouteRules = new Card(driver, MessageFormat.format(CARD, "Istio Route Rules"));
        }
    }

    public CardServiceDescription description() {
        loadThisPage(false);
        return description;
    }

    public CardServiceDeployments deployments() {
        loadThisPage(false);
        return deployments;
    }

    public Card sourceServices() {
        loadThisPage(false);
        return sourceServices;
    }

    public Card istioRouteRules() {
        loadThisPage(false);
        return istioRouteRules;
    }

}
