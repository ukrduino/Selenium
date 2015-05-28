package com.dataart.selenium.pages;

import org.apache.xpath.SourceTree;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JSPage extends BasePage {

    @FindBy(id = "top")
    WebElement topInput;

    @FindBy(id = "left")
    WebElement leftInput;

    @FindBy(id = "process")
    WebElement processButton;

    JavascriptExecutor js;

    public String getCoordOfJumpingDiv(){

        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor)driver;
        }
        js.executeScript("return $('div:contains(\"Find me\")').css('background-color', 'red');");
        int left = (int) Math.round((Double)js.executeScript("return $('div:contains(\"Find me\")').offset().left"));
        int top = (int) Math.round((Double)js.executeScript("return $('div:contains(\"Find me\")').offset().top"));
        System.out.println(left);
        System.out.println(top);
        topInput.sendKeys(Integer.toString(top));
        leftInput.sendKeys(Integer.toString(left));
        processButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();
        return alertText;
    }
}

//        if(lefOrTop.equals("left")){
//            String left = "";
//            return left;
//        }
//        else if (lefOrTop.equals("top")) {
//
//            String top ="";
//            return top;
//        }
//        else return "error";
//
//<script>
//        var p = $( "p:last" );
//var offset = p.offset();
//p.html( "left: " + offset.left + ", top: " + offset.top );
//</script>