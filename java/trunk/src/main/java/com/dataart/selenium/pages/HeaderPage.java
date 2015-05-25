package com.dataart.selenium.pages;

import org.openqa.selenium.By;

public class HeaderPage extends BasicPage {

    public String getWelcomeMessage() {
        return driver.findElement(By.xpath("//div[@class='header']/div[@class='welcome']")).getText();
    }

}
