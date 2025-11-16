Feature: Fe-002: OTP page

  Scenario Outline: OTP resend on "<platform>"
    Given OTP page is open
    And Received OTP code
    When Click resend button
    Then Should receive new OTP code

  Scenario Outline: OTP entering attempts on "<platform>"
    Given OTP page is open
    And Received OTP code
    When Enter OTP code
    Then <result>





    Examples:
      | platform | result                                       |
      | android  | The home page should be displayed            |
      | android  | The wrong attempt window should be displayed |
      | android  | The wrong attempt window should be displayed |
      | android  | The wrong attempt window should be displayed |
