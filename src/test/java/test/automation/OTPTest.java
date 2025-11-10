package test.automation;

import core.data.android.PhysicalDeviceConfig;
import core.utils.smsMessages.OTPCode;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeClass;

public class OTPTest {

    @BeforeClass
    void setUp() {
        Dotenv dotenv = Dotenv.load();
        PhysicalDeviceConfig physicalDevice = new PhysicalDeviceConfig();

        OTPCode otpCode = new OTPCode(dotenv.get("DEVICE_SERIAL_NUMBER"));
        String latestOTP = otpCode.getOTPCode(physicalDevice.getOTP_FILTER_TEXT(), physicalDevice.getOTP_SORT_COLUMN(), "\\b(\\d{4,8})\\b");
    }

}


