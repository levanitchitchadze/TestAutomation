package test.automation;

import core.base.TestBase;
import core.data.android.LoginData;
import core.steps.android.HomeSteps;
import core.steps.android.LoginSteps;
import core.steps.android.OTPSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomeTest extends TestBase {

    LoginSteps loginSteps;
    LoginData loginData;
    OTPSteps otpsteps;
    HomeSteps homeSteps;

    @BeforeClass
    void setUpHomeTest() {
        homeSteps = new HomeSteps();

    }


    @Test
    void moveToMenuBarPages() {
        homeSteps.itIsHomePage();
        homeSteps.navigateToAllPage();
    }


}
