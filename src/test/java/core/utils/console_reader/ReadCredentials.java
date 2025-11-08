package core.utils.console_reader;

import core.utils.hellper.AppiumHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadCredentials {

    BufferedReader reader;
    AppiumHelper appiumHelper;


    public void readOtpCode() {
        reader = getNewReader();

    }

    private BufferedReader getNewReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }


}
