package com.dataart.selenium.tests;

import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dataart.selenium.models.UserBuilder.admin;
import static com.dataart.selenium.pages.BasePage.initPage;
import static org.testng.Assert.*;

public class AjaxTests extends BaseTest{

    private HeaderPage onHeader;
    private AjaxPage onAjaxPage;

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        onHeader = initPage(HeaderPage.class);
        User user = admin();
        LoginPage onLoginPage = onHeader.forceLogout();
        onLoginPage.loginAs(user);
    }
    @AfterMethod
    public void clean(){
        onAjaxPage.clickGoBackLink();

    }

    @Test
    public void addTwoValidNumbersAndCheckResultTest(){
        onAjaxPage = onHeader.clickAjaxTestPageLink();
        assertTrue(onAjaxPage.addTwoNumbers("-1.35", "10")==8.65);

    }

    @Test
    public void addTwoInValidNumbersAndCheckResultTest(){
        onAjaxPage = onHeader.clickAjaxTestPageLink();
        assertTrue(onAjaxPage.addTwoInvalidNumbers("invalid number", "10.33").equals("Incorrect data"));

    }

}
