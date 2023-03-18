Feature: Measurement Unit Conversion
  This feature provides a range of scenarios corresponding to the
  intended external behaviour of converting a measure to another unit.

  # This is just a comment.
  # You can start with a Background: that will be run before executing each scenario.

  Background:
    Given I initialise a value at 1

  # Each scenario can be seen as a test that can be executed with JUnit,
  # provided that each of the steps (Given, When, And and Then) are
  # implemented in a Java mapping file (CalculatorSteps.Java)

  Scenario: Converting meter to kilometer
    Given a type of converter 'Length' and a first unit 'METER'
    When I provide a second unit 'KILOMETER'
    Then the unit conversion evaluates to 1000

