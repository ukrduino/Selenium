package com.dataart.selenium.models;

import com.dataart.selenium.framework.Utils;


public class ApplicationBuilder {

    public static Application createNewAppModel() {
        String nowDateKey = Utils.getStringCurrentDateTime();
        Application newAppModel = new Application();
        newAppModel.setAppTitle("Selenium "+nowDateKey);
        newAppModel.setAppDescription("Super web testing framework !!!");
        newAppModel.setCategory(ApplicationCategories.Development);
        return newAppModel;
    }

    public static Application createNewAppModelWith(String imagePath) {
        String nowDateKey = Utils.getStringCurrentDateTime();
        Application newAppModel = new Application();
        newAppModel.setAppTitle("Selenium "+nowDateKey);
        newAppModel.setAppDescription("Super web testing framework !!!");
        newAppModel.setCategory(ApplicationCategories.Development);
        newAppModel.setIconPath(imagePath);
        newAppModel.setImagePath(imagePath);
        return newAppModel;
    }

}
