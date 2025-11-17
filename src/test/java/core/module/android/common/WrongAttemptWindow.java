package core.module.android.common;

import core.utils.hellper.AppiumHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WrongAttemptWindow extends AppiumHelper {

    public boolean wrongAttemptWindowIsDisplayed(boolean click, String WRONG_ATTEMPT_WINDOW_BTN) {
        if (isDisplayed(WRONG_ATTEMPT_WINDOW_BTN)) {

            if (click) click(WRONG_ATTEMPT_WINDOW_BTN);

            return true;
        }
        return false;
    }

    public boolean isWrongAttemptWindowDisplayed(boolean click, String WRONG_ATTEMPT_WINDOW_BTN, String WRONG_ATTEMPT_WINDOW_TEXT, String windowText) {
        waitTimeOut(10);
        if (isDisplayed(WRONG_ATTEMPT_WINDOW_BTN)) {

            if (!textOf(WRONG_ATTEMPT_WINDOW_TEXT).equals(windowText))
                log.error("Wrong attempt window doesn't have correct text");


            if (click) click(WRONG_ATTEMPT_WINDOW_BTN);
            return true;
        }
        return false;
    }
}
