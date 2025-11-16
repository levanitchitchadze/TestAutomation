package core.config.cucumber;

import core.enums.Language;
import io.cucumber.java.ParameterType;

public class ParameterTypes {
    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }

    @ParameterType("GEO|ENG|RUS|KZ")
    public Language language(String lang) {
        return Language.valueOf(lang.toUpperCase());
    }
    
}
