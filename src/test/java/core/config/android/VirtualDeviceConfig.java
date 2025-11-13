package core.config.android;


import lombok.Getter;

@Getter
public class VirtualDeviceConfig {
    private final String PLATFORM_NAME = "Android";
    private final String AUTOMATION_NAME = "UiAutomator2";
    private final String DEVICE_NAME = "Pixel_6a";
    private final String APP_PACKAGE = "com.icomvision.bsc.tbc";
    private final String APP_ACTIVITY = "com.icomvision.bsc.tbc/ge.tbc.bank.features.authentication.presentation.LoginActivity";
    private final String URL = "http://127.0.0.1";
    private final int port = 4723; //default
    private final String ANDROID_SDK_ROOT = "/home/levan/Android/Sdk";
    private final String ANDROID_HOME = "/home/levan/Android/Sdk";


}
