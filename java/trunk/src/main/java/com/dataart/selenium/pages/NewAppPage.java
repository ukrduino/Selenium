package com.dataart.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class NewAppPage extends BasePage{

    public final static By byPageTitle = By.xpath("//div[@class='content']/h1");
    public final static By byNewAppTitle = By.name("title");
    public final static By byNewAppDescription = By.name("description");
    public final static By byNewAppImageBrowseButton = By.name("image");
    public final static By byNewAppIconBrowseButton = By.name("icon");
    public final static By byCreateNewButton = By.xpath("//input[@value='Create']");


    @FindBy(xpath = "//div[@class='content']/h1")
    WebElement pageTitle;

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

}
