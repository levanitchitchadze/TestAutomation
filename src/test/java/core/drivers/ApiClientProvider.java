package core.drivers;

import core.data.api.BaseData;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClientProvider {

    private static RequestSpecification client;

    public static RequestSpecification getClient() {
        if (client == null) {
            client = given()
                    .baseUri(BaseData.getBASE_URL())
                    .header("Content-Type", "application/json")
                    .log()
                    .all();

        }
        return client;
    }

}
