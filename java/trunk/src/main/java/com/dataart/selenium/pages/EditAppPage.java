package com.dataart.selenium.pages;

import com.dataart.selenium.models.ApplicationCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EditAppPage extends BasePage{


    @FindBy(name="description")
    WebElement editAppDescription;

    Select editAppCategory = new Select(driver.findElement(By.name("category")));

    @FindBy(name = "image")
    WebElement editAppImageBrowseButton;

    @FindBy(name = "icon")
    WebElement editAppIconBrowseButton;

    public void editAppWithNewDescriptionAndCategory(String newDescription,
                                                     ApplicationCategories newCategory){
        editAppDescription.clear();
        editAppDescription.sendKeys(newDescription);
        editAppCategory.selectByVisibleText(newCategory.name());
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

}
