@APITest
Feature: Fe-004: User Api feature

  Scenario: Get users records with "api"
    Given User have right permissions
    When Send api-users GET request
    Then User gets users list

  Scenario: Create users record with "api"
    Given User have right permissions
    When Send api-users POST request
    Then User record created


  Scenario: Update user with "api"
    Given User have right permissions
    When Send api-users PUT request
    Then User record updated


  Scenario: Part Update user "api"
    Given User have right permissions
    When Send api-users PATCH request
    Then User record updated


  Scenario: Delete user "api"
    Given User have right permissions
    When Send api-users DELETE request
    Then User record deleted

