package com.redhat.qe.kiali.ui.pages;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.components.Tab;
import com.redhat.qe.kiali.ui.pageshelper.ServiceDetailInfoContent;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */
public class ServiceDetailPage extends RootPage {

    private static final String URI = "/console/namespaces/{0}/services/{1}";

    private String namespace;
    private String serviceName;

    private Tab tab;
    private ServiceDetailInfoContent infoContent;

    public ServiceDetailPage(KialiWebDriver driver, String namespace, String serviceName) {
        super(driver);
        this.namespace = namespace;
        this.serviceName = serviceName;
        reload();
    }

    @Override
    public void reload() {
        super.reload();
        load();
    }

    @Override
    protected void load() {
        driver.navigateTo(format(URI, namespace, serviceName), false);
        super.load();
        tab = new Tab(driver);
        infoContent = new ServiceDetailInfoContent(driver, tab);
    }

    public Tab tab() {
        return tab;
    }

    public ServiceDetailInfoContent info() {
        return infoContent;
    }

}
