package com.dataart.selenium.pages;

import org.openqa.selenium.By;

public class HeaderPage extends BasicPage {

    public final static By byMyApplicationsLink = By.xpath("//a[text()='My applications']");


    public String getWelcomeMessage() {
        return driver.findElement(By.xpath("//div[@class='header']/div[@class='welcome']")).getText();
    }

    public MyApplicationsPage toMyApplicationsPage() {
        driver.findElement(byMyApplicationsLink).click();
        return initPage(MyApplicationsPage.class);
    }

}
