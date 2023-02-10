# Automation testing of "Automation Exercise" website

I would like to express my gratitude to IT Bootcamp course and especially to the instructors Milan Jovanovic and Vladimir Minic, who provided me and my colleagues with the opportunity to learn and grow as automation testers. They have taught us a great deal and were always there to help whenever we needed it.

This is an exercise automation test using Selenium and TestNG for one of the final projects on IT Bootcamp QA course. The project was built using Maven and Java 19.

The test suite is based on the instructions from the "Automation Exercise" website (URL: https://automationexercise.com/), which contains test cases that were used to guide the testing process. 

Since test cases require the Chrome browser to start over in each test case for proper operation, the Chrome driver is set up in the Before method of the test.

Due to the high number of advertisements on the website, the code was implemented with an AdBlocker ("AdBlock Max"), but there is also a method which can be used in specific places where advertisements affect the testing process.

The following test cases were executed:

Test Case 1: Register User

Test Case 2: Login User with correct email and password

Test Case 3: Login User with incorrect email and password

Test Case 4: Logout User

Test Case 5: Register User with existing email

Test Case 6: Contact Us Form

Test Case 7: Verify Test Cases Page

Test Case 8: Verify All Products and product detail page

Test Case 9: Search Product

Test Case 10: Verify Subscription in home page

Test Case 11: Verify Subscription in Cart page

Test Case 12: Add Products in Cart

Test Case 13: Verify Product quantity in Cart

Test Case 14: Place Order: Register while Checkout

Test Case 15: Place Order: Register before Checkout

Test Case 16: Place Order: Login before Checkout

Test Case 17: Remove Products From Cart

Test Case 18: View Category Products

Test Case 19: View & Cart Brand Products

Test Case 20: Search Products and Verify Cart After Login

Test Case 21: Add review on product

Test Case 22: Add to cart from Recommended items

Test Case 23: Verify address details in checkout page

Test Case 24: Download Invoice after purchase order

Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality

Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality
