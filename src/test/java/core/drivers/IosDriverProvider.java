package core.drivers;

import io.appium.java_client.ios.IOSDriver;

public class IosDriverProvider {

    private static IOSDriver iosDriver;

    public static IOSDriver getDriver() {
        if (iosDriver == null) {
            iosDriver = create();
            return iosDriver;
        }

        return iosDriver;
    }


    private static IOSDriver create() {

        return null;
    }
}
