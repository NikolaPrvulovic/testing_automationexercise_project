package automationExercisePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSignUpPage extends BasePage {
    public LoginSignUpPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void verifySignUpPageVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='signup-form']/h2"))));
    }

    public WebElement getSignUpNameInput() {
        return driver.findElement(
                By.xpath("//input[@type='text']"));
    }

    public WebElement getSignUpEmailInput() {
        return driver.findElement(
                By.xpath("//input[@data-qa='signup-email']"));
    }

    public WebElement getSignUpButton() {
        return driver.findElement(
                By.xpath("//button[@data-qa='signup-button']"));
    }

    public void verifyEnterAccountInfoVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='login-form']/h2"))));
    }

    public WebElement getGenderRadioButton() {
        return driver.findElement(
                By.xpath("//input[@value='Mr']"));
    }

    public WebElement getNameInput() {
        return driver.findElement(
                By.xpath("//input[@id='name']"));
    }

    public WebElement getEmailInput() {
        return driver.findElement(
                By.xpath("//input[@id='email']"));
    }

    public WebElement getPasswordInput() {
        return driver.findElement(
                By.xpath("//input[@id='password']"));
    }

    public WebElement getFirstNameInput() {
        return driver.findElement(
                By.xpath("//input[@id='first_name']"));
    }

    public WebElement getLastNameInput() {
        return driver.findElement(
                By.xpath("//input[@id='last_name']"));
    }

    public WebElement getCompanyInput() {
        return driver.findElement(
                By.xpath("//input[@id='company']"));
    }

    public WebElement getAddressInput() {
        return driver.findElement(
                By.xpath("//input[@id='address1']"));
    }

    public WebElement getAddress2Input() {
        return driver.findElement(
                By.xpath("//input[@id='address2']"));
    }

    public Select getDayOfBirthSelect() {
        return new Select(driver.findElement(
                By.xpath("//select[@id='days']")));
    }

    public Select getMonthOfBirthSelect() {
        return new Select(driver.findElement(
                By.xpath("//select[@id='months']")));
    }

    public Select getYearOfBirthSelect() {
        return new Select(driver.findElement(
                By.xpath("//select[@id='years']")));
    }

    public WebElement getNewsLetterCheckbox() {
        return driver.findElement(
                By.xpath("//input[@id='newsletter']"));
    }

    public WebElement getSpecialOffersCheckbox() {
        return driver.findElement(
                By.xpath("//input[@id='optin']"));
    }

    public Select getCountrySelect() {
        return new Select(driver.findElement(
                By.xpath("//select[@id='country']")));
    }

    public WebElement getStateInput() {
        return driver.findElement(
                By.xpath("//input[@id='state']"));
    }

    public WebElement getCityInput() {
        return driver.findElement(
                By.xpath("//input[@id='city']"));
    }

    public WebElement getZipcodeInput() {
        return driver.findElement(
                By.xpath("//input[@id='zipcode']"));
    }

    public WebElement getMobileNumberInput() {
        return driver.findElement(
                By.xpath("//input[@id='mobile_number']"));
    }

    public WebElement getCreateAccountButton() {
        return driver.findElement(
                By.xpath("//button[@data-qa='create-account']"));
    }

    public void verifyAccountCreatedVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[contains(@class, 'col-sm-9')]/h2"))));
    }

    public void verifyLoginPageVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='login-form']/h2"))));
    }

    public WebElement getLoginEmailInput() {
        return driver.findElement(
                By.xpath("//input[@data-qa='login-email']"));
    }

    public WebElement getLoginPasswordInput() {
        return driver.findElement(
                By.xpath("//input[@data-qa='login-password']"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(
                By.xpath("//button[@data-qa='login-button']"));
    }

    public void verifyAccountDeletedVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[contains(@class, 'col-sm-9')]/h2"))));
    }

    public void verifyLoginEmailAndPasswordIncorrect() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//p[contains(text(), 'Your email or password is incorrect!')]"))));
    }

    public void verifySignUpEmailAlreadyExists() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//p[contains(text(), 'Email Address already exist!')]"))));
    }

    public WebElement getAccountCreatedContinueButton() {
        return driver.findElement(
                By.xpath("//a[@data-qa='continue-button']"));
    }

}
