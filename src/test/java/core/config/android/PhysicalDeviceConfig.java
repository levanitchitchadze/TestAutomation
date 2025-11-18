package core.config.android;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

@Getter
public class PhysicalDeviceConfig {

    private String OTP_SORT_COLUMN = "date";
    private String OTP_FILTER_TEXT = "tbconline an tibisis mobilur aplikaciashi";

    private String DEVICE_SERIAL_NUMBER = Dotenv.load().get("DEVICE_SERIAL_NUMBER");

}
