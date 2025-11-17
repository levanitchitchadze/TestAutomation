package core.drivers;

import core.data.BaseData;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ApiClientProvider implements IDriverProvider<RequestSpecification> {

    private static RequestSpecification client;
    private BaseData baseData = new BaseData();


    @Override
    public RequestSpecification restart() {
        client = null;
        create();
        return client;
    }

    @Override
    public RequestSpecification getDriver() {

        create();
        return client;
    }

    @Override
    public void create() {
        client = given()
                .baseUri(baseData.getBASE_URL())
                .headers(baseData.getAPI_DEFAULT_HEADERS())
                .log()
                .all();

    }

    @Override
    public void close() {

    }

}
