package com.dataart.selenium.tests;

import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.HeaderPage;
import com.dataart.selenium.pages.JSPage;
import com.dataart.selenium.pages.LoginPage;
import com.opera.core.systems.scope.protos.HttpLoggerProtos;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.dataart.selenium.models.UserBuilder.admin;
import static com.dataart.selenium.pages.BasePage.initPage;
import static org.testng.Assert.assertTrue;

public class JSTests extends BaseTest {

    private JSPage onJsPage;

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        HeaderPage onHeader = initPage(HeaderPage.class);
        User user = admin();
        LoginPage onLoginPage = onHeader.forceLogout();
        onLoginPage.loginAs(user);
        onJsPage = onHeader.clickJSTestPageLink();
    }

    @Test
    public void getCoordOfJumpingDivWithJqueryTest(){
        assertTrue(onJsPage.getCoordOfJumpingDiv().equals("Whoo Hoooo! Correct!"));

    }
}
