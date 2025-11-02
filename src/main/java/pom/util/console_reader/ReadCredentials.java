package pom.util.console_reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class read_credentials {

    BufferedReader reader;

    public String[] readBasicCredentials() {
        reader = getNewReader();
        String[] credentials = new String[2];

        System.out.println("Enter Tbc Mobile Bank ");
        try {
            reader.readLine();

            System.out.print("Username: ");
            credentials[0] = reader.readLine();
            System.out.println("Password: ");
            credentials[1] = reader.readLine();
        } catch (IOException ioe) {
            System.out.println("Exception while reading user credentials: " + ioe.getMessage());
        }

        return credentials;
    }

    public void readOtpCode() {
        reader = getNewReader();

    }

    private BufferedReader getNewReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }


}
