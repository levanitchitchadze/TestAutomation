Feature: Login feature

  Scenario: Successful login
    Given The user opened the application
    And Accepted notification permissions
    And Chose the Georgian language
    When User enters correct credentials and click submit button
    Then The home page should be displayed