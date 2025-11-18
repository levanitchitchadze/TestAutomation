@smoke
Feature: Fe-001: Login

  Scenario Outline: "<platform>" "<attemptType>"
    Given The user opened the application
    And allow notification permissions
    And Chose the GEO language
    And User has "<usernameType>" username and "<passwordType>" password
    When User enters credentials
    Then The wrong login attempt window "<result>"


    Examples:
      | platform | attemptType                             | usernameType | passwordType | result               |
      | android  | login Attempts with invalid username    | invalid      | correct      | should be displayed  |
      | android  | login Attempts with invalid credentials | invalid      | invalid      | should be displayed  |
      | android  | login Attempts with correct credentials | correct      | correct      | should not displayed |
#
