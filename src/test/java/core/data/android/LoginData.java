package core.data.android;


import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.Setter;
import net.datafaker.Faker;
import net.datafaker.providers.base.Credentials;

// You think I could save my credentials in these pity class ? No!
// I keep simple testing data here so I don't have to modify the code for small changes.
@Getter
@Setter
public class LoginData {
    Faker faker = new Faker();
    Credentials fakeCredentials = faker.credentials();
    private String incorrectUsername = fakeCredentials.username();
    private String incorrectPassword = fakeCredentials.password(8, 20, true, true, true);
    private String username = Dotenv.load().get("username");
    private String password = Dotenv.load().get("password");


}
