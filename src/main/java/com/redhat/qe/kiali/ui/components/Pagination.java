package com.redhat.qe.kiali.ui.components;

import java.util.List;

import com.redhat.qe.kiali.ui.KialiWebDriver;
import com.redhat.qe.kiali.ui.UIAbstract;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class Pagination extends UIAbstract {

    private static final String PER_PAGE_DROPDOWN = "//*[contains(@class, \"pagination-pf-pagesize\")]";

    private static final String TOTAL_ITEMS = ".//*[contains(@class, \"pagination-pf-items-total\")]";
    private static final String TOTAL_PAGES = ".//*[contains(@class, \"pagination pagination-pf-forward\")]/..//*[contains(@class, \"pagination-pf-pages\")]";
    private static final String CURRENT_PAGE = "//input[contains(@class, \"pagination-pf-page\")]";
    private static final String FIRST_PAGE = "//*[@title=\"First Page\"]";
    private static final String LAST_PAGE = "//*[@title=\"Last Page\"]";
    private static final String NEXT_PAGE = "//*[@title=\"Next Page\"]";
    private static final String PREVIOUS_PAGE = "//*[@title=\"Previous Page\"]";
    private String identifier = "//*[contains(@class, \"list-view-pf-pagination\") and contains(@class, \"content-view-pf-pagination\")]";

    public Pagination(KialiWebDriver driver) {
        this(driver, null);
    }

    public Pagination(KialiWebDriver driver, String identifier) {
        super(driver);
        if (identifier != null) {
            this.identifier = identifier;
        }
    }

    public Integer currentPage() {
        return integerValueOf(new Input(driver, identifier + CURRENT_PAGE).value());
    }

    public void moveToFirstPage() {
        element(identifier, FIRST_PAGE).click();
    }

    public void moveToLastPage() {
        element(identifier, LAST_PAGE).click();
    }

    public void moveToNextPage() {
        element(identifier, NEXT_PAGE).click();
    }

    public void moveToPreviousPage() {
        element(identifier, PREVIOUS_PAGE).click();
    }

    public void moveToPage(Integer pageNo) {
        new Input(driver, identifier + CURRENT_PAGE).set(String.valueOf(pageNo));
    }

    public Integer totalItems() {
        return integerValueOf(element(identifier, TOTAL_ITEMS).getText());
    }

    public Integer pages() {
        return integerValueOf(element(identifier, TOTAL_PAGES).getText());
    }

    public Integer perPage() {
        return integerValueOf(new Dropdown(driver, identifier + PER_PAGE_DROPDOWN).selected());
    }

    public void perPage(Integer perPage) {
        new Dropdown(driver, identifier + PER_PAGE_DROPDOWN).select(String.valueOf(perPage));
    }

    public List<Integer> perPageOptions() {
        Dropdown perpDropdown = new Dropdown(driver, identifier + PER_PAGE_DROPDOWN);
        return integerList(perpDropdown.options());
    }

}
