package core.utils.hellper;

import core.base.TestBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
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


    public static AndroidDriver androidDriver;

    protected WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(maxSecondsOfWait));


    @Override
    public void click(String selector) {

        WebElement element = androidDriver.findElement(AppiumBy.id(selector));
        waitFor(element).click();
    }

    @Override
    public void clickcn(String selector) {
        WebElement element = androidDriver.findElement(AppiumBy.className(selector));
        waitFor(element).click();
    }

    public void typecn(String selector, String text) {
        WebElement element = androidDriver.findElement(AppiumBy.className(selector));
        element.clear();
        element.sendKeys(text);
    }


    @Override
    public void switchTo() {
        androidDriver.switchTo().alert();
    }

    public String getPageSource() {
        return androidDriver.getPageSource();
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
        WebElement element = waitFor(androidDriver.findElement(AppiumBy.id(id)));
        element.clear();
        element.sendKeys(text);
    }

    public void typex(String xpath, String text) {
        WebElement element = waitFor(androidDriver.findElement(AppiumBy.xpath(xpath)));
        element.clear();
        if (text.isEmpty()) element.sendKeys(text);
    }

    public void type(String id, String text, boolean clearFirst) {
        if (!clearFirst)
            waitFor(androidDriver.findElement(AppiumBy.id(id))).sendKeys(text);
        type(id, text);
    }

    @Override
    public boolean isDisplayed(String id) {

        try {
            WebElement element = androidDriver.findElement(AppiumBy.id(id));

            return waitFor(element).isDisplayed();

        } catch (NotFoundException e) {
            return false;
        }

    }

    @Override
    public boolean isNotDisplayed(String id) {
        WebElement element = androidDriver.findElement(AppiumBy.id(id));

        return !element.isDisplayed();
    }

    @Override
    public boolean isDisplayed(String id, int maxSeconds) {
        try {
            WebElement element = androidDriver.findElement(AppiumBy.id(id));
            return waitFor(element).isDisplayed();

        } catch (NotFoundException e) {
            return false;
        }

    }

    @Override
    public boolean isNotDisplayed(String id, int maxSeconds) {
        try {
            WebElement element = androidDriver.findElement(AppiumBy.id(id));
            return !element.isDisplayed();
        } catch (NotFoundException nfe) {
            log.info("Not displayed element: " + nfe.getMessage());
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

        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    @Override
    public WebElement waitFor(String selector) {

        return wait.until(ExpectedConditions.visibilityOf(get(selector)));

    }

    @Override
    public WebElement waitFor(String selector, int maxSecondsOfWait) {

        return wait.until(ExpectedConditions.visibilityOf(get(selector)));

    }

    @Override
    public void waitTimeOut(int waitOfSeconds) {
        synchronized (androidDriver) {
            androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitOfSeconds));
        }
    }
}
