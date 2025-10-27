@login
Feature: Login with multiple users

  Background: 
    Given user is on login page

  Scenario: Login with data from Excel
    When I read login test data from "data.xlsx" and sheet "login"
    Then user should login with valid credentials
    