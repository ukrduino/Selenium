package com.dataart.selenium.pages;


import org.json.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DownloadPage extends BasePage {
    @FindBy(xpath = "//pre")
    WebElement Response;


    private JSONObject jsonObject;

    public void makeJsonObjectFromString() throws JSONException {
        jsonObject = new JSONObject(Response.getText());
    }

    public String getAppTitleFromJson() throws JSONException {

        return jsonObject.getString("title");
    }

    public String getAppDescriptionFromJson() throws JSONException {
        return jsonObject.getString("description");
    }

    public String getCategoryFromJson() throws JSONException {
        return jsonObject.getJSONObject("category").getString("title");
    }

    public String getAuthorFromJson() throws JSONException {
        return jsonObject.getJSONObject("author").getString("name");
    }

    public String getNumberOfDownloadsFromJson() throws JSONException {

        return Integer.toString(jsonObject.getInt("numberOfDownloads")-1);
    }

}

