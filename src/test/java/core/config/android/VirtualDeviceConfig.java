package core.config.android;

import lombok.Getter;

@Getter
public class VirtualDeviceConfig {


    private static VirtualDeviceConfig instance;
    private String PLATFORM_NAME = "Android";
    private String AUTOMATION_NAME = "UiAutomator2";
    private String DEVICE_NAME = "Pixel_6a";
    private String APP_PACKAGE = "com.icomvision.bsc.tbc";
    private String APP_ACTIVITY = "com.icomvision.bsc.tbc/ge.tbc.bank.features.authentication.presentation.LoginActivity";
    private String PORT = "5554";
    private String DEVICE_SERIAL_NUMBER = "emulator-" + PORT;
    private String ANDROID_SDK_ROOT = "/home/levan/Android/Sdk";
    private String ANDROID_HOME = "/home/levan/Android/Sdk";


    private VirtualDeviceConfig() {

    }


    public static synchronized VirtualDeviceConfig getInstance() {

        if (instance == null) {
            instance = new VirtualDeviceConfig();
        }

        return instance;
    }

}
