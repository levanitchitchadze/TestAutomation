package core.data;

import lombok.Getter;

import java.util.Map;


@Getter
public class BaseData {


    private final String BASE_URL = "https://reqres.in/api/";
    private Map<String, ?> API_DEFAULT_HEADERS = Map.ofEntries(
            Map.entry("Content-Type", "application/json"),
            Map.entry("x-api-key", "reqres-free-v1")
    );


}
