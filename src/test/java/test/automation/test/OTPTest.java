package test.automation.test;

import core.data.OTPData;
import core.steps.android.AndroidOTPSteps;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static core.data.OTPData.lastOTPCode;

public class OTPTest {

    private AndroidOTPSteps otpSteps;
    private OTPData otpData;


    @BeforeClass
    void setUpOTPTest() {
        Dotenv dotenv = Dotenv.load();

        otpSteps = new AndroidOTPSteps(dotenv.get("DEVICE_SERIAL_NUMBER"));
        otpData = new OTPData();
        lastOTPCode = otpSteps.getLatestOTPCode();

    }


    @Test(dependsOnGroups = {"login"}, groups = "otpNegative")
    void enterIncorrectOTPCode() {
        otpSteps.enterOTP(otpData.getINCORRECT_OTP_CODE());
        otpSteps.wrongAttemptWindowValidation(true);
    }

    //     User Lock after few try, so I need different user or unlocking tool
    //    @Test(dependsOnGroups = {"login"}, groups = "otpNegative")
    void enterShortOTPCode() {
        otpSteps.enterOTP(otpData.getSHORT_OTP_CODE());
        otpSteps.wrongAttemptWindowValidation(true);


    }

    //    @Test(dependsOnGroups = {"login"}, groups = "otpNegative")
    void enterLongOTPCode() {
        otpSteps.enterOTP(otpData.getLONG_OTP_CODE());
        otpSteps.wrongAttemptWindowValidation(true);

    }


    @Test(dependsOnGroups = {"otpNegative"}, alwaysRun = true)
    void resendOtpCode() {
        otpSteps.resendOTPCode();
        otpSteps.checkNewOTPCode();
    }

    @Test(dependsOnMethods = {"resendOtpCode"}, groups = "otpPositive", alwaysRun = true)
    void passOTPCheck() {
        otpSteps.enterOTP(lastOTPCode);
    }


}


