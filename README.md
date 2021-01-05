# Calculating arithmetic expressions

## About

This repository contains Java 11 code for computing arithmetic expressions. It is deliberately incomplete as it serves to be the basis of all kinds of extensions,
such as a more sophisticated Calculator application.

The code was written by Tom Mens in January 2021 for educational purposes.
(Software Engineering Lab, Faculty of Sciences, University of Mons, Belgium)


## Unit testing and BDD

The example code is accompanied by a set of JUnit 5 unit tests.
The example code is accompanied by a set of Cucumber BDD scenarios, also running in Junit.

## Maven instructions

The code is accompanied by a pom.xml file so that it can be installed, compiled, tested and run using Maven, an open source build automation tool provided by Apache.

Upon first use of the code in this repository, you will need to run
  "mvn install"
to ensure that all required project dependencies (e.g. for Java, JUnit, Cucumber, and Maven) will be downloaded and installed locally.

Assuming you have a sufficiently recent version of Maven installed (the required versions are specified as properties in the POM file), you can compile the source code using
  "mvn compile"
and then execute the main class of the Java code using
  "mvn exec:java"
  
The tests and BDD scenarios are executable with Maven using
  "mvn test"
Note that the tests are also executed when you do a "mvn install". It is possible to skip those tests by providing an extra parameter. For details of more advanced uses of Maven, please refer to its official documentation.

## Licence

This code is licensed as CC BY-SA 4.0 (Creative Commons Attribution-ShareAlike 4.0 International)
https://creativecommons.org/licenses/by-sa/4.0/


