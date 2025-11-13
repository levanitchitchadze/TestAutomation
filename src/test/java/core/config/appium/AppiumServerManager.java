package core.config.appium;

import core.config.android.VirtualDeviceConfig;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class AppiumServerManager {
    private static Process process;
    private static AppiumDriverLocalService service;

    public static URL startAppiumServer(VirtualDeviceConfig virtualDeviceConfig) {
        Dotenv dotenv = Dotenv.load();

        String url = virtualDeviceConfig.getURL();
        int port = virtualDeviceConfig.getPort();


        File nodeJsExecutableFile = new File(dotenv.get("NODE_EXECUTABLE_FILE_PATH"));
        File appiumExecutableFile = new File(dotenv.get("APPIUM_SERVER_PATH"));


        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
                .usingDriverExecutable(nodeJsExecutableFile)
                .withAppiumJS(appiumExecutableFile)
                .withTimeout(Duration.ofSeconds(20))
                .usingPort(port) // .usingAnyFreePort() it can chose port automatically
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.USE_PLUGINS, "inspector")
                .withLogFile(new File("src/test/java/log/appium/appium.log"));
//                .withArgument(GeneralServerFlag.ALLOW_INSECURE, "--allow-cors")
//                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")


        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(appiumServiceBuilder);


        try {
            service.start();
            System.out.println("Appium Server started at: " + service.getUrl());
            return service.getUrl();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Can't start Appium server");
        }

        return null;
    }

    public static void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped.");
        }
    }


}
