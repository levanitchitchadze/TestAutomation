package core.utils.hellper;

import core.base.TestBase;
import io.appium.java_client.AppiumBy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Setter
@Slf4j
public class AppiumHelper extends TestBase implements UserInterfaceHelper {
    //    All of this methods are little helpers for me to not write some code again and again

    protected static AppiumHelper appiumHelper;
    private short maxSecondsOfWait = 20;

    @Override
    public void click(String selector) {
        WebElement element = androidDriver.findElement(AppiumBy.id(selector));
        waitFor(element).click();
    }

    public void clickx(String xpath) {
        WebElement element = androidDriver.findElement(AppiumBy.xpath(xpath));
        waitFor(element).click();
    }


    @Override
    public String textOf(String id) {
        WebElement element = androidDriver.findElement(AppiumBy.id(id));
        return waitFor(element).getText();
    }

    @Override
    public WebElement get(String id) {
        return androidDriver.findElement(AppiumBy.id(id));
    }

    public WebElement getWithText(String text) {
        return androidDriver.findElement(AppiumBy.xpath("//*[contains(@text, '" + text + "')]"));
    }

    @Override
    public WebElement getx(String xpath) {
        return waitFor(androidDriver.findElement(AppiumBy.xpath(xpath)));
    }


    //    here is overload example BTW :D
    @Override
    public void click(WebElement element) {
        waitFor(element).click();
    }

    @Override

    public void type(String id, String text) {
        waitFor(androidDriver.findElement(AppiumBy.id(id))).sendKeys(text);
    }

    @Override
    public boolean isDisplayed(String id) {
        WebElement element = androidDriver.findElement(AppiumBy.id(id));

        return waitFor(element).isDisplayed();
    }

    @Override
    public boolean isNotDisplayed(String id) {
        WebElement element = androidDriver.findElement(AppiumBy.id(id));

        return !element.isDisplayed();
    }

    @Override
    public boolean isDisplayed(String id, int maxSeconds) {
        WebElement element = androidDriver.findElement(AppiumBy.id(id));

        return waitFor(element).isDisplayed();
    }

    @Override
    public boolean isNotDisplayed(String id, int maxSeconds) {
        try {
            WebElement element = androidDriver.findElement(AppiumBy.id(id));
            return !element.isDisplayed();
        } catch (NotFoundException nfe) {
            log.info("Not found element: " + nfe.getMessage());
        }

        return true;
    }

    @Override
    public WebElement waitFor(WebElement element, int maxSeconds) {

        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(maxSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));

    }


    @Override
    public WebElement waitFor(WebElement element) {

        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(maxSecondsOfWait));
        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    @Override
    public WebElement waitFor(String selector) {

        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(maxSecondsOfWait));
        return wait.until(ExpectedConditions.visibilityOf(get(selector)));

    }

    @Override
    public void waitTimeOut(int waitOfSeconds) {

        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitOfSeconds));

    }
}
