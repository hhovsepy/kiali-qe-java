package com.redhat.qe.swsui.tests.services;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.qe.model.KeyValue;
import com.redhat.qe.model.Namespace;
import com.redhat.qe.model.Service;
import com.redhat.qe.model.SortOption;
import com.redhat.qe.swsui.components.Filter;
import com.redhat.qe.swsui.components.Pagination;
import com.redhat.qe.swsui.components.SortDropdown;
import com.redhat.qe.swsui.enums.ServicesPageEnum.FILTER;
import com.redhat.qe.swsui.enums.ServicesPageEnum.PERPAGE;
import com.redhat.qe.swsui.enums.ServicesPageEnum.SORT;
import com.redhat.qe.swsui.pages.ServicesPage;
import com.redhat.qe.swsui.tests.TestAbstract;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Slf4j
public class TestServicePage extends TestAbstract {
    private ServicesPage services;

    private void checkNumberOfServices(Integer perPage) {
        Pagination pagination = services.pagination();
        pagination.perPage(perPage);
        Assert.assertEquals(pagination.perPage(), perPage);
        List<Service> serviceList = services.serviceList().items();
        Assert.assertEquals(serviceList.size(), perPage.intValue());
    }

    /**
     * Navigate to services page.
     */
    @BeforeClass
    public void loadServicesPage() {
        services = new ServicesPage(driverUI());
        rootPage = services;
    }

    /**
     * Test description:
     *   Tests services page filter functionality. Like apply and remove filter
     * Steps:
     *      - check filter options. we have only two type of filter options[Namespace, Server Name]
     *      - check available filters, by default NONE
     *      - check available options for Namesapce, if NONE fail it
     *      - compare namespaces with REST API, count and names
     *      - apply filter1 and check applied filters and filtered data
     *      - apply filter2, if we have enough data and test filters and data
     *      - remove filter2, if applied and check filter and data
     *      - remove filter1 and check applied filters
     */
    @Test
    public void testFilterFeature() {
        // load defined items
        FILTER[] filtersDefined = FILTER.values();
        Filter filter = services.filter();
        // test available filter options
        List<String> filters = filter.filters();
        _logger.debug("Available filter options:{}", filters);
        Assert.assertEquals(filters.size(), filtersDefined.length);
        for (String item : filters) {
            Assert.assertNotNull(FILTER.fromText(item));
        }

        // get namespaces from UI and REST compare both
        List<String> namespacesUI = filter.options(FILTER.NAMESPACE.getText());
        // remove the default title from items list
        if (namespacesUI.size() > 1) {
            namespacesUI.remove("Filter by Namespace");
        }
        // TODO: sometime list not loaded properly. need to fix this on pageObject.
        // For now workaround, retry once again
        if (!(namespacesUI.size() > 1)) {
            _logger.debug("First try failed. Retrying once again. **Fix this on PageObject code**");
            filter.sleep(500);
            namespacesUI = filter.options(FILTER.NAMESPACE.getText());
        }
        _logger.debug("Namespace UI list:{}", namespacesUI);
        // fail test if there is no namespace available
        Assert.assertTrue(namespacesUI.size() > 1, "There is no name space available in UI!");

        // get namespace from REST API
        List<Namespace> namespacesREST = restClient().namespaces();
        _logger.debug("Namespace REST list:{}", namespacesREST);
        // assert the size in UI and in REST
        Assert.assertEquals(namespacesUI.size(), namespacesREST.size(), "number of namespaces in UI and in REST");
        // assert names in UI and REST
        for (Namespace namespace : namespacesREST) {
            Assert.assertTrue(namespacesUI.contains(namespace.getName()), "REST Namespace in UI:" + namespace);
        }

        // test apply filter
        KeyValue filter1 = KeyValue.builder().key(FILTER.NAMESPACE.getText()).value("istio-system").build();
        filter.apply(filter1);
        List<KeyValue> activeFilters = filter.activeFilters();
        _logger.debug("Active filters:{}", activeFilters);
        Assert.assertEquals(activeFilters.size(), 1);
        Assert.assertTrue(contains(activeFilters, filter1));
        // test values after this filter
        List<Service> serviceItems = services.serviceList().items();
        _logger.debug("Service items:{}", serviceItems);
        for (Service item : serviceItems) {
            Assert.assertEquals(item.getNamespace(), "istio-system");
        }
        // TODO: test on next page as well
        // add one more filter
        if (serviceItems.size() > 0) {
            Service selectedItem = serviceItems.get(random(serviceItems.size()));
            KeyValue filter2 = KeyValue.builder()
                    .key(FILTER.SERVICE_NAME.getText())
                    .value(selectedItem.getName())
                    .build();
            filter.apply(filter2);
            activeFilters = filter.activeFilters();
            _logger.debug("Active filters:{}", activeFilters);
            Assert.assertEquals(activeFilters.size(), 2);
            Assert.assertTrue(contains(activeFilters, filter1));
            Assert.assertTrue(contains(activeFilters, filter2));
            serviceItems = services.serviceList().items();
            _logger.debug("Service items:{}", serviceItems);
            for (Service item : serviceItems) {
                Assert.assertTrue(item.getName().contains(selectedItem.getName()));
                Assert.assertEquals(item.getNamespace(), "istio-system");
            }
            // remove filter 2
            filter.remove(filter2);
            activeFilters = filter.activeFilters();
            _logger.debug("Active filters:{}", activeFilters);
            Assert.assertEquals(activeFilters.size(), 1);
            Assert.assertTrue(contains(activeFilters, filter1));
            serviceItems = services.serviceList().items();
            _logger.debug("Service items:{}", serviceItems);
            for (Service item : serviceItems) {
                Assert.assertEquals(item.getNamespace(), "istio-system");
            }
        }
        // remove filter1
        filter.remove(filter1);
        activeFilters = filter.activeFilters();
        _logger.debug("Active filters:{}", activeFilters);
        Assert.assertEquals(activeFilters.size(), 0);
    }

    /**
     * Test description:
     *   Tests services pagination functionality
     * Steps:
     *      - check per page options.
     *      - check total number of items available in the page
     *      - compare total items with rest client
     *      - if the total item is greater than per-page continue the test
     *      - Check for all the defined per page option
     *      - TODO: first, last, next, previous options
     *      - TODO: check total number of items listed properly
     */
    @Test
    public void testPaginationFeauture() {
        // load defined items
        PERPAGE[] optionsDefined = PERPAGE.values();
        Pagination pagination = services.pagination();
        // per page options
        List<Integer> perPageOptions = pagination.perPageOptions();
        Assert.assertEquals(perPageOptions.size(), optionsDefined.length);
        for (Integer value : perPageOptions) {
            Assert.assertNotNull(PERPAGE.fromValue(value));
        }
        // check total number of items
        int totalItems = pagination.totalItems();
        // get total number of items from REST API
        int totalItemsRestApi = 0;
        for (Namespace namespace : restClient().namespaces()) {
            totalItemsRestApi += restClient().services(namespace.getName()).getServices().size();
        }
        Assert.assertEquals(totalItems, totalItemsRestApi);
        // can execute this test if we have more than per page items as total
        for (PERPAGE perpage : optionsDefined) {
            if (totalItems > perpage.getValue()) {
                checkNumberOfServices(perpage.getValue());
            }
        }
    }

    /**
     * Test description:
     *   Tests services page sorting functionality
     * Steps:
     *      - check available options count and items, for now[Namespace, Server Name]
     *      - set an option and verify
     *      - TODO: verify services order after sort option changed
     */
    @Test
    public void testSortingFeauture() {
        // load defined items
        SORT[] optionsDefined = SORT.values();
        SortDropdown sort = services.sort();
        // check available options
        List<String> options = sort.options();
        _logger.debug("Sorting options:{}", options);
        Assert.assertEquals(options.size(), optionsDefined.length);
        for (String item : options) {
            Assert.assertNotNull(SORT.fromText(item));
        }
        // set an option and verify
        SortOption set = SortOption.builder().option(SORT.SERVICE_NAME.getText()).ascending(false).build();
        sort.select(set);
        SortOption selected = sort.selected();
        Assert.assertEquals(selected.getOption(), set.getOption());
        Assert.assertEquals(selected.isAscending(), set.isAscending());
        // TODO: verify services order after sort option changed
    }
}
