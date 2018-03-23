package com.redhat.qe.kiali.ui.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.redhat.qe.kiali.model.rules.Action;
import com.redhat.qe.kiali.model.rules.Rule;
import com.redhat.qe.kiali.ui.KialiWebDriver;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ListViewIstioMixer extends ListView<Rule> {

    private static final String ACTION_HEADER = ".//*[contains(@class, \"list-group-item-text\")]//strong[normalize-space(text())=\"{0}\"]/..";

    public ListViewIstioMixer(KialiWebDriver driver) {
        this(driver, null);
    }

    public ListViewIstioMixer(KialiWebDriver driver, String identifier) {
        super(driver, identifier);
    }

    @Override
    public List<Rule> items() {
        ArrayList<Rule> items = new ArrayList<Rule>();
        for (WebElement rawItem : elements(identifier, ITEMS)) {
            String[] text = element(rawItem, ITEM_TEXT).getText().split("\\n");

            String handler = element(rawItem, ACTION_HEADER, "Handler").getText().replaceFirst("Handler: ", "");
            String[] instancesArray = element(rawItem, ACTION_HEADER, "Instances").getText()
                    .replaceFirst("Instances: ", "").split(",");
            List<String> instances = new ArrayList<String>();
            for (String instance : instancesArray) {
                instances.add(instance.trim());
            }

            List<Action> actions = new ArrayList<Action>();
            actions.add(Action.builder()
                    .handler(handler)
                    .instances(instances)
                    .build());

            items.add(Rule.builder()
                    .name(text[0])
                    .namespace(text[1])
                    .actions(actions)
                    .build());
        }
        return items;
    }

    @Override
    public void open(Rule rule) {
        open(rule.getName(), rule.getNamespace());
    }
}
