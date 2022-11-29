Feature: Login Functionality Feature

  Scenario Outline: Login Functionality
    Given user navigates to "http://the-internet.herokuapp.com"
    Then user clicks on <linktext> and link <link>
    When user logs in using username as <username> and password <password>
    Then login should be successful

    Examples:
      | linktext              | link     | username   | password               |
      | "Form Authentication" | "/login" | "tomsmith" | "SuperSecretPassword!" |