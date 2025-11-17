package core.drivers;

public interface IDriverProvider<T> {
    void create();

    void close();

    Object restart();

    //    Object getDriver(); // AppiumDriver<?> ან WebDriver
    T getDriver();
}
