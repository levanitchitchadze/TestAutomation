package core.drivers;

import core.config.AppiumConfig;
import core.config.android.VirtualDeviceConfig;
import core.utils.messages.output.error.TestFailMessages;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Slf4j
public class AndroidDriverProvider implements IDriverProvider<AndroidDriver> {

    private static AndroidDriver androidDriver;

    public static AndroidDriverProvider getInstance() {
        return Holder.INSTANCE;
    }

    private static void setUpServers(VirtualDeviceConfig deviceConfig, AppiumConfig appiumConfig) {
        AndroidEmulatorManager androidEmulatorManager = AndroidEmulatorManager.getInstance(deviceConfig);

        try {
            androidEmulatorManager.startEmulatorAsync().thenRun(() -> {
                AppiumServerManager.getInstance(appiumConfig).startAppiumServer();
            }).get();
        } catch (ExecutionException | InterruptedException e) {
            log.error("Mistake while waiting emulator and appium: " + e.getMessage());
            throw new RuntimeException("Startup interrupted", e);
        }
    }

    private static URL getURL(String urlString, int port) {
        try {
            return new URI(urlString + ":" + port).toURL();
        } catch (URISyntaxException | MalformedURLException urlE) {
            log.error("Appium URL is not with valid format: " + urlE.getMessage());
            return null;
        }
    }
//

    @Override
    public synchronized AndroidDriver getDriver() {
        if (androidDriver == null) create();

        return androidDriver;
    }

    @Override
    public void create() {
//        The DesiredCapabilities class helps us specify which parameters our program should run with
        DesiredCapabilities capabilities = new DesiredCapabilities();


        VirtualDeviceConfig deviceConfig = VirtualDeviceConfig.getInstance();
        AppiumConfig appiumConfig = AppiumConfig.getInstance();

//        those little method starts emulator and then appium server
        setUpServers(deviceConfig, appiumConfig);


//        And there is parameters
        capabilities.setCapability("udid", deviceConfig.getDEVICE_SERIAL_NUMBER());
        capabilities.setCapability("platformName", deviceConfig.getPLATFORM_NAME());
        capabilities.setCapability("appium:automationName", deviceConfig.getAUTOMATION_NAME());
        capabilities.setCapability("appium:deviceName", deviceConfig.getDEVICE_NAME());
        capabilities.setCapability("enableMultiWindows", true);
        capabilities.setCapability("autoGrantPermissions", true);

//        App package name I want to test (already installed app from PlayStore)
        capabilities.setCapability("appPackage", deviceConfig.getAPP_PACKAGE());
//        also we need to run package, so I set activity name
        capabilities.setCapability("appActivity", deviceConfig.getAPP_ACTIVITY());


//        I can download .apk or .ipa file and run to emulator.
//        I don't have Macbook so not actually can run .ipa file:D
//        capabilities.setCapability("appium:app",  System.getProperty("user.dir") + "/src/main/java/pom/data/test_object/apk/ApiDemos-debug.apk");

//        If I want to not remove app data before use
//        capabilities.setCapability("noReset", true);


        URL url = getURL(appiumConfig.getURL(), appiumConfig.getPORT());
        assert url != null : TestFailMessages.DRIVER_URL_CANT_BE_NULL;

        System.out.println(url);


        androidDriver = new AndroidDriver(url, capabilities);

//        Here is some wait before program connect to server
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        log.info("Android Driver start.");
//        P.S: It's not AI comments :D

//        return androidDriver;
    }

    @Override
    public void close() {
        androidDriver.quit();
    }

    public Object restart() {
        androidDriver.quit();
        create();
        return androidDriver;
    }


    private static class Holder {
        private static final AndroidDriverProvider INSTANCE = new AndroidDriverProvider();
    }


}
