package core.base;

import core.enums.PlatformType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class PlatformContext {

    @Setter(AccessLevel.PRIVATE)
    private PlatformType type;

    private WebDriver webDriver;
    private AndroidDriver androidDriver;
    private IOSDriver iosDriver;
    private RequestSpecification apiClient;


    public PlatformContext(PlatformType type) {
        this.type = type;
    }


}
