package core.drivers;

import org.openqa.selenium.WebDriver;

public class WebDriverProvider {
    private static WebDriver webDriver;

    public static WebDriver getDriver() {
        if (webDriver == null) {
            webDriver = create();
            return webDriver;
        }
        return webDriver;
    }

    private static WebDriver create() {

        return null;
    }

}
