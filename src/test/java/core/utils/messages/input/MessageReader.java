package core.utils.messages.input;

import core.utils.hellper.AppiumHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static core.utils.regex.MatcherRegexes.TBC_SMS_OTP_MESSAGES_REGEX;
import static core.utils.regex.MatcherRegexes.UNIX_TIME_REGEX;

@Slf4j
public class MessageReader {

    private static final String url = "content://sms/inbox";
    private final String deviceSerialNumber;
    private AppiumHelper appiumHelper = new AppiumHelper();

    private Date now;
    private int timeInSecond = 0;

    public MessageReader(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }


    public String getOTPCode(String filterBy, String sortBy) {

        appiumHelper.waitTimeOut(2);
        now = new Date(System.currentTimeMillis() - (3 * 1000));


        String regexString = TBC_SMS_OTP_MESSAGES_REGEX;

        String latestRowsCount = "10";

        String command = String.format(
                "adb -s %s shell 'content query --uri %s --projection Date:body --sort \"%s DESC\"' | grep '%s' | head -n %s",
                deviceSerialNumber, url, sortBy, filterBy, latestRowsCount);

        String out = getConsoleLines(command);

        return matchStringToRegex(out, regexString);

    }

//    public boolean checkIfOTPReceived(String sortBy, String filterBy) {
//        short maxWaitingTime = 60;
//        String command = String.format(
//                "adb -s %s shell content query --uri %s --projection date --sort \\\"%s DESC\\\" | grep '%s' | head -n 1",
//                deviceSerialNumber, url, sortBy, filterBy);
//
//
//        StringBuilder out = getConsoleLines(command);
//        if (out == null) {
//            appiumHelper.waitTimeOut(3);
//            checkIfOTPReceived(sortBy, filterBy);
//        }
//
//
//        String matchedText = matchStringToRegex(out, "\\d{13}");
//
//        if (matchedText == null) {
//            appiumHelper.waitTimeOut(3);
//            checkIfOTPReceived(sortBy, filterBy);
//        }
//
//        return false;
//    }

    private String getConsoleLines(String command) {
        String[] executionOptions = {"bash", "-c", command};
        appiumHelper.waitTimeOut(10);
        try {

            Process proc = Runtime.getRuntime().exec(executionOptions);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            StringBuilder out = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line).append("\n");
                if (!checkOTPDate(line)) {
                    appiumHelper.waitTimeOut(2);
                    timeInSecond += 2;
                    if (timeInSecond > 90) {
                        log.error("Can't receive OTP code");
                        break;
                    }
                    return getConsoleLines(command);
                } else {
                    return line;
                }
            }
            return out.toString();

        } catch (IOException ioe) {
            log.error("Can't execute command:" + executionOptions[2] + " \nError message: " + ioe.getMessage());
            throw new RuntimeException("Can't execute command:" + executionOptions[2] + " \nError message: " + ioe.getMessage());
        }


    }

    private boolean checkOTPDate(String msg) {
        String timeRegex = UNIX_TIME_REGEX;
        Matcher matcher = Pattern.compile(timeRegex).matcher(msg);

        if (!matcher.find()) return false;

        Date msgDate = new Date(Long.parseLong(matcher.group(0)));

        return msgDate.after(now);
    }

    private String matchStringToRegex(String out, String regexString) {

//       So Regex is important part to find OTP code so I stole it :D
        Pattern p = Pattern.compile(regexString);
        Matcher m = p.matcher(out);


        if (m.find()) return m.group(1);


        return null;
    }


}
