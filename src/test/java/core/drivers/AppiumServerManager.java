package core.drivers;

import core.config.AppiumConfig;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.Duration;

@Slf4j
public class AppiumServerManager {
    private static AppiumServerManager instance;
    private static Process process;
    private static AppiumDriverLocalService service;
    private AppiumConfig appiumConfig;


    private AppiumServerManager(AppiumConfig appiumConfig) {
        this.appiumConfig = appiumConfig;
    }

    public static synchronized AppiumServerManager getInstance(AppiumConfig appiumConfig) {
        if (instance == null) {
            instance = new AppiumServerManager(appiumConfig);
        }
        return instance;
    }

    public static void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped.");
        }
    }

    public void startAppiumServer() {

        this.appiumConfig = appiumConfig;

        Dotenv dotenv = Dotenv.load();


        String url = appiumConfig.getURL();
        int port = appiumConfig.getPORT();


        File nodeJsExecutableFile = new File(dotenv.get("NODE_EXECUTABLE_FILE_PATH"));
        File appiumExecutableFile = new File(dotenv.get("APPIUM_SERVER_PATH"));


        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
                .usingDriverExecutable(nodeJsExecutableFile)
                .withAppiumJS(appiumExecutableFile)
                .withTimeout(Duration.ofSeconds(20))
                .usingPort(port)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.USE_PLUGINS, "inspector") //appium will run with inspector plugin
                .withLogFile(new File("src/test/java/log/appium/appium.log"))
                .withArgument(() -> "--log-level", "fatal");


        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(appiumServiceBuilder);

        try {
            service.start();
            System.out.println("Appium Server started at: " + service.getUrl());
        } catch (Exception e) {
            log.error("Appium can't start: " + e.getMessage());
        }

    }


}
