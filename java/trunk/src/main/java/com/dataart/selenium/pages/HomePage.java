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

}

