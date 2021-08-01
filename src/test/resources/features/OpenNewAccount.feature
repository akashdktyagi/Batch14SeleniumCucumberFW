Feature: Open New Account Feature

  @open_new_account
  Scenario: User is able to Open a new account
    Given User is logged in
    And User clicked on link "Open New Account"
#    When User select account as "SAVINGS" and account number as "13344"
    When User select account as "SAVINGS" and any account number
    And User clicks on Button Open New Account
    Then Account Opened Message is Displayed
    And a new account number is generated