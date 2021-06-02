
Feature: test #1 - DB changing cell value functionality

  Scenario: check the current cell value depending on the button clicked by the user
    Given column userId exists in the DB
    And column state exists in the DB
    And state column = null
    When user with customerId '12345' clicks "button"
    Then column state should have "value"
#    Examples:
#    |button | value       |
#    |STR    | started     |
#    |END    | ended       |
#    |CONT   | in progress |


