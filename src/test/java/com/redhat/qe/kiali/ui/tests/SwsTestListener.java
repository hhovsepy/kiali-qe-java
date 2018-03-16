package com.redhat.qe.kiali.ui.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Slf4j
public class SwsTestListener extends TestListenerAdapter implements ISuiteListener {
    private static final String ZALENIUM_MESSAGE = "zaleniumMessage";
    private static final String ZALENIUM_TEST_STATUS = "zaleniumTestPassed";

    private int failures = 0;
    private int success = 0;
    private int skipped = 0;

    private Cookie cookie(String key, String value) {
        return new Cookie(key, value);
    }

    // test listeners

    @Override
    public void onFinish(ISuite suite) {
        DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_MESSAGE, "[S] End: " + suite.getName()));
        _logger.info("*** END[S]- {}", suite.getName());
        _logger.info("*** Tests run[total:{}, failures:{}, skipped:{}, success:{}]",
                (failures + skipped + success), failures, skipped, success);
        if (failures > 0) {
            DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_TEST_STATUS, "false"));
        } else {
            DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_TEST_STATUS, "true"));
        }

        // call tearDown tasks
        DriverFactory.tearDown();
    }

    @Override
    public void onFinish(ITestContext context) {
        DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_MESSAGE, "[T] End: " + context.getName()));
        _logger.info("*** END[T] - {}", context.getName());
    }

    @Override
    public void onStart(ISuite suite) {
        //DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_MESSAGE, "[S] Start: " + suite.getName()));
        _logger.info("*** START[S]- {}", suite.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_MESSAGE, "[T] Start: " + context.getName()));
        _logger.info("*** START[T] - {}", context.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failures++;

        // report to video test failed
        DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_MESSAGE, "[M] Failed: " + result.getName()));
        _logger.info("*** FAILED[M] - {}", result.getName());

        // take screenshot
        // TODO: add code to take screenshot with reports
        File screenshot = DriverFactory.driverUI().getScreenshotAs(OutputType.FILE);
        try {
            String distinationName = "/tmp/selenium/sl_" + result.getName() + "_" + System.currentTimeMillis()
                    + ".png";
            FileUtils.copyFile(screenshot, new File(distinationName));
        } catch (IOException ex) {
            _logger.error("Exception,", ex);
        }

        // reload browser in failure
        DriverFactory.driverUI().navigate().refresh();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skipped++;
        DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_MESSAGE, "[M] Skipped: " + result.getName()));
        _logger.info("*** SKIPPED[M] - {}", result.getName());
    }

    // suite listener

    @Override
    public void onTestStart(ITestResult result) {
        DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_MESSAGE, "[M] Start: " + result.getName()));
        _logger.info("*** START[M] - {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        success++;
        DriverFactory.driverUI().manage().addCookie(cookie(ZALENIUM_MESSAGE, "[M] Success: " + result.getName()));
        _logger.info("*** SUCCESS[M] - {}", result.getName());
    }

}
