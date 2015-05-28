package com.dataart.selenium.pages;

import org.openqa.selenium.By;

public class PopularPanel extends BasePage{


public AppDetailPage openApplicationWithTitle(String appTitle){
    String appOnPopularPanelLinkXpath = "//div[@class='popular-app']/a[div[text()='"+appTitle+"']]";
    driver.findElement(By.xpath(appOnPopularPanelLinkXpath)).click();
    return initPage(AppDetailPage.class);
}
}
