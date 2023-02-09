package automationExerciseTests;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class AutomationExerciseTest extends BaseTest {

    @Test(priority = 10)
    @Description("TC 1: Register User")
    public void registerUser() {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifySignUpPageVisible();

        loginSignUpPage.getSignUpNameInput().sendKeys(name);
        loginSignUpPage.getSignUpEmailInput().sendKeys(email);
        loginSignUpPage.getSignUpButton().click();
        loginSignUpPage.verifyEnterAccountInfoVisible();

        loginSignUpPage.getGenderRadioButton().click();
        loginSignUpPage.getPasswordInput().sendKeys(password);
        loginSignUpPage.getDayOfBirthSelect().selectByValue("9");
        loginSignUpPage.getMonthOfBirthSelect().selectByValue("4");
        loginSignUpPage.getYearOfBirthSelect().selectByValue("1995");
        loginSignUpPage.getNewsLetterCheckbox().click();
        loginSignUpPage.getSpecialOffersCheckbox().click();
        loginSignUpPage.getFirstNameInput().sendKeys(firstName);
        loginSignUpPage.getLastNameInput().sendKeys(lastName);
        loginSignUpPage.getCompanyInput().sendKeys(company);
        loginSignUpPage.getAddressInput().sendKeys(street1);
        loginSignUpPage.getAddress2Input().sendKeys(street2);
        loginSignUpPage.getCountrySelect().selectByVisibleText(country);
        loginSignUpPage.getStateInput().sendKeys(state);
        loginSignUpPage.getCityInput().sendKeys(city);
        loginSignUpPage.getZipcodeInput().sendKeys(zipCode);
        loginSignUpPage.getMobileNumberInput().sendKeys(mobileNumber);
        loginSignUpPage.getCreateAccountButton().click();

        loginSignUpPage.verifyAccountCreatedVisible();
    }

    @Test(priority = 40)
    @Description("TC 2: Login User with correct email and password")
    public void loginUserWithCorrectEmailAndPassword() {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifyLoginPageVisible();

        loginSignUpPage.getLoginEmailInput().sendKeys(email);
        loginSignUpPage.getLoginPasswordInput().sendKeys(password);
        loginSignUpPage.getLoginButton().click();
        navPage.verifyLoggedInAsVisible();
        navPage.getDeleteAccountLink().click();

//        navPage.ifAdShownNavigateTo("/delete_account");

        loginSignUpPage.verifyAccountDeletedVisible();
    }

    @Test(priority = 50)
    @Description("TC 3: Login User with incorrect email and password")
    public void loginUserWithIncorrectEmailAndPassword() {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifyLoginPageVisible();

        loginSignUpPage.getLoginEmailInput().sendKeys(email);
        loginSignUpPage.getLoginPasswordInput().sendKeys(password);
        loginSignUpPage.getLoginButton().click();

        loginSignUpPage.verifyLoginEmailAndPasswordIncorrect();
    }

    @Test(priority = 30)
    @Description("TC 4: Logout User")
    public void logoutUser() {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifyLoginPageVisible();

        loginSignUpPage.getLoginEmailInput().sendKeys(email);
        loginSignUpPage.getLoginPasswordInput().sendKeys(password);
        loginSignUpPage.getLoginButton().click();
        navPage.verifyLoggedInAsVisible();
        navPage.getLogoutLink().click();
    }

    @Test(priority = 20)
    @Description("TC 5: Register User with existing email")
    public void registerUserWithExistingEmail() {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifySignUpPageVisible();

        loginSignUpPage.getSignUpNameInput().sendKeys(name);
        loginSignUpPage.getSignUpEmailInput().sendKeys(email);
        loginSignUpPage.getSignUpButton().click();

        loginSignUpPage.verifySignUpEmailAlreadyExists();
    }

    @Test(priority = 60)
    @Description("TC 6: Contact Us Form")
    public void contactUsForm() {
        navPage.getContactUsLink().click();
        contactUsPage.verifyGetInTouchIsVisible();

        contactUsPage.getNameInput().sendKeys(name);
        contactUsPage.getEmailInput().sendKeys(email);
        contactUsPage.getSubjectInput().sendKeys("Subject Test");
        contactUsPage.getMessageInput().sendKeys("Message Text Testing");
        contactUsPage.getUploadFileInput().sendKeys(new File("test_data/happybug.gif").getAbsolutePath());
        contactUsPage.getSubmitButton().click();

        contactUsPage.switchToAlert();

        Assert.assertTrue(contactUsPage.verifySuccess().getText()
                        .contains("Success! Your details have been submitted successfully."),
                "Message does not contain 'Success!''");

        navPage.getHomeLink().click();
        homePage.verifyHomePageVisible();
    }

    @Test(priority = 70)
    @Description("TC 7: Verify Test Cases Page")
    public void verifyTestCasesPage() {
        testCasesPage.verifyUserNavigatedToTestCasesPage();
    }

    @Test(priority = 80)
    @Description("TC 8: Verify All Products and product detail page")
    public void verifyAllProductsAndProductsDetailPage() {
        navPage.getProductsLink().click();
        productsPage.verifyAllProductsPageIsVisible();

        productsPage.verifyProductsListIsVisible();

//        navPage.ifAdShownNavigateTo("/products");

        productsPage.verifyFirstProductIsVisible();

        javascriptExecutor.executeScript("arguments[0].click()", productsPage.getFirstProductDetailLink());

//        navPage.ifAdShownNavigateTo("/product_details/1");

        productsPage.verifyUserLandedOnFirstProductDetailPage();

        productsPage.verifyDetailsOfFirstProductAreVisible();
    }

    @Test(priority = 90)
    @Description("TC 9: Search Product")
    public void searchProduct() {
        navPage.getProductsLink().click();
        productsPage.verifyAllProductsPageIsVisible();

//        navPage.ifAdShownNavigateTo("/products");
        productSearch = "men";
        productsPage.getSearchInput().sendKeys(productSearch);
        productsPage.getSearchButton().click();

        productsPage.verifyAllProductsPageIsVisible();
        for (int i = 0; i < productsPage.verifyAllProductsRelateToSearch().size(); i++) {
            Assert.assertTrue(productsPage.verifyAllProductsRelateToSearch().get(i).contains(productSearch),
                    "Product does not contain " + productSearch);
        }
    }

    @Test(priority = 100)
    @Description("Verify Subscription in home page")
    public void verifySubscriptionInHomePage() {
        navPage.scrollToFooter();
        Assert.assertTrue(navPage.verifySubscriptionIsDisplayed(), "SUBSCRIPTION is not displayed");

        navPage.getSubscriptionInput().sendKeys(email);
        navPage.getSubscriptionButton().click();

        Assert.assertTrue(navPage.verifySubscriptionSuccessIsDisplayed(), "Subscription is not successful");
    }

    @Test(priority = 110)
    @Description("TC 11: Verify Subscription in Cart page")
    public void verifySubscriptionInCartPage() {
        navPage.getCartLink().click();
        navPage.scrollToFooter();
        Assert.assertTrue(navPage.verifySubscriptionIsDisplayed(), "SUBSCRIPTION is not displayed");

        navPage.getSubscriptionInput().sendKeys(email);
        navPage.getSubscriptionButton().click();

        Assert.assertTrue(navPage.verifySubscriptionSuccessIsDisplayed(), "Subscription is not successful");
    }

    @Test(priority = 120)
    @Description("TC 12: Add Products in Cart")
    public void addProductsInCart() throws InterruptedException {
        navPage.getProductsLink().click();

//        navPage.ifAdShownNavigateTo("/products");

        productsPage.verifyOverlayVisibleAndMoveTo(1);
        productsPage.getAddToCartButton(1).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(2);
        productsPage.getAddToCartButton(2).click();
        productsPage.getViewCartDialogButton().click();

        cartPage.setProductsInCartPricesQuantityTotalPriceReturnsTotalQuantity();
        Assert.assertEquals(cartPage.getProduct1Price(), 500, "Price of first product is not 500");
        Assert.assertEquals(cartPage.getProduct2Price(), 400, "Price of second product is not 400");
        Assert.assertEquals(cartPage.getProductsTotalPrice(), 900, "Total price products is not 900");
        Assert.assertEquals(cartPage.setProductsInCartPricesQuantityTotalPriceReturnsTotalQuantity(), 2,
                "Number of products in cart is not 2");
    }

    @Test(priority = 130)
    @Description("TC 13: Verify Product quantity in Cart")
    public void verifyProductQuantityInCart() {
        homePage.scrollToProduct(4);
        javascriptExecutor.executeScript("arguments[0].click()", homePage.getViewProductButton(2));

//        navPage.ifAdShownNavigateTo("/product_details/2");

        productsPage.getQuantityInput().sendKeys("4");
        productsPage.getAddToCartButton().click();
        productsPage.getViewCartDialogButton().click();

        cartPage.verifyActualQuantityExact(4);
        Assert.assertTrue(cartPage.isVerifyCartProductQuantity(), "Products quantity is not 4");
    }

    @Test(priority = 140)
    @Description("TC 14: Place Order: Register while Checkout")
    public void placeOrderRegisterWhileCheckout() throws InterruptedException {
        homePage.scrollToProduct(4);
        productsPage.verifyOverlayVisibleAndMoveTo(1);
        homePage.getAddToCartButton(1).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(2);
        homePage.getAddToCartButton(2).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(3);
        homePage.getAddToCartButton(3).click();
        productsPage.getContinueShoppingDialogButton().click();

        homePage.scrollToHeader();

        navPage.getCartLink().click();
        cartPage.verifyCartPageDisplayed();
        cartPage.getProceedToCheckoutButton().click();
        cartPage.verifyCheckoutDialogIsShown();
        cartPage.getRegisterLoginDialogButton().click();

        loginSignUpPage.getSignUpNameInput().sendKeys(name);
        loginSignUpPage.getSignUpEmailInput().sendKeys(email);
        loginSignUpPage.getSignUpButton().click();
        loginSignUpPage.verifyEnterAccountInfoVisible();

        loginSignUpPage.getGenderRadioButton().click();
        loginSignUpPage.getPasswordInput().sendKeys(password);
        loginSignUpPage.getDayOfBirthSelect().selectByValue("9");
        loginSignUpPage.getMonthOfBirthSelect().selectByValue("4");
        loginSignUpPage.getYearOfBirthSelect().selectByValue("1995");
        loginSignUpPage.getNewsLetterCheckbox().click();
        loginSignUpPage.getSpecialOffersCheckbox().click();
        loginSignUpPage.getFirstNameInput().sendKeys(firstName);
        loginSignUpPage.getLastNameInput().sendKeys(lastName);
        loginSignUpPage.getCompanyInput().sendKeys(company);
        loginSignUpPage.getAddressInput().sendKeys(street1);
        loginSignUpPage.getAddress2Input().sendKeys(street2);
        loginSignUpPage.getCountrySelect().selectByVisibleText(country);
        loginSignUpPage.getStateInput().sendKeys(state);
        loginSignUpPage.getCityInput().sendKeys(city);
        loginSignUpPage.getZipcodeInput().sendKeys(zipCode);
        loginSignUpPage.getMobileNumberInput().sendKeys(mobileNumber);
        loginSignUpPage.getCreateAccountButton().click();

        loginSignUpPage.verifyAccountCreatedVisible();
        loginSignUpPage.getAccountCreatedContinueButton().click();

//        navPage.ifAdShownNavigateTo("");

        navPage.verifyLoggedInAsVisible();
        navPage.getCartLink().click();
        cartPage.getProceedToCheckoutButton().click();

        cartPage.listOfAddressDetails(listOfDetals);
        Assert.assertTrue(cartPage.verifyAddressDetails(), "List of details is not equal");
        Assert.assertTrue(cartPage.verifyReviewYourOrder(), "Order list does not contain selected products");

        cartPage.getDescriptionInput().sendKeys("Testing description input");
        navPage.scrollToFooter();
        cartPage.getPlaceOrderButton().click();

        cartPage.getNameOnCardInput().sendKeys(firstName + " " + lastName);
        cartPage.getCardNumberInput().sendKeys("4536-2342-6564-3422");
        cartPage.getCVCInput().sendKeys("323");
        cartPage.getExpirationMonthInput().sendKeys("05");
        cartPage.getExpirationYearInput().sendKeys("24");
        cartPage.getPayAndComfirmOrderButton().click();

        cartPage.getYourOrderPlacedSuccessfullyMessage();

        navPage.getDeleteAccountLink().click();
        loginSignUpPage.verifyAccountDeletedVisible();
    }

    @Test(priority = 150)
    @Description("TC 15: Place Order: Register before Checkout")
    public void placeOrderRegisterBeforeCheckout() throws InterruptedException {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifySignUpPageVisible();

        loginSignUpPage.getSignUpNameInput().sendKeys(name);
        loginSignUpPage.getSignUpEmailInput().sendKeys(email);
        loginSignUpPage.getSignUpButton().click();
        loginSignUpPage.verifyEnterAccountInfoVisible();

        loginSignUpPage.getGenderRadioButton().click();
        loginSignUpPage.getPasswordInput().sendKeys(password);
        loginSignUpPage.getDayOfBirthSelect().selectByValue("9");
        loginSignUpPage.getMonthOfBirthSelect().selectByValue("4");
        loginSignUpPage.getYearOfBirthSelect().selectByValue("1995");
        loginSignUpPage.getNewsLetterCheckbox().click();
        loginSignUpPage.getSpecialOffersCheckbox().click();
        loginSignUpPage.getFirstNameInput().sendKeys(firstName);
        loginSignUpPage.getLastNameInput().sendKeys(lastName);
        loginSignUpPage.getCompanyInput().sendKeys(company);
        loginSignUpPage.getAddressInput().sendKeys(street1);
        loginSignUpPage.getAddress2Input().sendKeys(street2);
        loginSignUpPage.getCountrySelect().selectByVisibleText(country);
        loginSignUpPage.getStateInput().sendKeys(state);
        loginSignUpPage.getCityInput().sendKeys(city);
        loginSignUpPage.getZipcodeInput().sendKeys(zipCode);
        loginSignUpPage.getMobileNumberInput().sendKeys(mobileNumber);
        loginSignUpPage.getCreateAccountButton().click();

        loginSignUpPage.verifyAccountCreatedVisible();
        loginSignUpPage.getAccountCreatedContinueButton().click();

//        navPage.ifAdShownNavigateTo("");

        navPage.verifyLoggedInAsVisible();

        homePage.scrollToProduct(4);
        productsPage.verifyOverlayVisibleAndMoveTo(1);
        homePage.getAddToCartButton(1).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(2);
        homePage.getAddToCartButton(2).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(3);
        homePage.getAddToCartButton(3).click();
        productsPage.getContinueShoppingDialogButton().click();

        homePage.scrollToHeader();

        navPage.getCartLink().click();
        cartPage.verifyCartPageDisplayed();
        cartPage.getProceedToCheckoutButton().click();

        cartPage.listOfAddressDetails(listOfDetals);
        Assert.assertTrue(cartPage.verifyAddressDetails(), "List of details is not equal");
        Assert.assertTrue(cartPage.verifyReviewYourOrder(), "Order list does not contain selected products");

        cartPage.getDescriptionInput().sendKeys("Testing description input");
        navPage.scrollToFooter();
        cartPage.getPlaceOrderButton().click();

        cartPage.getNameOnCardInput().sendKeys(firstName + " " + lastName);
        cartPage.getCardNumberInput().sendKeys("4536-2342-6564-3422");
        cartPage.getCVCInput().sendKeys("323");
        cartPage.getExpirationMonthInput().sendKeys("05");
        cartPage.getExpirationYearInput().sendKeys("24");
        cartPage.getPayAndComfirmOrderButton().click();

        cartPage.getYourOrderPlacedSuccessfullyMessage();

        navPage.getDeleteAccountLink().click();
        loginSignUpPage.verifyAccountDeletedVisible();
    }

    @Test(priority = 160)
    @Description("TC 16.1: Register user for following test - Login before Checkout")
    public void registerUserForFollowingTestLoginBeforeCheckout() {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifySignUpPageVisible();

        loginSignUpPage.getSignUpNameInput().sendKeys(name);
        loginSignUpPage.getSignUpEmailInput().sendKeys(email);
        loginSignUpPage.getSignUpButton().click();
        loginSignUpPage.verifyEnterAccountInfoVisible();

        loginSignUpPage.getGenderRadioButton().click();
        loginSignUpPage.getPasswordInput().sendKeys(password);
        loginSignUpPage.getDayOfBirthSelect().selectByValue("9");
        loginSignUpPage.getMonthOfBirthSelect().selectByValue("4");
        loginSignUpPage.getYearOfBirthSelect().selectByValue("1995");
        loginSignUpPage.getNewsLetterCheckbox().click();
        loginSignUpPage.getSpecialOffersCheckbox().click();
        loginSignUpPage.getFirstNameInput().sendKeys(firstName);
        loginSignUpPage.getLastNameInput().sendKeys(lastName);
        loginSignUpPage.getCompanyInput().sendKeys(company);
        loginSignUpPage.getAddressInput().sendKeys(street1);
        loginSignUpPage.getAddress2Input().sendKeys(street2);
        loginSignUpPage.getCountrySelect().selectByVisibleText(country);
        loginSignUpPage.getStateInput().sendKeys(state);
        loginSignUpPage.getCityInput().sendKeys(city);
        loginSignUpPage.getZipcodeInput().sendKeys(zipCode);
        loginSignUpPage.getMobileNumberInput().sendKeys(mobileNumber);
        loginSignUpPage.getCreateAccountButton().click();

        loginSignUpPage.verifyAccountCreatedVisible();
    }

    @Test(priority = 161)
    @Description("TC 16.2: Place Order: Login before Checkout")
    public void placeOrderLoginBeforeCheckout() throws InterruptedException {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifyLoginPageVisible();

        loginSignUpPage.getLoginEmailInput().sendKeys(email);
        loginSignUpPage.getLoginPasswordInput().sendKeys(password);
        loginSignUpPage.getLoginButton().click();
        navPage.verifyLoggedInAsVisible();

        homePage.scrollToProduct(4);
        productsPage.verifyOverlayVisibleAndMoveTo(1);
        homePage.getAddToCartButton(1).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(2);
        homePage.getAddToCartButton(2).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(3);
        homePage.getAddToCartButton(3).click();
        productsPage.getContinueShoppingDialogButton().click();

        homePage.scrollToHeader();

        navPage.getCartLink().click();
        cartPage.verifyCartPageDisplayed();
        cartPage.getProceedToCheckoutButton().click();

        cartPage.listOfAddressDetails(listOfDetals);
        Assert.assertTrue(cartPage.verifyAddressDetails(), "List of details is not equal");
        Assert.assertTrue(cartPage.verifyReviewYourOrder(), "Order list does not contain selected products");

        cartPage.getDescriptionInput().sendKeys("Testing description input");
        navPage.scrollToFooter();
        cartPage.getPlaceOrderButton().click();

//        navPage.ifAdShownNavigateTo("/payment");

        cartPage.getNameOnCardInput().sendKeys(firstName + " " + lastName);
        cartPage.getCardNumberInput().sendKeys("4536-2342-6564-3422");
        cartPage.getCVCInput().sendKeys("323");
        cartPage.getExpirationMonthInput().sendKeys("05");
        cartPage.getExpirationYearInput().sendKeys("24");
        cartPage.getPayAndComfirmOrderButton().click();

        cartPage.getYourOrderPlacedSuccessfullyMessage();

        navPage.getDeleteAccountLink().click();
        loginSignUpPage.verifyAccountDeletedVisible();
    }

    @Test(priority = 170)
    @Description("TC 17: Remove Products From Cart")
    public void removeProductsFromCart() throws InterruptedException {
        homePage.scrollToProduct(4);

        productsPage.verifyOverlayVisibleAndMoveTo(1);
        homePage.getAddToCartButton(1).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(2);
        homePage.getAddToCartButton(2).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(3);
        homePage.getAddToCartButton(3).click();
        productsPage.getContinueShoppingDialogButton().click();

        homePage.scrollToHeader();

        navPage.getCartLink().click();
        cartPage.verifyCartPageDisplayed();

        cartPage.getDeleteProductFromCartButton(1).click();
        cartPage.getDeleteProductFromCartButton(2).click();
        cartPage.getDeleteProductFromCartButton(3).click();

        cartPage.verifyTableBodyNumberOfRowsZero();
    }

    @Test(priority = 180)
    @Description("TC 18: View Category Products")
    public void viewCategoryProducts() {
        homePage.verifyLeftSideBarVisible();
        homePage.getWomenCategoryLink().click();
        homePage.getDressCategoryUnderWomanCategoryLink().click();

//        navPage.ifAdShownNavigateTo("/category_products/1");

        productsPage.verifyAllProductsPageIsVisible();

        homePage.getMenCategoryLink().click();
        homePage.getTshirtsCategoryUnderManCategoryLink().click();

//        navPage.ifAdShownNavigateTo("/category_products/3");

        productsPage.verifyAllProductsPageIsVisible();
    }

    @Test(priority = 190)
    @Description("TC 19: View & Cart Brand Products")
    public void viewCartBrandProducts() {
        navPage.getProductsLink().click();

        homePage.verifyBrandsSideBarIsVisible();
//        navPage.ifAdShownNavigateTo("/brand_products/Polo");
        homePage.getPoloBrandLink("Polo").click();

        productsPage.verifyAllProductsPageIsVisible();
        productsPage.verifyProductsListIsVisible();

        homePage.getPoloBrandLink("H&M").click();

        productsPage.verifyAllProductsPageIsVisible();
        productsPage.verifyProductsListIsVisible();
    }

    @Test(priority = 200)
    @Description("TC 20.1: Register user for following test - Search Products and Verify Cart After Login")
    public void registerUserForFollowingTestVerifyCartAfterLogin() {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifySignUpPageVisible();

        loginSignUpPage.getSignUpNameInput().sendKeys(name);
        loginSignUpPage.getSignUpEmailInput().sendKeys(email);
        loginSignUpPage.getSignUpButton().click();
        loginSignUpPage.verifyEnterAccountInfoVisible();

        loginSignUpPage.getGenderRadioButton().click();
        loginSignUpPage.getPasswordInput().sendKeys(password);
        loginSignUpPage.getDayOfBirthSelect().selectByValue("9");
        loginSignUpPage.getMonthOfBirthSelect().selectByValue("4");
        loginSignUpPage.getYearOfBirthSelect().selectByValue("1995");
        loginSignUpPage.getNewsLetterCheckbox().click();
        loginSignUpPage.getSpecialOffersCheckbox().click();
        loginSignUpPage.getFirstNameInput().sendKeys(firstName);
        loginSignUpPage.getLastNameInput().sendKeys(lastName);
        loginSignUpPage.getCompanyInput().sendKeys(company);
        loginSignUpPage.getAddressInput().sendKeys(street1);
        loginSignUpPage.getAddress2Input().sendKeys(street2);
        loginSignUpPage.getCountrySelect().selectByVisibleText(country);
        loginSignUpPage.getStateInput().sendKeys(state);
        loginSignUpPage.getCityInput().sendKeys(city);
        loginSignUpPage.getZipcodeInput().sendKeys(zipCode);
        loginSignUpPage.getMobileNumberInput().sendKeys(mobileNumber);
        loginSignUpPage.getCreateAccountButton().click();

        loginSignUpPage.verifyAccountCreatedVisible();
    }

    @Test(priority = 201)
    @Description("TC 20.2: Search Products and Verify Cart After Login")
    public void searchProductsAndVerifyCartAfterLogin() throws InterruptedException {
        navPage.getProductsLink().click();
        productsPage.verifyAllProductsPageIsVisible();
//        navPage.ifAdShownNavigateTo("/products");
        productSearch = "men";
        productsPage.getSearchInput().sendKeys(productSearch);
        productsPage.getSearchButton().click();

        productsPage.verifyAllProductsPageIsVisible();

        for (int i = 0; i < productsPage.verifyAllProductsRelateToSearch().size(); i++) {
            Assert.assertTrue(productsPage.verifyAllProductsRelateToSearch().get(i)
                    .contains(productSearch), "Product does not contain " + productSearch);
        }

        productsPage.getAllProductsIDFromPageToCart();

        for (int i = 0; i < productsPage.getIdNumbersOfProducts().size(); i++) {
            homePage.scrollToProduct(productsPage.getIdNumbersOfProducts().get(i));
            productsPage.verifyOverlayVisibleAndMoveTo(productsPage.getIdNumbersOfProducts().get(i));
            homePage.getAddToCartButton(productsPage.getIdNumbersOfProducts().get(i)).click();
            productsPage.getContinueShoppingDialogButton().click();
        }

        navPage.getCartLink().click();
        cartPage.verifyCartPageDisplayed();

        cartPage.setCartProductElements();
        for (int i = 0; i < cartPage.getCartProductElements().size(); i++) {
            cartPage.reviewCartProducts(productsPage.getIdNumbersOfProducts().get(i));
        }

        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifyLoginPageVisible();

        loginSignUpPage.getLoginEmailInput().sendKeys(email);
        loginSignUpPage.getLoginPasswordInput().sendKeys(password);
        loginSignUpPage.getLoginButton().click();
        navPage.verifyLoggedInAsVisible();

        navPage.getCartLink().click();
        cartPage.verifyCartPageDisplayed();

        cartPage.setCartProductElements();
        for (int i = 0; i < cartPage.getCartProductElements().size(); i++) {
            cartPage.reviewCartProducts(productsPage.getIdNumbersOfProducts().get(i));
        }
    }

    @Test(priority = 210)
    @Description("TC 21: Add review on product")
    public void addReviewOnProduct() {
        navPage.getProductsLink().click();
        productsPage.verifyAllProductsPageIsVisible();

//        navPage.ifAdShownNavigateTo("/products");

        productsPage.verifyFirstProductIsVisible();

        javascriptExecutor.executeScript("arguments[0].click()", productsPage.getFirstProductDetailLink());

//        navPage.ifAdShownNavigateTo("/product_details/1");

        navPage.scrollToFooter();
        productsPage.verifyWriteYourReviewIsVisible();
        productsPage.getWriteReviewNameInput().sendKeys(firstName + " " + lastName);
        productsPage.getWriteReviewEmailInput().sendKeys(email);
        productsPage.getWriteReviewTextInput().sendKeys("Test Review input field");
        productsPage.getWriteReviewButton().click();
        productsPage.verifyWriteReviewSuccess();
    }

    @Test(priority = 220)
    @Description("TC 22: Add to cart from Recommended items")
    public void addToCartFromRecommendedItems() throws InterruptedException {


        homePage.verifyRecommendedItemsVisible();
        javascriptExecutor.executeScript("arguments[0].click()", homePage.getAddToCartIdInSliderVisible());
        productsPage.getViewCartDialogButton().click();

        cartPage.setCartProductElements();
        cartPage.reviewCartProducts(1);
    }

    @Test(priority = 230)
    @Description("TC 23: Verify address details in checkout page")
    public void verifyAddressDetailsInCheckoutPage() throws InterruptedException {
        navPage.getSignUpLoginLink().click();
        loginSignUpPage.verifySignUpPageVisible();

        loginSignUpPage.getSignUpNameInput().sendKeys(name);
        loginSignUpPage.getSignUpEmailInput().sendKeys("dz" + email);
        loginSignUpPage.getSignUpButton().click();
        loginSignUpPage.verifyEnterAccountInfoVisible();

        loginSignUpPage.getGenderRadioButton().click();
        loginSignUpPage.getPasswordInput().sendKeys(password);
        loginSignUpPage.getDayOfBirthSelect().selectByValue("9");
        loginSignUpPage.getMonthOfBirthSelect().selectByValue("4");
        loginSignUpPage.getYearOfBirthSelect().selectByValue("1995");
        loginSignUpPage.getNewsLetterCheckbox().click();
        loginSignUpPage.getSpecialOffersCheckbox().click();
        loginSignUpPage.getFirstNameInput().sendKeys(firstName);
        loginSignUpPage.getLastNameInput().sendKeys(lastName);
        loginSignUpPage.getCompanyInput().sendKeys(company);
        loginSignUpPage.getAddressInput().sendKeys(street1);
        loginSignUpPage.getAddress2Input().sendKeys(street2);
        loginSignUpPage.getCountrySelect().selectByVisibleText(country);
        loginSignUpPage.getStateInput().sendKeys(state);
        loginSignUpPage.getCityInput().sendKeys(city);
        loginSignUpPage.getZipcodeInput().sendKeys(zipCode);
        loginSignUpPage.getMobileNumberInput().sendKeys(mobileNumber);
        loginSignUpPage.getCreateAccountButton().click();

        loginSignUpPage.verifyAccountCreatedVisible();
        loginSignUpPage.getAccountCreatedContinueButton().click();

//        navPage.ifAdShownNavigateTo("");

        navPage.verifyLoggedInAsVisible();

        homePage.scrollToProduct(4);
        productsPage.verifyOverlayVisibleAndMoveTo(1);
        homePage.getAddToCartButton(1).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(2);
        homePage.getAddToCartButton(2).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(3);
        homePage.getAddToCartButton(3).click();
        productsPage.getContinueShoppingDialogButton().click();

        homePage.scrollToHeader();

        navPage.getCartLink().click();
        cartPage.verifyCartPageDisplayed();
        cartPage.getProceedToCheckoutButton().click();

        cartPage.listOfAddressDetails(listOfDetals);
        Assert.assertTrue(cartPage.verifyAddressDetails(), "List of details is not equal");

        navPage.getDeleteAccountLink().click();
        loginSignUpPage.verifyAccountDeletedVisible();
        loginSignUpPage.getAccountCreatedContinueButton().click();
    }

    @Test(priority = 240)
    @Description("TC 24: Download Invoice after purchase order")
    public void downloadInvoiceAfterPurchaseOrder() throws InterruptedException {
        homePage.scrollToProduct(4);
        productsPage.verifyOverlayVisibleAndMoveTo(1);
        homePage.getAddToCartButton(1).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(2);
        homePage.getAddToCartButton(2).click();
        productsPage.getContinueShoppingDialogButton().click();

        productsPage.verifyOverlayVisibleAndMoveTo(3);
        homePage.getAddToCartButton(3).click();
        productsPage.getContinueShoppingDialogButton().click();

        homePage.scrollToHeader();

        navPage.getCartLink().click();
        cartPage.verifyCartPageDisplayed();
        cartPage.getProceedToCheckoutButton().click();
        cartPage.verifyCheckoutDialogIsShown();
        cartPage.getRegisterLoginDialogButton().click();

        loginSignUpPage.getSignUpNameInput().sendKeys(name);
        loginSignUpPage.getSignUpEmailInput().sendKeys("n"+email);
        loginSignUpPage.getSignUpButton().click();
        loginSignUpPage.verifyEnterAccountInfoVisible();

        loginSignUpPage.getGenderRadioButton().click();
        loginSignUpPage.getPasswordInput().sendKeys(password);
        loginSignUpPage.getDayOfBirthSelect().selectByValue("9");
        loginSignUpPage.getMonthOfBirthSelect().selectByValue("4");
        loginSignUpPage.getYearOfBirthSelect().selectByValue("1995");
        loginSignUpPage.getNewsLetterCheckbox().click();
        loginSignUpPage.getSpecialOffersCheckbox().click();
        loginSignUpPage.getFirstNameInput().sendKeys(firstName);
        loginSignUpPage.getLastNameInput().sendKeys(lastName);
        loginSignUpPage.getCompanyInput().sendKeys(company);
        loginSignUpPage.getAddressInput().sendKeys(street1);
        loginSignUpPage.getAddress2Input().sendKeys(street2);
        loginSignUpPage.getCountrySelect().selectByVisibleText(country);
        loginSignUpPage.getStateInput().sendKeys(state);
        loginSignUpPage.getCityInput().sendKeys(city);
        loginSignUpPage.getZipcodeInput().sendKeys(zipCode);
        loginSignUpPage.getMobileNumberInput().sendKeys(mobileNumber);
        loginSignUpPage.getCreateAccountButton().click();

        loginSignUpPage.verifyAccountCreatedVisible();
        loginSignUpPage.getAccountCreatedContinueButton().click();

//        navPage.ifAdShownNavigateTo("");

        navPage.verifyLoggedInAsVisible();
        navPage.getCartLink().click();
        cartPage.getProceedToCheckoutButton().click();

        cartPage.listOfAddressDetails(listOfDetals);
        Assert.assertTrue(cartPage.verifyAddressDetails(), "List of details is not equal");
        Assert.assertTrue(cartPage.verifyReviewYourOrder(), "Order list does not contain selected products");

        cartPage.getDescriptionInput().sendKeys("Testing description input");
        navPage.scrollToFooter();
        cartPage.getPlaceOrderButton().click();

        cartPage.getNameOnCardInput().sendKeys(firstName + " " + lastName);
        cartPage.getCardNumberInput().sendKeys("4536-2342-6564-3422");
        cartPage.getCVCInput().sendKeys("323");
        cartPage.getExpirationMonthInput().sendKeys("05");
        cartPage.getExpirationYearInput().sendKeys("24");
        cartPage.getPayAndComfirmOrderButton().click();

        cartPage.getYourOrderPlacedSuccessfullyMessage();

        cartPage.getDownloadInvoiceButton().click();

        navPage.getDeleteAccountLink().click();
        loginSignUpPage.verifyAccountDeletedVisible();
    }

    @Test(priority = 250)
    @Description("TC 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    public void verifyScrollUpUsingArrowButtonAndScrollDownFunctionality() {
        navPage.scrollToFooter();
        navPage.verifySubscriptionIsDisplayed();
        javascriptExecutor.executeScript("arguments[0].click()", homePage.getScrollUpArrow());
        homePage.verifyHomePageVisible();
    }

    @Test(priority = 260)
    @Description("TC 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality")
    public void verifyScrollUpWithoutArrowButtonAndScrollDownFunctionality() {
        navPage.scrollToFooter();
        navPage.verifySubscriptionIsDisplayed();
        homePage.scrollToHeader();
        homePage.verifyHomePageVisible();
    }
}
