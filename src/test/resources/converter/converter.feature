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
    Then the unit conversion evaluates to 0.001

  # A scenario outline (or template) is a scenario that is parameterised
  # with different values. The outline comes with a set of examples.
  # The scenario will be executed with each of the provided inputs.
  Scenario Outline: Converting the value to multiple unit
    Given a type of converter '<conv>' and a first unit '<u1>'
    When I provide a second unit '<u2>'
    Then the unit conversion evaluates to <result>

    Examples:
      |conv|u1|u2|result|
      |Length|METER|YARD|1.09361|
      |Length|METER|FEET|3.28084|
      |Length|METER|MILE|0.000621371|
      |Currency|EURO|YEN|142.10|
      |Currency|EURO|DOLLAR|1.08|
      |Temperature|CELSIUS|KELVIN|274.15|
      |Temperature|CELSIUS|FAHRENHEIT|33.8|
      |Time|HOUR|MINUTE|60|
      |Time|HOUR|SECOND|3600|
      |Time|HOUR|DAY|0.041666666666666664|
      |Energy|JOULE|MEGA_JOULE|0.000001|
      |Energy|JOULE|WATT_HOUR|0.0002777777777777778|
      |Energy|JOULE|CALORIE|0.0002390057361376673|
      |Mass|KILOGRAM|GRAM|1000|
      |Mass|KILOGRAM|POUND|2.20462|
      |Mass|KILOGRAM|OUNCE|35.274|


