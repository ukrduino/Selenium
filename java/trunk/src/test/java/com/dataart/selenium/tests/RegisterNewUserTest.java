package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.BasicPage;
import com.dataart.selenium.pages.HeaderPage;
import com.dataart.selenium.pages.LoginPage;
import com.dataart.selenium.pages.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static com.dataart.selenium.models.UserBuilder.createUniqueUserWithRole;
import static org.fest.assertions.Assertions.assertThat;
import static com.dataart.selenium.framework.BasePage.initPage;


public class RegisterNewUserTest extends BaseTest {

    private RegisterPage onRegisterPage;
    private HeaderPage headerPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openRegistrationPage() {
        BasicPage basicPage = initPage(BasicPage.class);
        LoginPage loginPage = basicPage.forceLogout();
        onRegisterPage = loginPage.toRegisterPage();
        headerPage = initPage(HeaderPage.class);
        user = createUniqueUserWithRole("DEVELOPER");
    }

    @Test
    public void registerNewUserTest() {
        onRegisterPage.registerNewUser(user);
        assertHeader(user);
    }

//    @Test
//    public void registerNewUserTest() {
//        onRegisterPage.registerNewUser(user);
//        assertHeader(user);
//    }

    private void assertHeader(User user){
        assertThat(headerPage.getWelcomeMessage()).isEqualTo("Welcome " + user.getFname() + " " + user.getLname());
    }
}
