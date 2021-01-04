Feature: Integer Arithmetic Expressions
  This feature provides a range of scenarios corresponding to the
  intended behaviour of arithmetic expressions on integers.

  # This is just a comment. Below, you can see a list of scenarios.
  # Each scenario can be seen as a test that can be executed with JUnit,
  # provided that each of the steps (Given, When, And and Then) are
  # implemented in a Java mapping file (CalculatorSteps.Java)

  Scenario: Adding two integer numbers
    Given an arithmetic expression
    When I provide a first number 4
    And I provide a second number 5
    Then the sum is 9

  Scenario: Printing the sum of two integer numbers
    Given the sum of two numbers 8 and 6
    Then its INFIX notation is ( 8 + 6 )
    And its PREFIX notation is + (8, 6)
    And its POSTFIX notation is (8, 6) +

  # This is an example of a scenario in which we provide a list of numbers as input.
  # (In fact, this is not entirely true, since what is given as input is a table of
  # strings. In this case, the table is of dimension 1 * 3 (1 line and three columns).
  Scenario: Adding a list of integer numbers
    Given the following list of numbers
      | 3 | 5 | 7 |
    Then the sum is 15

  # A scenario outline (or template) is a scenario that is parameterised
  # with different values. The outline comes with a set of examples.
  # The scenario will be executed with each of the provided inputs.
  Scenario Outline: Adding  two integer numbers
    Given an arithmetic expression
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the sum is <result>

    Examples:
      |n1|n2|result|
      |4|5|9|
      |5|3|8|

  Scenario: Subtracting two integer numbers
    Given an arithmetic expression
    When I provide a first number 7
    And I provide a second number 5
    Then the difference is 2

  Scenario: Multiplying two integer numbers
    Given an arithmetic expression
    When I provide a first number 7
    And I provide a second number 5
    Then the product is 35

  Scenario Outline: Dividing two integer numbers
    Given an arithmetic expression
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the quotient is <result>

    Examples:
      |n1|n2|result|
      |35|5|7|
      |7|5|1|
      |5|7|0|
