package core.steps.api;

import core.config.android.PhysicalDeviceConfig;
import core.utils.hellper.AppiumHelper;
import core.utils.smsMessages.MessageReader;

public class OTPSteps extends AppiumHelper {

    private final String deviceSerialNumber;

    public OTPSteps(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }


    public String getLatestOTPCode() {
        PhysicalDeviceConfig physicalDevice = new PhysicalDeviceConfig();

        MessageReader messageReader = new MessageReader(deviceSerialNumber, appiumHelper);

        return messageReader.getOTPCode(physicalDevice.getOTP_FILTER_TEXT(), physicalDevice.getOTP_SORT_COLUMN());
    }


    public void enterOTP() {

        System.out.println(getLatestOTPCode());
    }
}
