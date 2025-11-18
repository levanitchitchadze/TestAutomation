@smoke
Feature: Fe-002: OTP page

#  Scenario Outline: OTP resend on "<platform>"
#    Given OTP page is open
#    And Received OTP code
#    When Click resend button
#    Then Should receive new OTP code
#
#    Examples:
#      | platform |
#      | android  |


  Scenario Outline: OTP entering attempts on "<platform>"
    Given OTP page is open
    And Received OTP code
    When Enter "<valid>" OTP
    Then The wrong OTP attempt window "<result>"

    Examples:
      | platform | valid   | result
      | android  | invalid | should be displayed
      | android  | correct | should not displayed
