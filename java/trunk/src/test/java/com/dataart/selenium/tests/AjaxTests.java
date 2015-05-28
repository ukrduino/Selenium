package com.dataart.selenium.tests;

import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dataart.selenium.models.UserBuilder.admin;
import static com.dataart.selenium.pages.BasePage.initPage;
import static com.dataart.selenium.pages.BasePage.onHeader;
import static org.testng.Assert.*;

public class AjaxTests extends BaseTest{

//    private BasePage basePage;
    private HeaderPage onHeader;
    private HomePage onHomePage;
    private AjaxPage onAjaxPage;
//    private DownloadPage onDownloadPage;
//    private NewAppPage onNewAppPage;
//    private EditAppPage onEditAppPage;
//    private PopularPanel onPopularPanel;
//    private Application newAppModel;
    private User user;

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        onHeader = initPage(HeaderPage.class);
        LoginPage onLoginPage = onHeader.forceLogout();
        user = admin();
        onHomePage = onLoginPage.loginAs(user);
    }
    @AfterMethod
    public void clean(){
        onAjaxPage.clickGoBackLink();

    }

    @Test
    public void addTwoValidNumbersAndCheckResultTest(){
        onAjaxPage = onHeader().clickAjaxTestPageLink();
        assertTrue(onAjaxPage.addTwoNumbers("-1.35", "10")==8.65);

    }

    @Test
    public void addTwoInValidNumbersAndCheckResultTest(){
        onAjaxPage = onHeader().clickAjaxTestPageLink();
        assertTrue(onAjaxPage.addTwoInvalidNumbers("invalid number", "10.33").equals("Incorrect data"));

    }

}
