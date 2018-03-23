package com.redhat.qe.kiali.ui.pages;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.UIAbstract;
import com.redhat.qe.kiali.ui.components.MainMenu;
import com.redhat.qe.kiali.ui.components.Navbar;
import com.redhat.qe.kiali.ui.components.PageHeader;
import com.redhat.qe.kiali.ui.enums.RootPageEnum.MAIN_MENU;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class RootPage extends UIAbstract {
    private MainMenu menu;
    private PageHeader pageHeader;

    protected MAIN_MENU thisPageMenu;

    public RootPage(KialiWebDriver driver, MAIN_MENU thisPageMenu) {
        super(driver);
        this.thisPageMenu = thisPageMenu;
        load();
    }

    public RootPage(KialiWebDriver driver) {
        super(driver);
        load();
    }

    public void reload() {
        // refresh browser
        driver.navigate().refresh();
        load();
    }

    protected void load() {
        menu = new MainMenu(driver);
        pageHeader = new PageHeader(driver, "//*[contains(@class, \"page-header\")]//h2");
    }

    public MainMenu menu() {
        return menu;
    }

    public Navbar navbar() {
        return menu().navbar();
    }

    public PageHeader pageHeader() {
        return pageHeader;
    }
}
