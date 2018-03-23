package com.redhat.qe.kiali.ui.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.model.services.PodStatus;
import com.redhat.qe.kiali.model.services.Service;
import com.redhat.qe.kiali.ui.KialiWebDriver;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ListViewService extends ListView<Service> {

    private ComponentsHelper componentsHelper = null;

    public ListViewService(KialiWebDriver driver) {
        this(driver, null);
    }

    public ListViewService(KialiWebDriver driver, String identifier) {
        super(driver, identifier);
        componentsHelper = new ComponentsHelper(driver);
    }

    @Override
    public List<Service> items() {
        ArrayList<Service> items = new ArrayList<Service>();
        for (WebElement rawItem : elements(identifier, ITEMS)) {
            String[] text = element(rawItem, ITEM_TEXT).getText().split("\\n");

            PodStatus pod = componentsHelper.podStatus(rawItem);

            items.add(Service.builder()
                    .name(text[0])
                    .namespace(text[1])
                    .replicas(pod.getReplicas())
                    .availableReplicas(pod.getAvailableReplicas())
                    .replicasStatus(pod.getStatus())
                    .build());
        }
        return items;
    }

    @Override
    public void open(Service service) {
        open(service.getName(), service.getNamespace());
    }

}
