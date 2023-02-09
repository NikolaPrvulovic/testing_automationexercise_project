package automationExercisePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {
    private List<WebElement> allProducts;
    private ArrayList<Integer> idNumbersOfProducts = new ArrayList<>();

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    ////////////getters
    public ArrayList<Integer> getIdNumbersOfProducts() {
        return idNumbersOfProducts;
    }

    /////////////
    public void verifyAllProductsPageIsVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[contains(@class, 'features_items')]/h2"))));
    }

    public void verifyProductsListIsVisible() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//div[contains(@class, 'col-sm-4')]"), 0));
    }

    public WebElement getFirstProductDetailLink() {
        return driver.findElement(
                By.xpath("//a[@href='/product_details/1']"));
    }

    public void verifyFirstProductIsVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
                By.xpath("//a[@href='/product_details/1']"))));
        new Actions(driver).scrollToElement(driver.findElement(
                By.xpath("//a[@href='/product_details/4']"))).perform();
    }

    public void verifyUserLandedOnFirstProductDetailPage() {
        wait.until(ExpectedConditions.urlContains("/product_details/1"));
    }

    public void verifyDetailsOfFirstProductAreVisible() {
        List<WebElement> listOfDetails = new ArrayList<>();
        listOfDetails.add(driver.findElement(
                By.xpath("//div[@class='product-information']/h2")));
        listOfDetails.add(driver.findElement(
                By.xpath("//div[@class='product-information']/p[1]")));
        listOfDetails.add(driver.findElement(
                By.xpath("//div[@class='product-information']/span/span")));
        listOfDetails.add(driver.findElement(
                By.xpath("//div[@class='product-information']/p[2]")));
        listOfDetails.add(driver.findElement(
                By.xpath("//div[@class='product-information']/p[3]")));
        listOfDetails.add(driver.findElement(
                By.xpath("//div[@class='product-information']/p[4]")));
        for (int i = 0; i < listOfDetails.size(); i++) {
            wait.until(ExpectedConditions.visibilityOf(listOfDetails.get(i)));
        }
    }

    public WebElement getSearchInput() {
        return driver.findElement(
                By.xpath("//input[@name='search']"));
    }

    public WebElement getSearchButton() {
        return driver.findElement(
                By.xpath("//button[@id='submit_search']"));
    }

    public ArrayList<String> verifyAllProductsRelateToSearch() {
        List<WebElement> listOfProducts = driver.findElements(
                By.xpath("//div[contains(@class, 'col-sm-4')]//div[contains(@class, 'productinfo')]"));
        ArrayList<String> nameOfProducts = new ArrayList<>();
        for (int i = 0; i < listOfProducts.size(); i++) {
            nameOfProducts.add(listOfProducts.get(i).getText().toLowerCase());
        }
        return nameOfProducts;
    }

    public void verifyOverlayVisibleAndMoveTo(int productNumber) throws InterruptedException {
        new Actions(driver)
                .scrollToElement(driver.findElement(

                        By.xpath("//a[@href='/product_details/" + productNumber + "']")))
                .perform();
        new Actions(driver)
                .moveToElement(driver.findElement(

                        By.xpath("//img[@src='/get_product_picture/" + productNumber + "']")))
                .perform();
        Thread.sleep(1000);
    }

    public WebElement getAddToCartButton(int productNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
                By.xpath("//div[@class='overlay-content']/a[@data-product-id='" + productNumber + "']"))));
        return driver.findElement(
                By.xpath("//div[@class='overlay-content']/a[@data-product-id='" + productNumber + "']"));
    }

    public WebElement getContinueShoppingDialogButton() {
        new Actions(driver).moveToElement(driver.findElement(
                By.xpath("//div[@class='modal-footer']/button")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='modal-footer']/button"))));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
                By.xpath("//div[@class='modal-footer']/button"))));
        return driver.findElement(
                By.xpath("//div[@class='modal-footer']/button"));
    }

    public void verifyCartDialogIsShown() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='cartModal']")));
    }

    public WebElement getViewCartDialogButton() {
        return driver.findElement(
                By.xpath("//div[@id='cartModal']//a[@href='/view_cart']"));
    }

    public WebElement getQuantityInput() {
        driver.findElement(
                By.xpath("//input[@id='quantity']")).clear();
        return driver.findElement(
                By.xpath("//input[@id='quantity']"));
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(
                By.xpath("//button[@type='button']"));
    }

    public void getAllProductsIDFromPageToCart() {
        allProducts = driver.findElements(
                By.xpath("//div[@class='col-sm-4']/div/div/div/a"));
        for (int i = 0; i < allProducts.size(); i++) {
            int num = 0;
            String string = allProducts.get(i).getAttribute("data-product-id");
            try {
                idNumbersOfProducts.add(Integer.parseInt(string));
            } catch (NumberFormatException e) {
                num = 0;
            }
        }
    }

    public void verifyWriteYourReviewIsVisible() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[contains(@class, 'shop-details')]/div[@class='col-sm-12']"))));
    }

    public WebElement getWriteReviewNameInput() {
        return driver.findElement(
                By.xpath("//input[@id='name']"));
    }

    public WebElement getWriteReviewEmailInput() {
        return driver.findElement(
                By.xpath("//input[@id='email']"));
    }

    public WebElement getWriteReviewTextInput() {
        return driver.findElement(
                By.xpath("//textarea[@id='review']"));
    }

    public WebElement getWriteReviewButton() {
        return driver.findElement(
                By.xpath("//button[@id='button-review']"));
    }

    public void verifyWriteReviewSuccess() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[contains(@class, 'col-md-12')]/div[contains(@class, 'alert-success alert')]"))));
    }

}

