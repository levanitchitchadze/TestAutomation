package core.utils.url;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {
    private String path;
    private Map<String, String> queryParams = new HashMap<>();

    public RequestBuilder path(String path) {
        this.path = path;
        return this;
    }

    public RequestBuilder query(Map<String, String> params) {
        this.queryParams = params;
        return this;
    }

    public String buildUrl() {
        StringBuilder url = new StringBuilder(path);

        if (!queryParams.isEmpty()) {
            url.append("?");
            queryParams.forEach((k, v) -> url.append(k).append("=").append(v).append("&"));
            url.deleteCharAt(url.length() - 1);
        }

        return url.toString();
    }
}
