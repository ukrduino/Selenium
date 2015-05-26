package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import com.dataart.selenium.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;



public class RegisterPage extends BasePage{

    public static final String REG_USER_NAME_TEXT_FIELD_XPATH = "//input[@name='name']";
    public static final String REG_USER_FIRST_NAME_TEXT_FIELD_XPATH = "//input[@name='fname']";
    public static final String REG_USER_LAST_NAME_TEXT_FIELD_XPATH = "//input[@name='lname']";
    public static final String REG_USER_PASSWORD_TEXT_FIELD_XPATH = "//input[@name='password']";
    public static final String REG_USER_CONF_PASSWORD_TEXT_FIELD_XPATH = "//input[@name='passwordConfirm']";
    public static final String REG_USER_ROLE_SELECT_XPATH = "//input[@name='role']";
    public static final String REG_USER_REGISTER_BUTTON_XPATH = "//div[@class='form']/form/input";


    @FindBy(xpath = REG_USER_NAME_TEXT_FIELD_XPATH)
    WebElement regUserNameTextField;

    @FindBy(xpath = REG_USER_FIRST_NAME_TEXT_FIELD_XPATH)
    WebElement regUserFirstNameTextField;

    @FindBy(xpath = REG_USER_LAST_NAME_TEXT_FIELD_XPATH)
    WebElement regUserLastNameTextField;

    @FindBy(xpath = REG_USER_PASSWORD_TEXT_FIELD_XPATH)
    WebElement regUserPasswordTextField;

    @FindBy(xpath = REG_USER_CONF_PASSWORD_TEXT_FIELD_XPATH)
    WebElement regUserConfPasswordTextField;

    Select regUserRoleSelect = new Select(driver.findElement(By.name("role")));

    @FindBy(xpath = REG_USER_REGISTER_BUTTON_XPATH)
    WebElement regUserRegisterButton;


    public HomePage registerNewUser(User user) {
        regUserNameTextField.clear();
        regUserFirstNameTextField.clear();
        regUserLastNameTextField.clear();
        regUserPasswordTextField.clear();
        regUserConfPasswordTextField.clear();

        regUserNameTextField.sendKeys(user.getUsername());
        regUserFirstNameTextField.sendKeys(user.getFname());
        regUserLastNameTextField.sendKeys(user.getLname());
        regUserPasswordTextField.sendKeys(user.getPassword());
        regUserConfPasswordTextField.sendKeys(user.getPassword());
        regUserRoleSelect.selectByValue(user.getRole());
        regUserRegisterButton.click();
        return initPage(HomePage.class);
    }

}
