# SpriteCloud Assignment

This project contains two folders. The UI Testing folder contains the automated test cases for https://www.saucedemo.com using Selenium and Java. The API Testing folder contains a postman collection which has test cases to test various APIs of https://reqres.in/

## UI Testing Solution

The automated UI tests cover the following scenarios:
 1. Full checkout process that contains at least two items, validating the final price.
 2. Sorting items by name (Z-A) and validating the sorting order.
 3. Failed login validation.

### Test Cases Implemented

1. TC001_VerifyCheckoutFlow:

   - Steps
       1. Login with valid credentials.
       2. Check if login is successful, inventory page is visible, add 2 products to cart, and click on cart.
       3. Click on checkout.
       4. Enter information on "Checkout-Your Information Page" and click on continue.
       5. Validate that the final price is same as the expected final price. Click on Finish.
       6. Check if "Checkout-Complete Page" is visible.

 
   - Assumptions
       1. In the website, a tax was added to the total price of products. According to my calculations it is 8%. So, a tax of 8% is added while validating the final price.
