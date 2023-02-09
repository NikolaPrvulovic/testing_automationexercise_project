package automationExercisePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void verifyHomePageVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//a[@href='/']/img"))));
    }

    public void scrollToProduct(int productNumber) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//img[@src='/get_product_picture/" + productNumber + "']"))));
        new Actions(driver).scrollToElement(driver.findElement(
                By.xpath("//img[@src='/get_product_picture/" + productNumber + "']"))).perform();
    }

    public WebElement getViewProductButton(int productNumber) {
        return driver.findElement(
                By.xpath("//a[@href='/product_details/" + productNumber + "']"));
    }

    public void scrollToHeader() {
        new Actions(driver).scrollToElement(driver.findElement(
                By.xpath("//header"))).perform();
    }

    public WebElement getAddToCartButton(int productNumber) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='overlay-content']/a[@data-product-id='" + productNumber + "']"))));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
                By.xpath("//div[@class='overlay-content']/a[@data-product-id='" + productNumber + "']"))));
        return driver.findElement(
                By.xpath("//div[@class='overlay-content']/a[@data-product-id='" + productNumber + "']"));
    }

    public void verifyLeftSideBarVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='left-sidebar']"))));
    }

    public WebElement getWomenCategoryLink() {
        return driver.findElement(
                By.xpath("//a[@href='#Women']"));
    }

    public WebElement getDressCategoryUnderWomanCategoryLink() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//a[@href='/category_products/1']"))));
        return driver.findElement(
                By.xpath("//a[@href='/category_products/1']"));
    }

    public WebElement getMenCategoryLink() {
        return driver.findElement(
                By.xpath("//a[@href='#Men']"));
    }

    public WebElement getTshirtsCategoryUnderManCategoryLink() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//a[@href='/category_products/3']"))));
        return driver.findElement(
                By.xpath("//a[@href='/category_products/3']"));
    }

    public void verifyBrandsSideBarIsVisible() {
        new Actions(driver).scrollToElement(driver.findElement(
                By.xpath("//a[@href='/brand_products/Polo']"))).perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='brands_products']"))));
    }

    public WebElement getPoloBrandLink(String brand) {
        return driver.findElement(
                By.xpath("//a[@href='/brand_products/" + brand + "']"));
    }

    public void verifyRecommendedItemsVisible() {
        new Actions(driver).scrollToElement(driver.findElement(
                By.xpath("//div[@class='recommended_items']"))).perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='recommended_items']"))));
    }

    public WebElement getAddToCartIdInSliderVisible() {
        wait.until(ExpectedConditions.attributeContains(driver.findElement(
                By.xpath("//div[@class='recommended_items']//div[@class='carousel-inner']/div[1]")),
                "class", "active"));
        return driver.findElement(
                By.xpath("//*[@id='recommended-item-carousel']/div/div[1]/div[1]/div/div/div/a"));
    }

    public WebElement getScrollUpArrow() {
        return driver.findElement(
                By.xpath("//a[@id='scrollUp']"));
    }
}
