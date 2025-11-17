package core.data;

import lombok.Getter;

@Getter
public class OTPData extends TestDataClass {

    public static String lastOTPCode;
    private final String INCORRECT_OTP_CODE = faker.number().digits(6);
    private final String SHORT_OTP_CODE = faker.number().digits(3);
    private final String LONG_OTP_CODE = faker.number().digits(10);

}
