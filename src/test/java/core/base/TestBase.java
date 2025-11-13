package core.base;

import core.factories.steps.StepsFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class TestBase {
    protected static AndroidDriver androidDriver;
    protected IPlatformAbstractFactory iPlatformAbstractFactory;
    protected PlatformContext context;
    protected StepsFactory stepsFactory;
    protected PageFactory pageFactory;

    @BeforeTest
    @Parameters({"platform"})
    public void setUp(String platform) {
        context = PlatformContextBuilder.build(platform);

        androidDriver = context.getAndroidDriver();

        stepsFactory = new StepsFactory();

        pageFactory = new PageFactory();

    }


    //    The tearDown method closes the driver no mether what, otherwise it may cause problems for the next run.
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }
}
