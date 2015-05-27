package com.dataart.selenium.pages;

import com.dataart.selenium.framework.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.dataart.selenium.framework.Utils.isElementPresent;

public class BasePage {

    public static WebDriver driver;
    public static Settings settings;
    public final static By flash = By.xpath("//p[@class='flash']");


    public static <T extends BasePage> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }

    public static HeaderPage onHeader() {
        return initPage(HeaderPage.class);
    }

    public static String getFlashMessage() {
        String notext = "FlashMessage has no text ...";
        if (isElementPresent(flash)) {
            return driver.findElement(flash).getText();
        }
        return notext;
    }
}
