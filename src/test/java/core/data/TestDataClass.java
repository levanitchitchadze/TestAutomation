package core.data;

import net.datafaker.Faker;
import net.datafaker.providers.base.Credentials;

public class TestDataClass {
    protected Faker faker = new Faker();
    protected Credentials fakeCredentials = faker.credentials();

}
