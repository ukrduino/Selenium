package com.dataart.selenium.tests;

import com.dataart.selenium.models.*;
import com.dataart.selenium.pages.*;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.io.File;

//import static com.dataart.selenium.framework.Utils.isElementPresent;
//import static com.dataart.selenium.pages.BasePage.driver;
import static com.dataart.selenium.pages.BasePage.getFlashMessage;
import static org.testng.Assert.*;


import static com.dataart.selenium.models.UserBuilder.admin;
import static com.dataart.selenium.pages.BasePage.initPage;

public class ApplicationTests extends BaseTest {

    private BasePage basePage;
    private HeaderPage onHeader;
    private HomePage onHomePage;
    private AppDetailPage onAppDetailPage;
    private DownloadPage onDownloadPage;
    private NewAppPage onNewAppPage;
    private EditAppPage onEditAppPage;
    private PopularPanel onPopularPanel;
    private Application newAppModel;
    private User user;

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        onHeader = initPage(HeaderPage.class);
        onPopularPanel = initPage(PopularPanel.class);
        LoginPage onLoginPage = onHeader.forceLogout();
        user = admin();
        onHomePage = onLoginPage.loginAs(user);
    }

    @BeforeMethod(alwaysRun = true)
    public void backToHomePage () {
        onHeader.clickHomePageLink();
    }

    @Test
    public void checkAppParamsFromJsonEqualsParamsFromAppDetailPageTest() throws JSONException {

        onAppDetailPage = onHomePage.clickFirstAppDetailPageLink();
        String appTitle = onAppDetailPage.getAppTitle();
        String appDescription = onAppDetailPage.getAppDescription();
        String author = onAppDetailPage.getAuthor();
        String category = onAppDetailPage.getCategory();
        String numberOfDownloads = onAppDetailPage.getNumberOfDownloads();
        onDownloadPage = onAppDetailPage.clickDownloadButton();
        onDownloadPage.makeJsonObjectFromString();

        assertTrue(appTitle.equals(onDownloadPage.getAppTitleFromJson()),
                "App appTitle from AppDetailPage not equals that from DownloadPage(JSON)");
        assertTrue(appDescription.equals(onDownloadPage.getAppDescriptionFromJson()),
                "App appDescription from AppDetailPage not equals that from DownloadPage(JSON)");
        assertTrue(author.equals(onDownloadPage.getAuthorFromJson()),
                "App author from AppDetailPage not equals that from DownloadPage(JSON)");
        assertTrue(category.equals(onDownloadPage.getCategoryFromJson()),
                "App category from AppDetailPage not equals that from DownloadPage(JSON)");
        System.out.println(numberOfDownloads);
        System.out.println(onDownloadPage.getNumberOfDownloadsFromJson());

        assertTrue(numberOfDownloads.equals(onDownloadPage.getNumberOfDownloadsFromJson()),
                "App numberOfDownloads from AppDetailPage not equals that from DownloadPage(JSON)");
        BasePage.driver.navigate().back();

    }

    @Test
    public void createNewAppWithoutImageAndIconAndVerifyItDisplayAndDownloadTest() throws JSONException {

        newAppModel = ApplicationBuilder.createNewAppModel();

        onAppDetailPage = onHeader.clickMyApplicationsLink()
                .clickToNewAppPage()
                .createNewApplicationFrom(newAppModel)
                .openApplicationWithTitle(newAppModel.getAppTitle());
        assertTrue(onAppDetailPage.getAppTitle().equals(newAppModel.getAppTitle()),
                "App appTitle from AppDetailPage not equals that from newAppModel)");
        assertTrue(onAppDetailPage.getAppDescription().equals(newAppModel.getAppDescription()),
                "App appDescription from AppDetailPage not equals that from newAppModel)");
        assertTrue(onAppDetailPage.getAuthor().equals(user.getUsername()),
                "App appAuthor from AppDetailPage not equals that from newAppModel)");
        assertTrue(onAppDetailPage.getCategory().equals(newAppModel.getCategory()),
                "App appCategory from AppDetailPage not equals that from newAppModel)");
        onDownloadPage = onAppDetailPage.clickDownloadButton();
        onDownloadPage.makeJsonObjectFromString();
        assertTrue(onDownloadPage.getAppTitleFromJson().equals(newAppModel.getAppTitle()),
                "App appTitle from DownloadPage(JSON) not equals that from newAppModel)");
        BasePage.driver.navigate().back();

    }

    @Test
    public void editAppWithoutImageAndIconAndVerifyChangesAppliedTest(){

        Integer numberOfAppWeWantToEdit = 2;
        String newAppDescription = "NEW DESCRIPTION!!!!!!";
        onAppDetailPage = onHomePage.openApplicationWithNumber(numberOfAppWeWantToEdit);
        String nameOfAppWeWantToEdit = onAppDetailPage.getAppTitle();
        onAppDetailPage.clickEditButton().editAppWithNewDescriptionAndCategory(
                newAppDescription,
                ApplicationCategories.Maps);
        assertTrue(getFlashMessage().contains("Application edited"),
                "Edit Application flash message is wrong");
        onAppDetailPage =onHeader.clickHomePageLink().openApplicationWithTitle(nameOfAppWeWantToEdit);
        assertTrue(onAppDetailPage.getAppDescription().equals(newAppDescription));
        assertTrue(onAppDetailPage.getCategory().equals(ApplicationCategories.Maps.name()));

    }

    @Test
    public void createNewApplicationWithImageAndIconTest(){

        ClassLoader classLoader = getClass().getClassLoader();
        File imageFile = new File(classLoader.getResource("Selenium.jpg").getFile());
        assertTrue(imageFile.exists());
        newAppModel = ApplicationBuilder
                                    .createNewAppModelWith(imageFile.getAbsolutePath());

        onAppDetailPage = onHeader.clickMyApplicationsLink()
                .clickToNewAppPage()
                .createNewApplicationFrom(newAppModel)
                .openApplicationWithTitle(newAppModel.getAppTitle());
    }

    @Test
    public void checkAppGoesToPopularPanelAndOpenItDetailsTest(){
        newAppModel = ApplicationBuilder.createNewAppModel();

        onAppDetailPage = onHeader.clickMyApplicationsLink()
                .clickToNewAppPage()
                .createNewApplicationFrom(newAppModel)
                .openApplicationWithTitle(newAppModel.getAppTitle());

        int x = 0;
        while(x < 5) {
            onAppDetailPage.clickDownloadButton();
            BasePage.driver.navigate().back();
            x++;
        }
        onHeader.clickHomePageLink();
        assertTrue(BasePage
                .driver.findElements(By
                        .xpath("//div[@class='popular-app']/a[div[text()='"
                                +newAppModel.getAppTitle()
                                +"']]"))
                .size() > 0,"App is not in popularPanel");
        onAppDetailPage = onPopularPanel.openApplicationWithTitle(
                newAppModel.getAppTitle());
        assertTrue(onAppDetailPage.getAppTitle().equals(newAppModel.getAppTitle()));
    }

    @Test
    public void deleteApplicationTest(){

        newAppModel = ApplicationBuilder.createNewAppModel();

        onHeader.clickMyApplicationsLink()
                .clickToNewAppPage()
                .createNewApplicationFrom(newAppModel)
                .openApplicationWithTitle(newAppModel.getAppTitle())
                .clickDeleteButton();

        assertTrue(getFlashMessage().contains("Deleted"),
                "Deleted flash message is wrong or absent");
        onHeader.clickHomePageLink();
        String findAppByTitleXPath = "//div[*[contains(.,'"+newAppModel.getAppTitle()+"')]]";
        assertFalse(BasePage.driver.findElements(By.xpath(findAppByTitleXPath)).size() > 0);

    }
}
