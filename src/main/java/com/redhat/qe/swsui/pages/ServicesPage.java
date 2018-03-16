package com.redhat.qe.swsui.pages;

import com.redhat.qe.swsui.SwsDriverUI;
import com.redhat.qe.swsui.components.Filter;
import com.redhat.qe.swsui.components.ListViewService;
import com.redhat.qe.swsui.components.Pagination;
import com.redhat.qe.swsui.components.SortDropdown;
import com.redhat.qe.swsui.enums.RootPageEnum.MAIN_MENU;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class ServicesPage extends RootPage {

    private Filter filter;

    private SortDropdown sort;
    private ListViewService serviceList;
    private Pagination pagination;

    public ServicesPage(SwsDriverUI driver) {
        super(driver);
        reload();
    }

    public Filter filter() {
        return filter;
    }

    public Pagination pagination() {
        return pagination;
    }

    @Override
    public void reload() {
        super.reload();
        if (!menu().selected().equals(MAIN_MENU.SERVICES.getText())) {
            menu().select(MAIN_MENU.SERVICES.getText());
        }
        filter = new Filter(driver);
        sort = new SortDropdown(driver);
        serviceList = new ListViewService(driver);
        pagination = new Pagination(driver);
    }

    public ListViewService serviceList() {
        return serviceList;
    }

    public SortDropdown sort() {
        return sort;
    }

}
