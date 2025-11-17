package core.data;

import lombok.Getter;
import net.datafaker.Faker;
import net.datafaker.providers.base.Credentials;

@Getter
public class TestDataClass {
    protected Faker faker = new Faker();
    protected Credentials fakeCredentials = faker.credentials();

}
