package com.redhat.qe.kiali.ui.tests;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.redhat.qe.kiali.model.KeyValue;
import com.redhat.qe.kiali.rest.KialiRestClient;
import com.redhat.qe.kiali.ui.KialiDriverUI;
import com.redhat.qe.kiali.ui.pages.RootPage;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public abstract class TestAbstract {
    protected RootPage rootPage;
    protected boolean reloadPageBeforeMethod = true;

    public boolean contains(List<Integer> items, Integer target) {
        for (Integer item : items) {
            if (item.intValue() == target.intValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(List<KeyValue> items, KeyValue target) {
        for (KeyValue item : items) {
            if (item.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(List<String> items, String target) {
        for (String item : items) {
            if (item.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public KialiDriverUI driverUI() {
        return DriverFactory.driverUI();
    }

    public int random() {
        return random(0, 100000);
    }

    // utils

    public int random(int bound) {
        return random(0, bound);
    }

    public int random(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    @BeforeMethod
    public void reloadPage() {
        if (reloadPageBeforeMethod) {
            if (rootPage != null) {
                rootPage.reload();
            }
        }
    }

    public KialiRestClient restClient() {
        return DriverFactory.restClient();
    }

    @BeforeSuite
    public void setupTasks(ITestContext ctx) throws MalformedURLException {
        DriverFactory.initialize(ctx.getSuite().getName());
    }

    @AfterSuite
    public void tearDownTasks() {
        // Selenium driver close will be handled by Listener
    }

}
