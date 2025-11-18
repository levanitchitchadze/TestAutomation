@smoke
Feature: Fe-003: Home

  Scenario Outline: "<platform>" Home page menu bar
    Given Home page is open
    When User clicks menu bar element
    Then  It changes page


    Examples:
      | platform |
      | android  |
