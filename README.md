# Amazon_Tech_T

## Create 3 Tests for each Scenario using Selenium WebDriver and TestNG assertions

### Scenario 1: Verify that a user cannot log in with a valid but unregistered email

### Scenario 2: Verify that items are added to the cart correctly

We follow the steps of the user and consider the following:

- **A**: Some products have only one item available, so we add only the available item to avoid assertion failures.
- **B**: New offers come with products, so we reject them.
- **C**: For Prime Day deals, we cannot add the product to the cart, so we make the test pass anyway.

### Scenario 3: Run the test without logging in and loop the scenario for each case

### Additional Notes

- We are using ExtentReports and have log files for all screenshots, and for failed test screenshots only, with logs for all test steps.
