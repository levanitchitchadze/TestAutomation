package pom.util;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import test.automation.base.AppiumBaseTest;

public class appium_helper extends AppiumBaseTest {
    //    All of this methods are little helpers for me to not write some code again and again
    public WebElement findById(String id){
        return driver.findElement(AppiumBy.id(id));
    }

    public WebElement findByXpath(String xpath){
        return driver.findElement(AppiumBy.xpath(xpath));
    }

    //    here is overload example BTW :D
    public void click(WebElement element){
        element.click();
    }

    public void click(String id){
        driver.findElement(AppiumBy.id(id)).click();
    }

    public String text_of(String id){
        return driver.findElement(AppiumBy.id(id)).getText();
    }

    public void type(String id ,String text){
        driver.findElement(AppiumBy.id(id)).sendKeys(text);
    }

    public boolean isDisplayed(String id){
        return driver.findElement(AppiumBy.id(id)).isDisplayed();
    }
}
