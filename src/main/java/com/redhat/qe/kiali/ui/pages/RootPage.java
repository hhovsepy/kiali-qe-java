package com.redhat.qe.kiali.ui.pages;

import com.redhat.qe.kiali.ui.KialiDriverUI;
import com.redhat.qe.kiali.ui.UIAbstract;
import com.redhat.qe.kiali.ui.components.MainMenu;
import com.redhat.qe.kiali.ui.components.Navbar;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class RootPage extends UIAbstract {
    private MainMenu menu;

    public RootPage(KialiDriverUI driver) {
        super(driver);
    }

    public MainMenu menu() {
        return menu;
    }

    public Navbar navbar() {
        return menu().navbar();
    }

    public void reload() {
        // refresh browser
        driver.navigate().refresh();
        // load menus
        menu = new MainMenu(driver);
    }

}
