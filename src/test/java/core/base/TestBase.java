package core.base;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TestBase {
    public static PlatformContext ctx;
    protected IPlatformAbstractFactory iPlatformAbstractFactory;
    //    protected androidPageFactory androidPageFactory;
    protected short maxSecondsOfWait = 20;

    @BeforeClass(alwaysRun = true)
    @Parameters({"platform"})
    public void setUp(String platform) {
        System.out.println();
        setUpDriver(platform);

    }

    protected boolean itIsCorrectPage(String[] requiredSelectors, String strategy) {
        WebDriver webDriver = ctx.getAndroidDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(maxSecondsOfWait));

        try {

            if (strategy.equals("xpath")) {
                return Arrays.stream(requiredSelectors)
                        .allMatch(x -> wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(x)))).isDisplayed());
            } else if (strategy.equals("id")) {
                return Arrays.stream(requiredSelectors)
                        .allMatch(id -> wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(AppiumBy.id(id)))).isDisplayed());
            } else {
                throw new RuntimeException("invalid Strategy isItCorrectPage method need strategy ex: id, xpath");
            }


        } catch (NotFoundException nfe) {
            log.error("Can't find required element for page:" + nfe.getMessage());
            return false;
        }
    }

    public void setUpForCucumber(Scenario scenario) {

        String regex = "\"([^\"]*)\"";
        Matcher matcher = Pattern.compile(regex).matcher(scenario.getName());
        String platform = "";
        if (matcher.find()) {
            platform = matcher.group(1);
        } else {
            throw new RuntimeException("Can't find platform name: android,ios,web,api in scenario name");
        }

        setUpDriver(platform);

    }


    private void setUpDriver(String platform) {

        ctx = PlatformContextBuilder.build(platform);


    }

    //    The tearDown method closes the driver no mether what, otherwise it may cause problems for the next run.
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (ctx != null) {
            ctx = null;

        }
    }

}
