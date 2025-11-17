package core.utils.url;

import java.util.HashMap;
import java.util.Map;

public class QueryBuilder {
    private final Map<String, String> params = new HashMap<>();

    public QueryBuilder add(String key, String value) {
        params.put(key, value);
        return this;
    }

    public Map<String, String> build() {
        return params;
    }

}

