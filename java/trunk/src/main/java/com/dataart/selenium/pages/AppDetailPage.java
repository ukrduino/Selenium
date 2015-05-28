package com.dataart.selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AppDetailPage extends BasePage {

    @FindBy(className = "name")
    WebElement appTitle;

    @FindBy(xpath="//div[@class='application']/div[2]")
    WebElement appDescription;

    @FindBy(xpath="//div[@class='application']/div[3]")
    WebElement category;

    @FindBy(xpath="//div[@class='application']/div[4]")
    WebElement author;

    @FindBy(xpath="//div[@class='downloads']")
    WebElement numberOfDownloads;

    @FindBy(xpath="//div[@class='download-button']/a")
    WebElement downloadButton;

    @FindBy(xpath="//div[@class='edit-app-button']/a[1]")
    WebElement deleteButton;

    @FindBy(xpath="//div[@class='edit-app-button']/a[2]")
    WebElement editButton;

    public String getAppTitle() {
        return appTitle.getText();
    }

    public String getAppDescription() {
        return appDescription.getText().replace("Description: ","");
    }

    public String getCategory() {
        return category.getText().replace("Category: ","");
    }

    public String getAuthor() {
        return author.getText().replace("Author: ","");
    }

    public String getNumberOfDownloads() {
        return numberOfDownloads.getText().replace("# of downloads: ","");
    }

    public DownloadPage clickDownloadButton() {
        downloadButton.click();
        return initPage(DownloadPage.class);
    }

    public EditAppPage clickEditButton() {
        editButton.click();
        return initPage(EditAppPage.class);
    }

    public void clickDeleteButton() {
        deleteButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
