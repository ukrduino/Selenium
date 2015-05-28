package com.dataart.selenium.framework;

import com.dataart.selenium.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// GET DATE & TIME IN ANY FORMAT
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    private static WebDriver driver = BasePage.driver;
    // for creating nowDateKey
    public static final String DATE_FORMAT_NOW = "yy-MM-dd HH:mm:ss";

    public static WebElement elementByXpathIfPresentInSeconds(String xpath, Integer secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        System.out.println(element.getText());

        return element;
    }

    public static boolean isElementPresent(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public static boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    public static String getText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }


//http://stackoverflow.com/questions/2942857/how-to-convert-current-date-into-string-in-java
//http://stackoverflow.com/a/11149783
    public static String getStringCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

        return sdf.format(cal.getTime()).replaceAll("[^a-zA-Z0-9]", "");
    }

}

