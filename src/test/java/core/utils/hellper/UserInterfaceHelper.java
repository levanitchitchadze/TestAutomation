package core.utils.hellper;

import org.openqa.selenium.WebElement;

public interface UserInterfaceHelper {
    WebElement findById(String id);

    WebElement findByXpath(String xpath);

    void click(WebElement element);

    void click(String selector);

    String text_of(String id);

    void type(String id, String text);

    boolean isDisplayed(String id);

    boolean isNotDisplayed(String id);

    WebElement waitFor(WebElement element);

    void waitTimeOut(int waitOfSeconds);

    WebElement waitFor(WebElement element, int maxSeconds);

    boolean isDisplayed(String id, int maxSeconds);

    boolean isNotDisplayed(String id, int maxSeconds);
}
