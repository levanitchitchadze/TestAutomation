package core.utils.api;

import core.data.BaseData;
import core.enums.HTTP;
import core.factories.ApiFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class APIRequestBuilder {

    public static RequestSpecification requestSpecification;
    private HTTP method;
    private String uri;
    private Map<String, Object> queryParams = new HashMap<>();
    private Map<String, Object> pathParams = new HashMap<>();
    private Object body;

    private BaseData baseData = new BaseData();

    public APIRequestBuilder method(HTTP method) {
        this.method = method;
        return this;
    }

    public APIRequestBuilder uri(String uri) {
        this.uri = uri;
        return this;
    }

    public APIRequestBuilder query(String key, Object value) {
        this.queryParams.put(key, value);
        return this;
    }

    public APIRequestBuilder query(Map<String, Object> query) {
        this.queryParams.putAll(query);
        return this;
    }

    public APIRequestBuilder path(String key, Object value) {
        this.pathParams.put(key, value);
        return this;
    }

    public APIRequestBuilder path(Map<String, Object> path) {
        this.pathParams.putAll(path);
        return this;
    }

    public APIRequestBuilder body(Object body) {
        this.body = body;
        return this;
    }


    public Response send() {
        RequestSpecification req = requestSpecification.given();
        requestSpecification = new ApiFactory().getDriver();
        if (!queryParams.isEmpty()) req.queryParams(queryParams);
        if (!pathParams.isEmpty()) req.pathParams(pathParams);
        if (body != null) req.body(body);

        return switch (method) {
            case HTTP.GET -> req.get(uri);
            case HTTP.POST -> req.post(uri);
            case HTTP.PUT -> req.put(uri);
            case HTTP.PATCH -> req.patch(uri);
            case HTTP.DELETE -> req.delete(uri);
        };

    }


}
