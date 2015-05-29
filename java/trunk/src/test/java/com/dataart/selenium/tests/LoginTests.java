package com.dataart.selenium.tests;

import com.dataart.selenium.framework.MyLogger;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.*;

import static com.dataart.selenium.framework.Utils.isElementPresent;
import static com.dataart.selenium.models.UserBuilder.admin;
import static com.dataart.selenium.pages.BasePage.*;
import static org.testng.Assert.*;


public class LoginTests extends BaseTest {

    private LoginPage onLoginPage;
    private HeaderPage onHeader;
    private User user;
    private MyLogger logger;

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        onHeader = initPage(HeaderPage.class);
        user = admin();
        logger = new MyLogger();
    }

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        onLoginPage = onHeader.forceLogout();
    }

    @Test
    public void correctLoginTest() {
        logger.logToFile("Trying to login as registered user");
        onLoginPage.loginAs(user);
        logger.logToFile("Login Successed");
        onHeader.assertHeader(user);
    }

    @Test
    public void incorrectLoginTest() {
        logger.logToFile("Trying to login as registered user with incorrect password");
        user.setPassword(user.getPassword() + user.getPassword());
        onLoginPage.loginAs(user);
        logger.logToFile("Login Failed");
        assertTrue(isElementPresent(flash), "flash in not present");
        assertTrue(getFlashMessage().equals("You have entered an invalid username or password!"),
                "flash message is wrong");
    }

    @Test
    public void incorrectThenCorrectTest() {
        user.setPassword(user.getPassword() + user.getPassword());
        onLoginPage.loginAs(user);
        user.setPassword(admin().getPassword());
        onLoginPage.loginAs(user);
        onHeader.assertHeader(user);
    }

}
