package test.automation.app_test;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeClass;
import pom.data.test_object.app_data.PhysicalDeviceConfig;
import pom.util.smsMessages.OTPCode;

public class OTPTest {

    @BeforeClass
    void setUp() {
        Dotenv dotenv = Dotenv.load();
        PhysicalDeviceConfig physicalDevice = new PhysicalDeviceConfig();

        OTPCode otpCode = new OTPCode(dotenv.get("DEVICE_SERIAL_NUMBER"));
        String latestOTP = otpCode.getOTPCode(physicalDevice.getOTP_FILTER_TEXT(), physicalDevice.getOTP_SORT_COLUMN(), "\\b(\\d{4,8})\\b");
    }

}
