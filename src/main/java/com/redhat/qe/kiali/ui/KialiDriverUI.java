package com.redhat.qe.kiali.ui;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Slf4j
public class KialiDriverUI extends RemoteWebDriver {

    private static final String ENSURE_PAGE_SAFE = "return {"
            + "jquery: (typeof jQuery === \"undefined\") ? true : jQuery.active < 1,"
            + "prototype: (typeof Ajax === \"undefined\") ? true : Ajax.activeRequestCount < 1,"
            + "document: document.readyState == \"complete\""
            + "}";

    private CommonUtils utils = new CommonUtils();

    public KialiDriverUI() {
        super();
    }

    public KialiDriverUI(Capabilities capabilities) {
        super(capabilities);
    }

    public KialiDriverUI(CommandExecutor commandExecutor, Capabilities capabilities) {
        super(commandExecutor, capabilities);
    }

    public KialiDriverUI(URL remoteAddress, Capabilities capabilities) {
        super(remoteAddress, capabilities);
    }

    protected void ensurePageSafe() {
        long timeOut = 1000 * 10;
        while (timeOut > 0) {
            if (isPageReady()) {
                return;
            }
            utils.sleep(200);
            timeOut -= 200;
        }
        _logger.warn("Looks like page not ready for more than {}ms", timeOut);
    }

    @Override
    public WebElement findElement(By by) {
        ensurePageSafe();
        _logger.trace("{}", by.toString());
        return super.findElement(by);
    }

    @Override
    public List<WebElement> findElements(By by) {
        ensurePageSafe();
        _logger.trace("{}", by.toString());
        return super.findElements(by);
    }

    private boolean isPageReady() {
        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) this.executeScript(ENSURE_PAGE_SAFE);
        _logger.trace("ENSURE_PAGE_SAFE: {}", result);
        for (String key : result.keySet()) {
            if (!(Boolean) result.get(key)) {
                return false;
            }
        }
        return true;
    }
}
