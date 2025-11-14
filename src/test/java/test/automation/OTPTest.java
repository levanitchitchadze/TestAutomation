package test.automation;

import core.steps.android.OTPSteps;
import core.utils.messages.output.error.TestFailMessages;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OTPTest {

    OTPSteps otpSteps;

    @BeforeClass
    void setUp() {
        Dotenv dotenv = Dotenv.load();

        otpSteps = new OTPSteps(dotenv.get("DEVICE_SERIAL_NUMBER"));
    }


    @Test(dependsOnGroups = {"login"})
    void passOTPCheck() {
        assert !otpSteps.itIsOTPPage() : TestFailMessages.ITS_NOT_CORRECT_PAGE;

        otpSteps.enterOTP();


    }
}


