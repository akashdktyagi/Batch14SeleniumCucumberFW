Feature: Temp

  @datatable
  Scenario: Example Scn
    Given I want to do smthing
    When I have a list of items to send
      | akash    |
      | shraddha |
      | chetna   |
      | romali   |
    When I have students and their marks
      | akash        | 34    |
      | shraddha     | 45    |
      | chetna       | 56    |
      | romali       | 67    |
    Then something should happen

    @Example
    Scenario Outline: I want to search for the product
      Given I am on the search page
      When I search for a product as "<product>"
      When I have a list of items to send
        | <product>    |
      Then result should be displayed related to "<product>"

      Examples:
        |product|
        |computer|
        |mobile  |
        |earphone|
        |blutooth|
        |keyboard|
        |shoes   |

