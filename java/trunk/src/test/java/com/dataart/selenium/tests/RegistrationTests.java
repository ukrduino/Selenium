package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static com.dataart.selenium.framework.BasePage.driver;
import static com.dataart.selenium.framework.Utils.isElementPresent;
import static com.dataart.selenium.models.UserBuilder.createUniqueUserWithRole;
import static org.fest.assertions.Assertions.assertThat;
import static com.dataart.selenium.framework.BasePage.initPage;


public class RegistrationTests extends BaseTest {

    public final String DEVELOPER = "DEVELOPER";
    public final String USER = "USER";

    private RegisterPage onRegisterPage;
    private HeaderPage headerPage;


    @BeforeMethod(alwaysRun = true)
    public void openRegistrationPage() {
        BasicPage basicPage = initPage(BasicPage.class);
        LoginPage loginPage = basicPage.forceLogout();
        onRegisterPage = loginPage.toRegisterPage();
        headerPage = initPage(HeaderPage.class);
    }

    @Test
    public void registerNewUserTestAndVerifyThatItIsLoggedIn() {
        User user = createUniqueUserWithRole(USER);
        HomePage onHomePage = onRegisterPage.registerNewUser(user);
        assertHeader(user);
        onHomePage.forceLogout();
    }

    @Test
    public void registerNewUserLogoutAndVerifyThatUserCanLoginTest() {
        User user = createUniqueUserWithRole(USER);
        HomePage onHomePage = onRegisterPage.registerNewUser(user);
        LoginPage onLoginPage = onHomePage.forceLogout();
        onLoginPage.loginAs(user);
        assertHeader(user);
        onHomePage.forceLogout();
    }

    @Test
    public void registerAsDeveloperVerifyUserCanOpenPageToUploadApplication() {
        User user = createUniqueUserWithRole(DEVELOPER);
        HomePage onHomePage = onRegisterPage.registerNewUser(user);
        HeaderPage onHeader = onHomePage.getHeader();
        MyApplicationsPage onMyApplicationsPage = onHeader.toMyApplicationsPage();
        NewAppPage onNewAppPage = onMyApplicationsPage.toNewAppPage();
        System.out.println(isElementPresent(onNewAppPage.byPageTitle));
        assertThat(isElementPresent(onNewAppPage.byNewAppTitle)).isTrue();
        assertThat(isElementPresent(onNewAppPage.byNewAppDescription)).isTrue();
        assertThat(isElementPresent(onNewAppPage.byNewAppImageBrowseButton)).isTrue();
        assertThat(isElementPresent(onNewAppPage.byNewAppIconBrowseButton)).isTrue();
        assertThat(isElementPresent(onNewAppPage.byCreateNewButton)).isTrue();
        assertThat(driver.findElement(onNewAppPage.byPageTitle).getText().equals("New application"));
        onNewAppPage.forceLogout();

    }

    @Test
    public void registerRegularUserVerifyUserCanSeeApplicationsButCannotUploadThemTest() {
        User user = createUniqueUserWithRole(USER);
        HomePage onHomePage = onRegisterPage.registerNewUser(user);
        HeaderPage onHeader = onHomePage.getHeader();
        assertThat(isElementPresent(onHomePage.byHomePageTitle)).isTrue();
        assertThat(isElementPresent(onHeader.byMyApplicationsLink)).isFalse();
        onHomePage.forceLogout();
    }

    private void assertHeader(User user){
        assertThat(headerPage.getWelcomeMessage()).isEqualTo("Welcome " + user.getFname() + " " + user.getLname());
    }
}
