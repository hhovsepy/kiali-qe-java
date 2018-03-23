package com.redhat.qe.kiali.ui.pages;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.components.ListViewIstioMixer;
import com.redhat.qe.kiali.ui.enums.RootPageEnum.MAIN_MENU;
import com.redhat.qe.kiali.ui.pageshelper.ListViewPageAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class IstioMixerRulesPage extends ListViewPageAbstract<ListViewIstioMixer> {

    public IstioMixerRulesPage(KialiWebDriver driver) {
        super(driver, MAIN_MENU.ISTIO_MIXER);
    }

    @Override
    protected void load() {
        super.load();
        list = new ListViewIstioMixer(driver);
    }

}
