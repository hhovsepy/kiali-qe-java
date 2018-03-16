package com.redhat.qe.kiali.ui.components;

import java.util.List;

import com.redhat.qe.kiali.model.SortOption;
import com.redhat.qe.kiali.ui.SwsDriverUI;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class SortDropdown extends UIAbstract {
    private String identifier = "//*[contains(@class, \"form-group\")]/*[contains(@class, \"dropdown\")]/../button/*[contains(@class, \"sort-direction\")]/../..";

    private Dropdown dropdown;
    private Sort sort;

    public SortDropdown(SwsDriverUI driver) {
        this(driver, null);
    }

    public SortDropdown(SwsDriverUI driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
        dropdown = new Dropdown(driver, this.identifier + "/*[contains(@class, \"dropdown\")]");
        sort = new Sort(driver, this.identifier + "/button/*[contains(@class, \"sort-direction\")]/..");
    }

    public List<String> options() {
        return dropdown.options();
    }

    public void orderBy(boolean ascending) {
        if (ascending) {
            sort.ascending();
        } else {
            sort.descending();
        }
    }

    public void select(SortOption sortOption) {
        dropdown.select(sortOption.getOption());
        if (sortOption.isAscending() != null) {
            orderBy(sortOption.isAscending());
        }
    }

    public void select(String option) {
        select(SortOption.builder().option(option).build());
    }

    public SortOption selected() {
        return SortOption.builder()
                .option(dropdown.selected())
                .ascending(sort.isAscending())
                .build();
    }
}
