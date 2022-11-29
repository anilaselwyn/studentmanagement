@TestNG @studentManagementApi
Feature: To test all the student management api's


  Scenario Outline: To test addition of a new student in the database
    Given using the relevant api "/studentmgmt/addStudent"
    When Adding a new student in database
      | firstName   | lastName   | studentId   | nationality   | studentClass   |
      | <firstName> | <lastName> | <studentId> | <nationality> | <studentClass> |
    Then Verify expected status code <statusCode>

    Examples:
      | firstName | lastName    | studentId | nationality | studentClass | statusCode |
      | Elon      | Musk        | 1145      | American    | Twitter      | 200        |
      | Elon      | Musk        | 1145      | American    | Twitter      | 500        |
      | Elon      | Musk        |           | American    | Twitter      | 400        |
      | Jeff      | Bezos       | 1146      | British     | Amazon       | 200        |
      | Mark      | Zuckerberg  | 1147      | American    | Meta         | 200        |



  Scenario Outline: To test deletion of a student in the database
    Given using the relevant api "/studentmgmt/deleteStudent"
    When Deleting a student in database with id <studentId>
    Then Verify expected status code <statusCode>

    Examples:
      | studentId | statusCode |
      | 11114    | 200        |


  Scenario Outline: To update existing student in the database
    Given using the relevant api "/studentmgmt/updateStudent"
    When Updating existing student information
      | firstName   | lastName   | studentId   | nationality   | studentClass   |
      | <firstName> | <lastName> | <studentId> | <nationality> | <studentClass> |
    Then Verify expected status code <statusCode>

    Examples:
      | firstName | lastName      | studentId | nationality | studentClass | statusCode |
      | Jeff      | Preston Bezos | 1146      | American    | Blue Origin  | 200        |


  Scenario Outline: Fetch student information based on Student class
    Given using the relevant api "/studentmgmt/fetchStudents"
    When fetching records based on students class <studentClass>
    Then Verify expected status code <statusCode>

    Examples:
      | studentClass  | statusCode |
      | "Meta"        | 200        |


  Scenario Outline: Fetch student information based on Student id
    Given using the relevant api "/studentmgmt/fetchStudents"
    When Fetching records based on studentId <studentId>
    Then Verify expected status code <statusCode>

    Examples:
      | studentId | statusCode |
      | 1146      | 200        |
      | 1147      | 200        |

  @test
  Scenario Outline: Fetch student information based on Student class and id
    Given using the relevant api "/studentmgmt/fetchStudents"
    When Fetching records based on studentClass <studentClass> and studentId <studentId>
    Then Verify expected status code <statusCode>
    Then Verify that response has right values first name <firstName> and last name <lastName> and nationality <nationality>

    Examples:
      | studentId | studentClass  | statusCode | firstName | lastName        | nationality |
      | 1146      | "Blue Origin" | 200        | "Jeff"    | "Preston Bezos" | "American"  |
      | 1147      | "Meta"        | 200        | "Mark"    | "Zuckerberg"    | "American"  |