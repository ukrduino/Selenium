package com.dataart.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.dataart.selenium.framework.Utils.*;

public class AjaxPage extends BasePage{

    @FindBy(id = "x")
    WebElement firstInput;

    @FindBy(id = "y")
    WebElement secondInput;

    @FindBy(id = "calc")
    WebElement resultButton;

    @FindBy(xpath = "//a[text()='go back']")
    WebElement goBackLink;

    public Double addTwoNumbers(String firstNumber, String secondNumber){
        firstInput.sendKeys(firstNumber);
        secondInput.sendKeys(secondNumber);
        resultButton.click();
        String resultString = elementByXpathIfPresentInSeconds("//*[@id='result']", 20)
                .getText()
                .replace("Result is: ", "");
        return Double.valueOf(resultString);

    }
    public String addTwoInvalidNumbers(String firstNumber, String secondNumber){
        firstInput.sendKeys(firstNumber);
        secondInput.sendKeys(secondNumber);
        resultButton.click();
        String resultString = elementByXpathIfPresentInSeconds("//*[@id='result']", 20)
                .getText()
                .replace("Result is: ", "");
        return resultString;

    }

    public HomePage clickGoBackLink(){
        goBackLink.click();
        return initPage(HomePage.class);
    }

}
