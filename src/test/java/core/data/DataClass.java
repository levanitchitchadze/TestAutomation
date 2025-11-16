package core.data;

import net.datafaker.Faker;
import net.datafaker.providers.base.Credentials;

public class DataClass {
    protected Faker faker = new Faker();
    protected Credentials fakeCredentials = faker.credentials();

}
