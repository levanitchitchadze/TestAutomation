package core.base;

import core.factories.steps.StepsFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TestBase {
    public static AndroidDriver androidDriver;
    protected IPlatformAbstractFactory iPlatformAbstractFactory;
    protected PlatformContext context;
    protected StepsFactory steps;
    //    protected androidPageFactory androidPageFactory;
    protected short maxSecondsOfWait = 20;

    protected WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(maxSecondsOfWait));


    protected boolean itIsCorrectPage(String[] requiredElements) {
        try {
            return Arrays.stream(requiredElements)
                    .allMatch(id -> wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(AppiumBy.id(id)))).isDisplayed());
        } catch (NotFoundException nfe) {
            log.error("Can't find required element for page:" + nfe.getMessage());
            return false;
        }

    }

    @BeforeTest
    @Parameters({"platform"})
    public void setUp(String platform) {
        setUpDriver(platform);

//        androidStepsFactory = StepsFactory.getAndroidFactory();

//        androidPageFactory = new PageFactory();

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

        if (androidDriver != null) return;
        if (context == null) context = PlatformContextBuilder.build(platform);

        androidDriver = context.getAndroidDriver();

    }

    //    The tearDown method closes the driver no mether what, otherwise it may cause problems for the next run.
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (androidDriver != null) {
            androidDriver.quit();

        }
    }

}
