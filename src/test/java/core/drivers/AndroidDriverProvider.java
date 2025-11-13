package core.drivers;

import core.config.android.VirtualDeviceConfig;
import core.config.appium.AppiumServerManager;
import core.utils.messages.error.TestFailMessages;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class AndroidDriverProvider {

    private static AndroidDriver androidDriver;

    public static synchronized AndroidDriver getDriver() {
        if (androidDriver == null) {
            androidDriver = create();
        }

        return androidDriver;
    }

    private static AndroidDriver create() {
//        The DesiredCapabilities class helps us specify which parametrs our program should run with
        DesiredCapabilities capabilities = new DesiredCapabilities();
        VirtualDeviceConfig deviceConfig = new VirtualDeviceConfig();

//        And there is parameters
        capabilities.setCapability("platformName", deviceConfig.getPLATFORM_NAME());
        capabilities.setCapability("appium:automationName", deviceConfig.getAUTOMATION_NAME());
        capabilities.setCapability("appium:deviceName", deviceConfig.getDEVICE_NAME());

//        I can download .apk or .ipa file and run to emulator.
//        I don't have Macbook so not actually can run .ipa file:D
//        capabilities.setCapability("appium:app",  System.getProperty("user.dir") + "/src/main/java/pom/data/test_object/apk/ApiDemos-debug.apk");

//        If I want to not remove app data before use
//        capabilities.setCapability("noReset", true);

//        App package name I want to test (already installed app from PlayStore)
        capabilities.setCapability("appPackage", deviceConfig.getAPP_PACKAGE());
//        also we need to run package so I set activity name
        capabilities.setCapability("appActivity", deviceConfig.getAPP_ACTIVITY());

//        Here i create URL to pass android driver and connect to appium server
//        URL uri = getURL(deviceConfig.getURL());

        URL uri = AppiumServerManager.startAppiumServer(new VirtualDeviceConfig());
        assert uri != null : TestFailMessages.DRIVER_URL_CANT_BE_NULL;

        androidDriver = new AndroidDriver(uri, capabilities);

//        Here is some wait before program connect to server
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));


//        P.S: It's not AI comments :D

        return androidDriver;
    }

    private static URL getURL(String urlString) {
        try {
            return new URI(urlString).toURL();

        } catch (URISyntaxException | MalformedURLException urle) {
            System.out.println("Not valid url String" + urle.getMessage());
            return null;
        }
    }


    public AndroidDriver rerunApplication() {
        androidDriver.quit();
        return create();
    }
}
