package pom.base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pom.data.test_object.app_data.VirtualDeviceConfig;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;


public class AppiumBaseTest {
    protected static AndroidDriver driver;

    @BeforeClass
    public void setUpDriver() {
//        The DesiredCapabilities class helps us specify which parametrs our program should run with
        DesiredCapabilities capabilities = new DesiredCapabilities();
        VirtualDeviceConfig deviceConfig = new VirtualDeviceConfig();

//        And there is parameters
        capabilities.setCapability("platformName", deviceConfig.getPLATFORM_NAME());
        capabilities.setCapability("appium:automationName", deviceConfig.getAUTOMATION_NAME());
        capabilities.setCapability("appium:deviceName", deviceConfig.getDEVICE_NAME());
//        I can download .apk or .ipa file and run to emulator.
//        I don't have Macbook so not actually :D
//        capabilities.setCapability("appium:app",  System.getProperty("user.dir") + "/src/main/java/pom/data/test_object/apk/ApiDemos-debug.apk");

//        If I want to not remove app data before use
//        capabilities.setCapability("noReset", true);

//        App package name I want to test (already installed app from PlayStore)
        capabilities.setCapability("appPackage", deviceConfig.getAPP_PACKAGE());
//        also we need to run package so I set activity name
        capabilities.setCapability("appActivity", deviceConfig.getAPP_ACTIVITY());

//        Here i create URL to pass android driver and connect to appium server
        var uri = getURL(deviceConfig.getURL());
        driver = new AndroidDriver(uri, capabilities);

//        Here is some wait before program connect to server
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


//        P.S: It's not AI comments :D

    }

    private URL getURL(String urlString) {
        try {
            return new URI(urlString).toURL();

        } catch (URISyntaxException | MalformedURLException urle) {
            System.out.println("Not valid url String" + urle.getMessage());
            return null;
        }
    }

    public void rerunApplication() {
        driver.close();
        setUpDriver();
    }

    //    The tearDown method closes the driver no mether what, otherwise it may cause problems for the next run.
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
