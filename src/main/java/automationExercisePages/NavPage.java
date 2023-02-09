package automationExercisePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavPage extends BasePage {
    public NavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getHomeLink() {
        return driver.findElement(
                By.xpath("//a[@href='/']"));
    }

    public WebElement getSignUpLoginLink() {
        return driver.findElement(
                By.xpath("//a[@href='/login']"));
    }

    public WebElement getDeleteAccountLink() {
        return driver.findElement(By.xpath("//a[@href='/delete_account']"));
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.xpath("//a[@href='/logout']"));
    }

    public WebElement getContactUsLink() {
        return driver.findElement(
                By.xpath("//a[@href='/contact_us']"));
    }

    public WebElement getProductsLink() {
        return driver.findElement(
                By.xpath("//a[@href='/products']"));
    }

    public WebElement getCartLink() {
        return driver.findElement(
                By.xpath("//a[@href='/view_cart']"));
    }

    public void scrollToFooter() {
        new Actions(driver).scrollToElement(
                driver.findElement(By.xpath("//footer/div[@class='footer-bottom']")))
                .perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//footer/div[@class='footer-bottom']"))));
    }

    public boolean verifySubscriptionIsDisplayed() {
        return driver.findElement(
                By.xpath("//div[@class='single-widget']/h2")).isDisplayed();
    }

    public WebElement getSubscriptionInput() {

        return driver.findElement(
                By.xpath("//input[@id='susbscribe_email']"));
    }

    public WebElement getSubscriptionButton() {

        return driver.findElement(
                By.xpath("//button[@id='subscribe']"));
    }

    public boolean verifySubscriptionSuccessIsDisplayed() {
        return driver.findElement(
                By.xpath("//div[@class='alert-success alert']")).isDisplayed();
    }

    public void verifyLoggedInAsVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//i[contains(@class, 'fa fa-user')]/.."))));
    }

    public void ifAdShownNavigateTo(String page) {
        if (driver.getCurrentUrl().contains("#google_vignette")) {
            driver.navigate().to("https://automationexercise.com" + page);
        }
    }
}
