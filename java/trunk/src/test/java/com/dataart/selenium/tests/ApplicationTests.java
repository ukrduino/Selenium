package com.dataart.selenium.tests;

import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.json.JSONException;
import org.testng.annotations.*;
import static org.testng.Assert.*;


import static com.dataart.selenium.models.UserBuilder.admin;
import static com.dataart.selenium.pages.BasePage.initPage;

public class ApplicationTests extends BaseTest {

    private BasePage basePage;
    private LoginPage onLoginPage;
    private HeaderPage onHeader;
    private HomePage onHomePage;
    private AppDetailPage onAppDetailPage;
    private DownloadPage onDownloadPage;
    private User user;

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        onHeader = initPage(HeaderPage.class);
        onLoginPage = onHeader.forceLogout();
        user = admin();
        onHomePage = onLoginPage.loginAs(user);
    }


    @Test
    public void checkAppParamsFromJsonEqualsParamsFromAppDetailPageTest() throws JSONException{

        onAppDetailPage = onHomePage.clickFirstAppDetailPageLink();
        String appTitle = onAppDetailPage.getAppTitle();
        String appDescription = onAppDetailPage.getAppDescription();
        String author = onAppDetailPage.getAuthor();
        String category = onAppDetailPage.getCategory();
        String numberOfDownloads = onAppDetailPage.getNumberOfDownloads();
        onDownloadPage = onAppDetailPage.clickDownloadButton();
        onDownloadPage.makeJsonObjectFromString();

        assertTrue(appTitle.equals(onDownloadPage.getAppTitleFromJson()),
                "App appTitle from AppDetailPage not equals those from DownloadPage(JSON)");
        assertTrue(appDescription.equals(onDownloadPage.getAppDescriptionFromJson()),
                "App appDescription from AppDetailPage not equals those from DownloadPage(JSON)");
        assertTrue(author.equals(onDownloadPage.getAuthorFromJson()),
                "App author from AppDetailPage not equals those from DownloadPage(JSON)");
        assertTrue(category.equals(onDownloadPage.getCategoryFromJson()),
                "App category from AppDetailPage not equals those from DownloadPage(JSON)");
        assertTrue(numberOfDownloads.equals(onDownloadPage.getNumberOfDownloadsFromJson()),
                "App numberOfDownloads from AppDetailPage not equals those from DownloadPage(JSON)");
        BasePage.driver.navigate().back();
//        onHeader.clickYoMyApplicationsPage().clickToNewAppPage();
    }
}
