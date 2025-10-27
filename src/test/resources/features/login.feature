@smoke
Feature: Login functionality

  Background: 
    Given user is on login page
	
	
  Scenario: Test login with valid credentials
    When user enters username "mmadhukar.qa@gmail.com" and password "madhukarqa"
    And user clicks login
    Then user should see the home page

  Scenario: Test login with valid credentials
    When user enters username "mmadhukar.qa@gmail.com" and password "madhukarqa"
    And user clicks login
    Then user should see the home page

  Scenario: Test login Invalid credentials
    When user enters username "mmadhukar@gmail.com" and password "madhukarqa"
    And user clicks login
    Then user should see an error message

  Scenario Outline: Test Login with multiple credentials
    When user enters username "<username>" and password "<password>"
    And user clicks login
    Then user should see the home page

    Examples: 
      | username               | password   |
      #| wrong@gmail.com        | madhukar   |
      | mmadhukar.qa@gmail.com | madhukarqa |

  # use this when we need send test data for any 1 step only.
  Scenario: Test Login with multiple credentials
    When user enters username and password
      | username               | password   |
      | wrong@gmail.com        | madhukar   |
      | mmadhuk@gmail.com      | madhukarqa |
      | wro@gmail.com          | madhukar   |
      | mmadhu@gmail.com       | madhukarqa |
      | w@gmail.com            | madhukar   |
      | mmadhukar.qa@gmail.com | madhukarqa |
    And user clicks login
    Then user should see the home page
