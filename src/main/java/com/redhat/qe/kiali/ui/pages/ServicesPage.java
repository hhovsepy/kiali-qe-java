package com.redhat.qe.kiali.ui.pages;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.components.ListViewService;
import com.redhat.qe.kiali.ui.enums.RootPageEnum.MAIN_MENU;
import com.redhat.qe.kiali.ui.pageshelper.ListViewPageAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ServicesPage extends ListViewPageAbstract<ListViewService> {

    public ServicesPage(KialiWebDriver driver) {
        super(driver, MAIN_MENU.SERVICES);
    }

    @Override
    protected void load() {
        super.load();
        list = new ListViewService(driver);
    }

}
