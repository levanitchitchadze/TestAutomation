package core.data.app;


import lombok.Getter;
import lombok.Setter;

// You think I could save my credentials in these pity class ? No!
// I keep simple testing data here so I don't have to modify the code for small changes.
@Getter
@Setter
public class LoginData {

    private String username = "";
    private String password = "";


}
