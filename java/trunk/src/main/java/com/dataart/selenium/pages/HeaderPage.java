package com.dataart.selenium.pages;

import com.dataart.selenium.models.User;
import org.openqa.selenium.By;

import static com.dataart.selenium.framework.Utils.isElementPresent;
import static org.testng.AssertJUnit.assertTrue;


public class HeaderPage extends BasePage {

    public final static By myApplicationsLink = By.xpath("//a[text()='My applications']");
    public final static By logoutLink = By.xpath("//a[contains(text(), 'Logout')]");



    public String getWelcomeMessage() {
        return driver.findElement(By.xpath("//div[@class='header']/div[@class='welcome']")).getText();
    }

    public MyApplicationsPage clickMyApplicationsLink() {
        driver.findElement(myApplicationsLink).click();
        return initPage(MyApplicationsPage.class);
    }

    public HomePage clickToHomePageLink(){
        driver.findElement(By.xpath("//a[text()='Home']")).click();
        return initPage(HomePage.class);
    }

    public void assertHeader(User user){
        assertTrue("WelcomeMessage is wrong!!!", getWelcomeMessage().equals("Welcome " + user.getFname() + " " + user.getLname()));
    }

    public LoginPage forceLogout() {
        driver.get(settings.getBaseUrl());
        if (isElementPresent(logoutLink)) {
            driver.findElement(logoutLink).click();
        }
        return initPage(LoginPage.class);
    }


}
