package com.dataart.selenium.pages;

import com.dataart.selenium.models.User;
import org.openqa.selenium.By;

import static org.fest.assertions.Assertions.assertThat;

public class HeaderPage extends BasicPage {

    public final static By myApplicationsLink = By.xpath("//a[text()='My applications']");


    public String getWelcomeMessage() {
        return driver.findElement(By.xpath("//div[@class='header']/div[@class='welcome']")).getText();
    }

    public MyApplicationsPage toMyApplicationsPage() {
        driver.findElement(myApplicationsLink).click();
        return initPage(MyApplicationsPage.class);
    }

    public void assertHeader(User user){
        assertThat(getWelcomeMessage()).isEqualTo("Welcome " + user.getFname() + " " + user.getLname());
    }

}
