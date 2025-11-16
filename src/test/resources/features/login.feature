Feature: Fe-001: Login

  Scenario Outline: "<platform>" login Attempts
    Given The user opened the application
    And "allowed" notification permissions
    And Chose the <language> language

    When User enters "<username>" and "<password>" and click submit button
    Then  "<result>"



    Examples:
      | platform | username    | password    | language | result                                       |
      | android  | correctUser | correctPass | GEO      | The OTP page should be displayed             |
      | android  | wrongUser   | correctPass | GEO      | The wrong attempt window should be displayed |
      | android  | correctUser | wrongPass   | GEO      | The wrong attempt window should be displayed |
      | android  | wrongUser   | wrongPass   | GEO      | The wrong attempt window should be displayed |


