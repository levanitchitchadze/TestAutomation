package test.automation.base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;


public class AppiumBaseTest {
    protected static AndroidDriver driver;

    @BeforeClass
    public void setUp() throws URISyntaxException, MalformedURLException {
//        The DesiredCapabilities class helps us specify which parametrs our program should run with
        DesiredCapabilities capabilities = new DesiredCapabilities();

//        And there is parameters
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "Pixel_6a");


//        I can download .apk or .ipa file and run to emulator.
//        I don't have Macbook so not actually :D
//        capabilities.setCapability("appium:app",  System.getProperty("user.dir") + "/src/main/java/pom/data/test_object/apk/ApiDemos-debug.apk");

//        If I want to not remove app data before use
//        capabilities.setCapability("noReset", true);

//        App package name I want to test (already installed app from PlayStore)
        capabilities.setCapability("appPackage", "com.icomvision.bsc.tbc");
//        also we need to run package so I set activity name
        capabilities.setCapability("appActivity", "com.icomvision.bsc.tbc/ge.tbc.bank.features.authentication.presentation.LoginActivity");

//        Here i create URL to pass android driver and connect to appium server
        var uri = new URI("http://127.0.0.1:4723").toURL();
        driver = new AndroidDriver(uri, capabilities);

//        Here is some wait before program connect to server
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


//        P.S: It's not AI comments :D

    }


    //    The tearDown method closes the driver no mether what, otherwise it may cause problems for the next run.
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
