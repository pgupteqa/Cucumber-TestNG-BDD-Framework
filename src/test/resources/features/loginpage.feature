Feature: Login Functionality for OpenCart Website

  As a user of the Open cart website
  I want to be able to login with my account
  so that I can access my account-related features and manage my orders


  @ValidLogin @Smoke @Regression
  Scenario Outline: Successful Login with Valid Credentials
    Given I am on the application login page
    When I login with email <email> and password <password>
    Then I should be logged in successfully and sees the username "Prateek Guptey"

    Examples:
      | email                  | password   |
      | vovojoc317@dosonex.com | Password@1 |


  @InvalidLogin @Smoke @Regression
  Scenario Outline: Validate the Login using Invalid Credentials
    Given I am on the application login page
    When I login with Invalid email <email> and password <password>
    Then I should see the Login Error Message "Authentication failed."

    Examples:
      | email                  | password   |
      | vov5joc355@dosonex.com | Password@12 |
      | testmsg@dosonex.com | Password@12 |