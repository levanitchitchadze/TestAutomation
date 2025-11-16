package test.automation;

import core.base.TestBase;
import core.data.android.LoginData;
import core.steps.android.LoginSteps;
import core.steps.android.OTPSteps;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePage extends TestBase {

    LoginSteps loginSteps;
    LoginData loginData;
    OTPSteps otpsteps;


    @BeforeClass
    void setUpLoginTest() {
        loginSteps = new LoginSteps();
//        HomeSteps homeSteps = new HomeSteps();
        Dotenv dotenv = Dotenv.load();

        loginData = new LoginData();
        otpsteps = new OTPSteps(dotenv.get("DEVICE_SERIAL_NUMBER"));
        System.out.println(dotenv.get("DEVICE_SERIAL_NUMBER"));
    }

    @Test
    void checkIfEmulatorRunning() {

        loginSteps.login("", loginData.getPassword());
        String otp = otpsteps.getLatestOTPCode();
        System.out.println("OTP CODE:" + otp);
        otpsteps.enterOTP(otp);

//            adb devices | grep "Emulator"    private String DEVICE_SERIAL_NUMBER = "emulator-" + PORT;

    }


}
