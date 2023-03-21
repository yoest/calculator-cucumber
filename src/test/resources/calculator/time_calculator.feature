Feature: Integer Arithmetic Expressions
  This feature provides a range of scenarios corresponding to the
  intended external behaviour of arithmetic expressions on integers.

  Background:
    Given I initialise a time calculator


  Scenario: Adding two times together
    Given a time operation '+'
    When I provide a first number of days 1
    And I provide a second number of days 2
    Then the time operation evaluates to 259200

  Scenario: Subtracting two times
    Given a time operation '-'
    When I provide a first number of days 4
    And I provide a second number of days 3
    Then the time operation evaluates to 86400

  Scenario: Printing the sum of two times
    Given the sum of one number of days 2 and a second number of days 3
    Then for the given times its INFIX notation is ( 2 days + 3 days )
    And for the given times its PREFIX notation is + (2 days, 3 days)
    And for the given times its POSTFIX notation is (2 days, 3 days) +

  Scenario: Evaluation time operations over a list of time event
    Given the following list of time events representing hours
      | 15:30 | 3:40:25 | 1:20:01 |  55800 13225 4801
    Then for the given times the sum is 73826
    And for the given times the difference is 37774

  Scenario Outline: Adding two time event
    Given a time operation '+'
    When I provide a first number of days <n1>
    And I provide a second number of days <n2>
    Then the time operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |2|4|518400|
      |5|3|691200|

  Scenario Outline: Subtracting two time event
    Given a time operation '-'
    When I provide a first number of days <n1>
    And I provide a second number of days <n2>
    Then the time operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |4|2|172800|
      |11|5|518400|

  Scenario Outline: Evaluating arithmetic operations with two time event
    Given a time operation <op>
    When I provide a first number of days <n1>
    And I provide a second number of days <n2>
    Then the time operation evaluates to <result>

    Examples:
      | op  |n1|n2|result|
      | "+" | 4| 5|777600|
      | "-" | 8| 5|259200|
