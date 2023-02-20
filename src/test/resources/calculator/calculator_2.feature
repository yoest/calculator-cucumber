Feature: Integer Arithmetic Expressions
  This feature provides a range of scenarios corresponding to the
  intended external behaviour of arithmetic expressions on integers.

  Background:
    Given I initialise a calculator

  Scenario Outline: Printing the operation of integers
    Given an integer operation <op>
    When I provide a first number 8
    And I provide a second number 6
    Then its <nota> notation is <res>
    And its <nota> notation is <res>
    And its <nota> notation is <res>

    Examples:
      |op|nota|res|
      |'*'|PREFIX| * (8, 6)|
      |'+'|PREFIX| + (8, 6)|
      |'/'|PREFIX| / (8, 6)|
      |'-'|PREFIX| - (8, 6)|
      |'*'|INFIX| ( 8 * 6 )|
      |'+'|INFIX| ( 8 + 6 )|
      |'/'|INFIX| ( 8 / 6 )|
      |'-'|INFIX| ( 8 - 6 )|
      |'*'|POSTFIX| (8, 6) *|
      |'+'|POSTFIX| (8, 6) +|
      |'/'|POSTFIX| (8, 6) /|
      |'-'|POSTFIX| (8, 6) -|