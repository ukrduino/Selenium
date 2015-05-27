package com.dataart.selenium.tests;

import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.*;

import static com.dataart.selenium.framework.Utils.isElementPresent;
import static com.dataart.selenium.models.UserBuilder.admin;
import static com.dataart.selenium.pages.BasePage.*;
import static org.testng.Assert.*;


public class LoginTest extends BaseTest {

    private LoginPage onLoginPage;
    private HeaderPage onHeader;
    private User user;

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        onHeader = initPage(HeaderPage.class);
        user = admin();
    }

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        onLoginPage = onHeader.forceLogout();
    }

    @Test
    public void correctLoginTest() {
        onLoginPage.loginAs(user);
        onHeader.assertHeader(user);
    }

    @Test
    public void incorrectLoginTest() {
        user.setPassword(user.getPassword() + user.getPassword());
        onLoginPage.loginAs(user);
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
