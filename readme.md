# Test Framework for Student Management API and UI

## Description

This framework has test coverage for all the student management api's using rest assured and is a cucumber BDD style framework - http://localhost:9080/swagger-ui.html#/

UI Test cases use selenium-cucumber-java approach for testing http://the-internet.herokuapp.com 


## Installation (pre-requisites)
1. JDK 1.8+ (make sure Java class path is set)
2. Maven (make sure .m2 class path is set)
3. Chrome driver (for UI tests)
4. [Allure](https://github.com/allure-framework/allure2#download) 


### Executing program

* To run all tests in suite - `mvn clean test`
* Tag based run : use any tags added in feature files - `mvn clean test -Dtest=BaseRunner -Dcucumber.filter.tags="@test"`

 

## Reports 
Allure Report:
- You can generate a report using one of the following command `allure generate --clean`
- To generate the report to temp directory and view in a server - `allure serve`

