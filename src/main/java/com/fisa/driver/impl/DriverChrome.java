package com.fisa.driver.impl;

import com.fisa.driver.DriverBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverChrome implements DriverBrowser {

    private static final String WEB_DRIVER_PARAM = "webdriver.chrome.driver";
    private String path_driver;

    private WebDriver webDriver;

    public DriverChrome(String path_driver) {
        this.path_driver = path_driver;
        System.setProperty(WEB_DRIVER_PARAM, path_driver);
        webDriver = new ChromeDriver();
    }
    @Override
    public WebDriver getDriver() {
        return webDriver;
    }
}
