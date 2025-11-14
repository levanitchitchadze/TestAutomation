package core.drivers;

import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IosDriverProvider {

    private static IOSDriver iosDriver;

    public static IOSDriver getDriver() {
        if (iosDriver == null) {
            iosDriver = create();
            return iosDriver;
        }

        return iosDriver;
    }


    private static synchronized IOSDriver create() {
        if (iosDriver == null) {
            iosDriver = create();
        }

        return iosDriver;
    }
}
