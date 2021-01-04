Feature: Binary Arithmetic Expressions

  Scenario: Adding two integer numbers
    Given an arithmetic expression
    When I provide a first number 4
    And I provide a second number 5
    Then the sum is 9

  Scenario Outline: Adding  two integer numbers
    Given an arithmetic expression
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the sum is <result>

    Examples:
      |n1|n2|result|
      |4|5|9|
      |5|3|8|

  Scenario: Printing the sum of two integer numbers
    Given the sum of two numbers 8 and 6
    Then its INFIX notation is ( 8 + 6 )
    And its PREFIX notation is + (8, 6)
    And its POSTFIX notation is (8, 6) +

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
