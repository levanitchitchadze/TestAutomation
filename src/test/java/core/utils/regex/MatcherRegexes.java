package core.utils.regex;

public interface MatcherRegexes {

    String EMAIL_VALIDATOR_REGEX = ".+@.+";

    String TBC_SMS_OTP_MESSAGES_REGEX = "\\b(\\d{4,8})\\b";
    String UNIX_TIME_REGEX = "(?<=date=)\\d{12,15}";
    String GET_VALUE_INSIDE_QUOTES = "\"([^\"]*)\"";


}
