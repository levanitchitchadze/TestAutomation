package pom.util.console_reader;

import pom.util.hellper.AppiumHelper;

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
