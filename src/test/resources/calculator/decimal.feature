Feature: Decimal Arithmetic Expressions
  this feature provides a range of scenarios for testing decimal arithmetic


  Background:
    Given I initialise a calculator

  Scenario Outline: Evaluating arithmetic operations with two integer parameters
    Given an integer operation <op>
    When I provide a first number <n1> as decimal
    And I provide a second number <n2> as decimal
    Then the operation evaluates to <res> in decimal

    Examples:
      | op  |n1|n2|res|
      | "+" | 4.4| 3.3| 7.70|
      | "-" | 4.4| 3.3| 1.10|
      | "*" | 4.4| 3.3| 14.52|
      | "/" | 4.4| 3.3| 1.33 |
      | "+" | 8.2 | 3.3 | 11.50 |
      | "-" | 8.2 | 3.3 | 4.90 |
      | "*" | 8.2 | 3.3 | 27.06 |
      | "/" | 8.2 | 3.3 | 2.48 |
      | "+" | 4.4 | 8.2 | 12.60 |
      | "-" | 4.4 | 8.2 | -3.80 |
      | "*" | 4.4 | 8.2 | 36.08 |
      | "/" | 4.4 | 8.2 | 0.54 |
      | "+" | 8.2 | 4.4 | 12.60 |
      | "-" | -8.2 | 4.4 | -12.60 |
      | "*" | 8.2 | -4.4 | -36.08 |
      | "/" | 8.2 | -4.4 | -1.86 |
      | "+" | 4.4 | -8.2 | -3.80 |