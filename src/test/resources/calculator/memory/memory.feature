Feature: Memory Calculator

  This feature provides a range of scenarios corresponding to the
  intended external behavior of the Memory Calculator.

  Background:
    Given I initialise a MemoryCalculator

  Scenario: Evaluate a single expression and save it in the memory
    Given I have an expression with value 2
    When I evaluate the expression
    And the expression is saved in the memory

  Scenario: Undo last expression
    When I have an expression with value 2
    And I evaluate the expression
    And I have an expression with value 3
    And I evaluate the expression
    And I undo the last expression
    Then the loaded expression should be 2

  Scenario: Redo last expression
    When I have an expression with value 2
    And I evaluate the expression
    And I have an expression with value 3
    And I evaluate the expression
    And I undo the last expression
    And I redo the last expression
    Then the loaded expression should be 3

  Scenario: Save the history of expressions in a file
    Given I have an expression with value 2
    And I evaluate the expression
    And I have an expression with value 3
    And I evaluate the expression
    And I save the history of expressions in a file
    Then the file should contain the following expressions:
      | 2 |
      | 3 |