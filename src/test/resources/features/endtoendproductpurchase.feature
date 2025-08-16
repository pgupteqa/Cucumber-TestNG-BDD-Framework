Feature: End to End Product Purchase Functionality

  As a user of the Open cart website
  I want to be able to login with my account
  then I am able to search the required product
  and able to add the product to the cart
  and make the payment on the checkout page



  @FullProductPurchase @Smoke @Regression
  Scenario Outline: Successful End to End Product Purchase
    Given I am on the application login page
    When I login with email <email> and password <password>
    Then I should be logged in successfully and sees the username "Prateek Guptey"
    When I search for the product with name as <productname>
    Then I should see the product <productname> in the search results
    When I add the product to the shopping cart and proceed to the checkout
    Then I should be able make the payment
    And  I should see the order success message as "Your order on My Shop is complete."

    Examples:
      | email                  | password   | productname |
      | vovojoc317@dosonex.com | Password@1 | Printed Summer Dress |