package test.automation;

import core.data.android.OTPData;
import core.steps.android.OTPSteps;
import core.utils.messages.output.error.TestFailMessages;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static core.data.android.OTPData.lastOTPCode;

public class OTPTest {

    OTPSteps otpSteps;
    OTPData otpData;

    @BeforeClass
    void setUpOTPTest() {
        Dotenv dotenv = Dotenv.load();

        otpSteps = new OTPSteps(dotenv.get("DEVICE_SERIAL_NUMBER"));
        otpData = new OTPData();
        lastOTPCode = otpSteps.getLatestOTPCode();
        assert otpSteps.itIsOTPPage() : TestFailMessages.ITS_NOT_CORRECT_PAGE;

    }


    @Test(dependsOnGroups = {"login"}, groups = "otpNegative")
    void enterIncorrectOTPCode() {
        otpSteps.enterOTP(otpData.getINCORRECT_OTP_CODE());
        wrongAttemptWindowCheck();
    }


    @Test(dependsOnGroups = {"login"}, groups = "otpNegative")
    void enterShortOTPCode() {
        otpSteps.enterOTP(otpData.getSHORT_OTP_CODE());
        wrongAttemptWindowCheck();

    }

    @Test(dependsOnGroups = {"login"}, groups = "otpNegative")
    void enterLongOTPCode() {
        otpSteps.enterOTP(otpData.getLONG_OTP_CODE());
        wrongAttemptWindowCheck();
    }

    @Test(dependsOnGroups = {"login"}, alwaysRun = true)
    void resendOtpCode() {
        otpSteps.resendOTPCode();
        String latestOTP = otpSteps.getLatestOTPCode();
        assert lastOTPCode.equals(latestOTP) : TestFailMessages.OTP_CODE_WAS_NOT_SEND;
        lastOTPCode = latestOTP;
    }

    @Test(dependsOnGroups = {"login"}, alwaysRun = true)
    void passOTPCheck() {
        otpSteps.enterOTP(lastOTPCode);
    }


    private void wrongAttemptWindowCheck() {
        assert otpSteps.wrongAttemptWindowIsDisplayed(true) : TestFailMessages.WRONG_ATTEMPT_WINDOW_IS_NOT_DISPLAYED;
    }
}


