package com.dataart.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public final static By pageTitle = By.xpath("//div[text()='Popular apps']");


    @FindBy(xpath = "//div[@class='apps']/div[1]/a")
    WebElement firstAppDetailPageLink;

    public AppDetailPage clickFirstAppDetailPageLink() {
        firstAppDetailPageLink.click();
        return initPage(AppDetailPage.class);
    }

    public AppDetailPage openApplicationWithTitle(String appTitle){
        String appDetailLinkXpath = "//div[*[contains(.,'"+appTitle+"')]]/a";
        driver.findElement(By.xpath(appDetailLinkXpath)).click();
        return initPage(AppDetailPage.class);
    }

    public AppDetailPage openApplicationWithNumber(Integer number){
        String appDetailLinkXpath = "//div[@class='app']["+number+"]/a";
        driver.findElement(By.xpath(appDetailLinkXpath)).click();
        return initPage(AppDetailPage.class);
    }

}

