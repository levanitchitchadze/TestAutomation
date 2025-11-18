package core.config.android;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

@Getter
public class VirtualDeviceConfig {


    private static VirtualDeviceConfig instance;
    private String PLATFORM_NAME = "Android";
    private String AUTOMATION_NAME = "UiAutomator2";
    private String DEVICE_NAME = "Pixel_6a";
    private String APP_PACKAGE = "com.icomvision.bsc.tbc";
    private String APP_ACTIVITY = "com.icomvision.bsc.tbc/ge.tbc.bank.features.authentication.presentation.LoginActivity";
    //    private String APP_WAIT_ACTIVITY = "com.icomvision.bsc.tbc/cz.bsc.mb.features.home.MainPageActivity";
    private String PORT = "5554";
    //    private String DEVICE_SERIAL_NUMBER = "emulator-" + PORT;
    private String DEVICE_SERIAL_NUMBER = "18e7eb0c";
    //
    private String ANDROID_HOME = Dotenv.load().get("ANDROID_HOME");
    private String ANDROID_SDK_ROOT = ANDROID_HOME;


    private VirtualDeviceConfig() {

    }


    public static synchronized VirtualDeviceConfig getInstance() {

        if (instance == null) {
            instance = new VirtualDeviceConfig();
        }

        return instance;
    }

}
