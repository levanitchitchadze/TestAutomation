package core.utils.hellper;

import core.base.TestBase;
import io.restassured.specification.RequestSpecification;

public class APIHelper extends TestBase {

    protected RequestSpecification client = ctx.getApiDriver();

}
