Feature: Login Feature

  # We can call it:
  # User Story, User Journey, Behaviour, Requirement, Scenario, Test Case

  Scenario: User is able to Login in the the application
    Given User opened the browser
    And User navigated to the application url
#    When User enter username as "john" and password as "demo" and click on login button
    When User enter username and password as in below table and click on login button
      |username|john|
      |password| demo|
    Then User is able to login in the application

  Scenario: User is able to Login in the the application
    Given User opened the browser
    And User navigated to the application url
    When User enter username as "akash" and password as "password" and click on login button
    Then User is able to login in the application
