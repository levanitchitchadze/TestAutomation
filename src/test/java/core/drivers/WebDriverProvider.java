package core.drivers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;


@Slf4j
public class WebDriverProvider {
    private static WebDriver webDriver;

    public static WebDriver getDriver() {
        if (webDriver == null) {
            webDriver = create();
            return webDriver;
        }
        return webDriver;
    }

    private static synchronized WebDriver create() {


        return null;
    }

}
