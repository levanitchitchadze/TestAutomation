package core.model.users;


import lombok.Data;

@Data
public class CreateUserRequest {

    String name;
    String job;
}


//    {
//        "name": "morpheus",
//        "job": "leader"
//    }