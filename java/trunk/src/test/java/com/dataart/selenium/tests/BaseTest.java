package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BrowserType;
import com.dataart.selenium.framework.Settings;
import com.dataart.selenium.pages.*;
import org.testng.annotations.*;

public class BaseTest {
    private static Settings settings = new Settings();

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        BasePage.driver = settings.getDriver();
        BasePage.settings = settings;
        BasePage.driver.get(settings.getBaseUrl());
        if (!settings.getBrowser().equals(BrowserType.HTMLUNIT)) {
            BasePage.driver.manage().window().maximize();
        }
    }

    @AfterSuite(alwaysRun = true)
    public static void afterClass() {
        BasePage.driver.quit();

    }
}
