# SpriteCloud Assignment

This project contains two folders. The UI Testing folder contains the automated test cases for https://www.saucedemo.com using Selenium and Java. TestNG was used to perform automated tests. The API Testing folder contains a postman collection which has test cases to test various APIs of https://reqres.in/

## UI Testing Solution

The automated UI tests cover the following scenarios:
 1. Full checkout process that contains at least two items, validating the final price.
 2. Sorting items by name (Z-A) and validating the sorting order.
 3. Failed login validation. (And successful login validation as well)

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


2. TC002_VerifySortedProducts:
   
   - Steps
       1. Login with valid credentials.
       2. Check if login is successful and inventory page is visible.
       3. Click on Sort products by Name Z-A.
       4. Validate that the sorting is correct.


 3. TC003_VerifyLoginFunctionality:

    - The test case contains following scenarios
         1. Valid Login - Validate that the user is redirected to the correct page.
         2. Invalid Login - Validate that the correct error message is displayed.
         3. Invalid Username - Validate that the correct error message is displayed.
         4. Invalid Password - Validate that the correct error message is displayed.
         5. Blank Username - Validate that the correct error message is displayed.
         6. Blank Password - Validate that the correct error message is displayed.
     
    - The test case uses a data provider (DataProviders.class) and an excel utility (ExcelUtility.Class) to fetch test data from an excel sheet. (Referred Youtube videos for excel utility)
   
### Test Reports

An automatic test report is generated when test excecution is completed. ExtentReports is used to generate test reports. Test reports are available in "reports" folder. And screenshots of failed test cases are stored in "screenshots" folder. (Referred Youtube videos for this)


## API Testing Solution

### Test Cases Implemented

1. Retrieve a List of Users:

   - Endpoint: /api/users?page=2
   - Method: GET
   - Description: This test case retrieves a list of users from the second page.
   - Validation:
     1. Verify that the response status code is 200.
     2. Verify that the response time is less than 200ms
     3. Verify that the user list is not empty.
     4. Validate that the per_page and total fields in the response match the expected values.
     5. Validate that the response matches the simplified JSON schema defined in Post-response Scripts.

2. Perform a successful login:

   - Endpoint: /api/login
   - Method: POST
   - Description: This test case performs a successful login using valid credentials.
   - Validation:
     1. Verify that the response status code is 200.
     2. Verify that the token is present.

3. Perform an update:

   - Endpoint: /api/users/{{user_id}}
   - Method: PUT
   - Description: This test case updates the details of a user present in environment variable user_id.
   - Validation:
     1. Verify that the response status code is 200.
     2. Verify that the response contains the updated name and job fields.

4. Perform a deletion:

   - Endpoint: /api/users/{{user_id}}
   - Method: DELETE
   - Description: This test case deletes a user present in environment variable user_id.
   - Validation:
     1. Verify that the response status code is 204.

5. A. Execute 2 negative scenarios - Invalid Login:

   - Endpoint: /api/login
   - Method: POST
   - Description: This test case attempts to log in with invalid credentials.
   - Validation:
     1. Verify that the response status code is 400.

5. B. Execute 2 negative scenarios - Invalid User:

   - Endpoint: /api/users/100
   - Method: GET
   - Description: This test case attempts to get invalid user.
   - Validation:
     1. Verify that the response status code is 404.

6. Execute a parameterized delayed request (max 3 seconds) and evaluate how long the request takes:

   - Endpoint: /api/users?delay={{delay_time}}
   - Method: GET
   - Description: This test case retrieves a list of users with a delay of up to 3 seconds.
   - Validation:
     1. Validate response time is less than 3 seconds.
     2. Verify that the response status code is 200.
     3. Validate data exists in response body.
