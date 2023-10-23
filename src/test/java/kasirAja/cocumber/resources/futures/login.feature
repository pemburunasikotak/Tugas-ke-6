Feature: Login page Aplikasi kasir aja

  Scenario: Success Login
    Given Halaman login kasir aja
    When Input username
    And Input password
    And Click Login button
    Then user in dashboard page


  Scenario: Failed Login
    Given Halaman login kasir aja
    When Input username
    And Input Invalid password
    And Click Login button
    Then User get Error massage

  @TDD
  Scenario Outline: User login to Kasir Aja
    Given Halaman login kasir aja
    When I input <email> as email
    And I input <password> as password
    And Click Login button
    Then I verify <status> login result

    Examples:
    | email                 | password    | status |
    | failed-login@gmail.com| failed-login| failed |
    | tdd-selenium@gmail.com| tdd-selenium| success|