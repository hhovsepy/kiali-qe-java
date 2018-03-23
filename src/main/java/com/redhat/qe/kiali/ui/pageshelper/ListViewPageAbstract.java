package com.redhat.qe.kiali.ui.pageshelper;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.components.Filter;
import com.redhat.qe.kiali.ui.components.Pagination;
import com.redhat.qe.kiali.ui.components.SortDropdown;
import com.redhat.qe.kiali.ui.enums.RootPageEnum.MAIN_MENU;
import com.redhat.qe.kiali.ui.pages.RootPage;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public abstract class ListViewPageAbstract<T> extends RootPage {

    private Filter filter;
    private SortDropdown sort;
    protected T list;
    private Pagination pagination;

    public ListViewPageAbstract(KialiWebDriver driver, MAIN_MENU thisPageMenu) {
        super(driver, thisPageMenu);
        this.thisPageMenu = thisPageMenu;
    }

    @Override
    protected void load() {
        super.load();
        if (!menu().selected().equals(thisPageMenu.getText())) {
            menu().select(thisPageMenu.getText());
        }
        filter = new Filter(driver);
        sort = new SortDropdown(driver);
        pagination = new Pagination(driver);
    }

    public Filter filter() {
        return filter;
    }

    public SortDropdown sort() {
        return sort;
    }

    public T list() {
        return list;
    }

    public Pagination pagination() {
        return pagination;
    }
}
