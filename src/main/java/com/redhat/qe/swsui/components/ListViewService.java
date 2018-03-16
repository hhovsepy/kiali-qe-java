package com.redhat.qe.swsui.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.redhat.qe.model.Service;
import com.redhat.qe.swsui.SwsDriverUI;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ListViewService extends ListView<Service> {

    private static final String SERVICE = ITEMS + "//span[text()=\"{0}\"]";
    private static final String SERVICE_WITH_NAMESPACE = SERVICE + "/small[text()=\"{1}\"]";

    public ListViewService(SwsDriverUI driver) {
        this(driver, null);
    }

    public ListViewService(SwsDriverUI driver, String identifier) {
        super(driver, identifier);
    }

    @Override
    public List<Service> items() {
        ArrayList<Service> items = new ArrayList<Service>();
        for (WebElement rawItem : elements(identifier, ITEMS)) {
            String[] text = element(rawItem, ITEM_TEXT).getText().split("\\n");
            items.add(Service.builder()
                    .name(text[0])
                    .namespace(text[1])
                    .build());
        }
        return items;
    }

    @Override
    public void open(Service service) {
        if (service.getName() != null && service.getNamespace() != null) {
            element(identifier, SERVICE_WITH_NAMESPACE, service.getName(), service.getNamespace()).click();
        } else if (service.getName() != null) {
            element(identifier, SERVICE, service.getName()).click();
        }
    }

    @Override
    public void open(String serviceName) {
        open(Service.builder().name(serviceName).build());
    }
}
