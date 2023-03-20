Feature: Integer Arithmetic Expressions with given radix
  This feature describes the behaviour of a calculator that can perform
  integer arithmetic operations on numbers with a given radix.

  # This is just a comment.
  # You can start with a Background: that will be run before executing each scenario.

  Background:
    Given I initialise a calculator

  # Each scenario can be seen as a test that can be executed with JUnit,
  # provided that each of the steps (Given, When, And and Then) are
  # implemented in a Java mapping file (CalculatorSteps.Java)

  Scenario: Adding two numbers in radix 10
    Given an integer operation '+'
    When I provide a first number '5' in radix 10
    And I provide a second number '4' in radix 10
    Then the operation evaluates to '9' in radix 10

  Scenario: Adding two numbers in radix 2
    Given an integer operation '+'
    When I provide a first number '01' in radix 2
    And I provide a second number '01' in radix 2
    Then the operation evaluates to '10' in radix 2

  Scenario: Adding two numbers in radix 16
    Given an integer operation '+'
    When I provide a first number '8AB' in radix 16
    And I provide a second number 'B78' in radix 16
    Then the operation evaluates to '1423' in radix 16

  Scenario: Adding two numbers in many radix
    Given an integer operation '+'
    When I provide a first number '10' in radix 10
    And I provide a second number '5' in radix 10
    Then the operation evaluates to '17' in radix 8

  Scenario: Adding two numbers in many radix
    Given an integer operation '+'
    When I provide a first number '10' in radix 10
    And I provide a second number '5' in radix 10
    Then the operation evaluates to '17' in radix 8

  Scenario: Notation two numbers in radix 36
    Given an integer operation '+'
    When I provide a first number 'a' in radix 36
    And I provide a second number 'k' in radix 36
    Then the operation evaluates to 'u' in radix 36

  Scenario: Notation two numbers in radix 36
    Given an integer operation '+'
    When I provide a first number 'a' in radix 36
    And I provide a second number 'k' in radix 36
    Then its INFIX notation is ( a + k )
    And its PREFIX notation is + (a, k)
    And its POSTFIX notation is (a, k) +
