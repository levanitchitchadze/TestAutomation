package core.config;

import lombok.Getter;

@Getter
public class AppiumConfig {
    private static AppiumConfig instance;
    private String URL = "http://127.0.0.1";
    private int PORT = 4723;

    public static synchronized AppiumConfig getInstance() {

        if (instance == null) {
            instance = new AppiumConfig();
        }

        return instance;
    }
}
