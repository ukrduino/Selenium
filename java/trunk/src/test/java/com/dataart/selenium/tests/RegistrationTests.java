package com.dataart.selenium.tests;

import com.dataart.selenium.models.*;
import com.dataart.selenium.pages.*;
import org.testng.annotations.*;


import static com.dataart.selenium.pages.BasePage.*;
import static com.dataart.selenium.framework.Utils.isElementPresent;
import static com.dataart.selenium.models.UserBuilder.createUniqueUserWithRole;
import static org.testng.Assert.*;


public class RegistrationTests extends BaseTest {

    private HeaderPage onHeader;
    private LoginPage onLoginPage;
    private NewAppPage onNewAppPage;
    private HomePage onHomePage;
    private User user;


    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        onHeader = initPage(HeaderPage.class);
        onLoginPage = onHeader.forceLogout();
    }

    @AfterMethod(alwaysRun = true)
    public void openLoginPage() {
        onHeader.forceLogout();
    }

    @Test
    public void registerNewUserTestAndVerifyThatItIsLoggedIn() {
        user = createUniqueUserWithRole(UserRoles.USER);
        onLoginPage.clickToRegisterPage().registerNewUser(user);
        onHeader.assertHeader(user);
    }

    @Test
    public void registerNewUserLogoutAndVerifyThatUserCanLoginTest() {
        user = createUniqueUserWithRole(UserRoles.USER);
        onLoginPage.clickToRegisterPage().registerNewUser(user);
        onHeader.forceLogout().loginAs(user);
        onHeader.assertHeader(user);
    }

    @Test
    public void registerAsDeveloperVerifyUserCanOpenPageToUploadApplication() {
        user = createUniqueUserWithRole(UserRoles.DEVELOPER);
        onLoginPage.clickToRegisterPage().registerNewUser(user);
        onNewAppPage = onHeader.clickMyApplicationsLink().clickToNewAppPage();
        assertTrue(onNewAppPage.getPageTitle().equals("New application"));
        assertTrue(isElementPresent(onNewAppPage.NewAppTitle));
        assertTrue(isElementPresent(onNewAppPage.NewAppDescription));
        assertTrue(isElementPresent(onNewAppPage.NewAppImageBrowseButton));
        assertTrue(isElementPresent(onNewAppPage.NewAppIconBrowseButton));
        assertTrue(isElementPresent(onNewAppPage.CreateNewButton));
    }

    @Test
    public void registerUserAndVerifyAccessToApplications() {
        user = createUniqueUserWithRole(UserRoles.USER);
        onLoginPage.clickToRegisterPage().registerNewUser(user);
        assertTrue(isElementPresent(onHomePage.pageTitle), "Home page title is not present !!!");
        assertFalse(isElementPresent(onHeader.myApplicationsLink), "User has myApplicationsLink !!!");

    }


}
