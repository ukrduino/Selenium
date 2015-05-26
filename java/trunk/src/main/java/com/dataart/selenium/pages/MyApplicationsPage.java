package com.dataart.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyApplicationsPage extends BasicPage{

    @FindBy(xpath = "//a[text()='Click to add new application']")
    WebElement toNewAppPageLink;

    public NewAppPage toNewAppPage() {
        toNewAppPageLink.click();
        return initPage(NewAppPage.class);
    }


}

