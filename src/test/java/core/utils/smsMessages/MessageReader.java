package core.utils.smsMessages;

import core.utils.hellper.AppiumHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageReader {

    private static final String url = "content://sms/inbox";
    private final String deviceSerialNumber;
    private AppiumHelper appiumHelper;


    public MessageReader(String deviceSerialNumber, AppiumHelper appiumHelper) {
        this.deviceSerialNumber = deviceSerialNumber;
        this.appiumHelper = appiumHelper;
    }


    public String getOTPCode(String filterBy, String sortBy) {

        checkIfOTPReceived(sortBy, filterBy);
        String regexString = "\\b(\\d{4,8})\\b";

        String latestRowsCount = "1";
        String command = String.format(
                "adb -s %s shell content query --uri %s --projection body --sort \\\"%s DESC\\\" | grep '%s' | head -n %s",
                deviceSerialNumber, url, sortBy, filterBy, latestRowsCount);

        System.out.println(command);

        StringBuilder out = getConsoleLines(command);
        return matchStringToRegex(out, regexString);

    }

    public boolean checkIfOTPReceived(String sortBy, String filterBy) {
        short maxWaitingTime = 60;
        String command = String.format(
                "adb -s %s shell content query --uri %s --projection date --sort \\\"%s DESC\\\" | grep '%s' | head -n 1",
                deviceSerialNumber, url, sortBy, filterBy);


        StringBuilder out = getConsoleLines(command);
        if (out == null) {
            appiumHelper.waitTimeOut(3);
            checkIfOTPReceived(sortBy, filterBy);
        }


        String matchedText = matchStringToRegex(out, "\\d{13}");

        if (matchedText == null) {
            appiumHelper.waitTimeOut(3);
            checkIfOTPReceived(sortBy, filterBy);
        }

//        TODO need convert seconds to datetime
//        and compare time when I click if it is newer take code else wait 3 second.

//cl
        return false;
    }

    private StringBuilder getConsoleLines(String command) {
        String[] executionOptions = {"bash", "-c", command};

        try {

            Process proc = Runtime.getRuntime().exec(executionOptions);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            StringBuilder out = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line).append("\n");

            }
            return out;

        } catch (IOException ioe) {
            System.out.println("Can't execute command:" + executionOptions[2] + " \nError message: " + ioe.getMessage());
        }


        return null;
    }

    private String matchStringToRegex(StringBuilder out, String regexString) {
        String sms = out.toString();
        System.out.println(sms);

//       So Regex is important part to find OTP code so I stole it :D
        Pattern p = Pattern.compile(regexString);
        System.out.println(p.matcher(sms));
        Matcher m = p.matcher(sms);

        if (m.find()) return m.group(1);
        return null;
    }


}
