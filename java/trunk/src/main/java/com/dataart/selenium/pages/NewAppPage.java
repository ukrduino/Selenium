package com.dataart.selenium.pages;

import com.dataart.selenium.models.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class NewAppPage extends BasePage{

    public final static By NewAppTitle = By.name("title");
    public final static By NewAppDescription = By.name("description");
    public final static By NewAppImageBrowseButton = By.name("image");
    public final static By NewAppIconBrowseButton = By.name("icon");
    public final static By CreateNewButton = By.xpath("//input[@value='Create']");



    @FindBy(name = "title")
    WebElement newAppTitle;

    @FindBy(name="description")
    WebElement newAppDescription;

    Select newAppCategory = new Select(driver.findElement(By.name("category")));

    @FindBy(name = "image")
    WebElement newAppImageBrowseButton;

    @FindBy(name = "icon")
    WebElement newAppIconBrowseButton;

    @FindBy(xpath = "//input[@value='Create']")
    WebElement createNewButton;

    public String getPageTitle(){
        return driver.findElement(By.xpath("//div[@class='content']/h1")).getText();
    }

    public HomePage createNewApplicationFrom(Application newAppModel){
        newAppTitle.sendKeys(newAppModel.getAppTitle());
        newAppDescription.sendKeys(newAppModel.getAppDescription());
        newAppCategory.selectByVisibleText(newAppModel.getCategory());
        newAppImageBrowseButton.sendKeys(newAppModel.getImagePath());
        newAppIconBrowseButton.sendKeys(newAppModel.getImagePath());
        createNewButton.click();
        return initPage(HomePage.class);
    }

}
