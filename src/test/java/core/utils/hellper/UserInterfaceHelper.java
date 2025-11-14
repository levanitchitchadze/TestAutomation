package core.utils.hellper;

import org.openqa.selenium.WebElement;

public interface UserInterfaceHelper {

    WebElement get(String id);

    WebElement getx(String xpath);

    void click(WebElement element);

    void click(String selector);

    String textOf(String id);

    void type(String id, String text);

    boolean isDisplayed(String id);

    boolean isNotDisplayed(String id);

    WebElement waitFor(WebElement element);

    void waitTimeOut(int waitOfSeconds);

    WebElement waitFor(WebElement element, int maxSeconds);

    WebElement waitFor(String selector);

    boolean isDisplayed(String id, int maxSeconds);

    boolean isNotDisplayed(String id, int maxSeconds);
}
