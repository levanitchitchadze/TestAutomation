package core.drivers;

import core.data.HomeData;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ApiClientProvider implements IDriverProvider<RequestSpecification> {

    private static RequestSpecification client;


    @Override
    public RequestSpecification getDriver() {
        if (client == null) {
            create();
        }
        return client;
    }


    @Override
    public void create() {
        client = given()
                .baseUri(HomeData.BASE_URL)
                .header("Content-Type", "application/json")
                .log()
                .all();

    }

    @Override
    public void close() {

    }

    @Override
    public Object restart() {
        client = null;
        create();
        return client;
    }

}
