Feature: Rest API - Create user

  Scenario Outline: Successful user creation
    Given request body:
    """
    {
      "name": "<name>",
      "gender": "<gender>",
      "email": "<email>",
      "status": "<status>"
    }
    """
    When user performs "POST" request to "/public/v2/users" using "RestApi"
    Then response contains status 201
    And response body id is not null
    And response body name is "<name>"
    And response body email is "<email>"
    And response body gender is "<gender>"
    And response body status is "<status>"

    Examples:
      | name                 | email                              | gender | status |
      | Cucumber RestAssured1 | cucumber.restAssured111@test15ce.com | male   | active |
      | Cucumber RestAssured2 | cucumber.restAssure222@test15ce.com | male   | active |

  Scenario: Unsuccessful user creation (user email already registered)
    Given request body:
    """
    {
      "name": "Tenali Ramakrishna",
      "gender": "male",
      "email": "tenali.ramakrishna@15ce.com",
      "status": "active"
    }
    """
    When user performs "POST" request to "/public/v2/users" using "RestApi"
    Then response contains status 422
    And response body contains error field value "email"
    And error message "has already been taken"
