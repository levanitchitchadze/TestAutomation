package pom.module.api_controller;


import lombok.Getter;


// There we have selectors for login page i use lombok to automatically generate Getters,
// but I can write it myself I swear.
@Getter
public class LoginPage {

    
    private final String notificationWindow="";
    private final String notificationAllowBtn="com.android.permissioncontroller:id/permission_allow_button";
    private final String notificationDenyBtn="com.android.permissioncontroller:id/permission_allow_button";



}
