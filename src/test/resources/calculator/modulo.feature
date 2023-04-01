Feature: Integer Arithmetic Expressions with modulo
  This feature describes the behaviour of a calculator that can perform
  integer arithmetic operations on numbers with a modulo.

  # This is just a comment.
  # You can start with a Background: that will be run before executing each scenario.

  Background:
    Given I initialise a calculator

  Scenario: Two numbers modulo
    Given an integer operation 'mod'
    When I provide a first number 5
    And I provide a second number 4
    Then the operation evaluates to 1

  Scenario: Inverse modulo
    Given an integer operation 'mod-1'
    When I provide a first number 4
    And I provide a second number 17
    Then the operation evaluates to 13