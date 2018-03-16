package com.redhat.qe.swsui.pages;

import com.redhat.qe.swsui.SwsDriverUI;
import com.redhat.qe.swsui.UIAbstract;
import com.redhat.qe.swsui.components.MainMenu;
import com.redhat.qe.swsui.components.Navbar;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class RootPage extends UIAbstract {
    private MainMenu menu;

    public RootPage(SwsDriverUI driver) {
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
