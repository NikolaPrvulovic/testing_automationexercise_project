package automationExercisePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private int product1Price;
    private int product2Price;
    private int productsTotalPrice;
    private boolean verifyCartProductQuantity;
    private List<String> listOfDetails;
    private List<WebElement> cartProductElements;
    private ArrayList<String> newList = new ArrayList<>();

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    ////////////////getters


    public List<WebElement> getCartProductElements() {
        return cartProductElements;
    }

    public int getProduct1Price() {
        return product1Price;
    }

    public int getProduct2Price() {
        return product2Price;
    }

    public int getProductsTotalPrice() {
        return productsTotalPrice;
    }

    public boolean isVerifyCartProductQuantity() {
        return verifyCartProductQuantity;
    }

    /////////////////////
    public int setProductsInCartPricesQuantityTotalPriceReturnsTotalQuantity() {
        List<WebElement> productsInCart = driver.findElements(
                By.xpath("//tbody/tr"));
        int product1 = 0;
        int product2 = 0;
        int totalQuantity = 0;
        int productPrice;
        for (int i = 0; i < productsInCart.size(); i++) {
            String element = productsInCart.get(i).findElement(
                    By.xpath("td[contains(@class,'price')]")).getText();
            if (element.contains("Rs.")) {
                element = element.replace("Rs. ", "");
            }
            try {
                productPrice = Integer.parseInt(element);
            } catch (NumberFormatException e) {
                productPrice = 0;
            }
            if (productPrice == 500 || productPrice == 400) {
                if (productPrice == 500) {
                    product1 = 500;
                    product1Price = product1;
                } else {
                    product2 = 400;
                    product2Price = product2;
                }
                productsTotalPrice = product1 + product2;
            }
            element = productsInCart.get(i).findElement(
                    By.xpath("td[contains(@class,'quantity')]")).getText();
            try {
                productPrice = Integer.parseInt(element);
            } catch (NumberFormatException e) {
                productPrice = 0;
            }
            if ((productPrice == 1 && i == 0) || (productPrice == 1 && i == 1)) {
                totalQuantity++;
            }
        }
        return totalQuantity;
    }

    public void verifyActualQuantityExact(int quantity) {

        String element = driver.findElement(
                By.xpath("//tbody/tr[1]/td[contains(@class,'quantity')]")).getText();
        int productQuantity;
        try {
            productQuantity = Integer.parseInt(element);
        } catch (NumberFormatException e) {
            productQuantity = 0;
        }
        if (productQuantity == quantity) {
            verifyCartProductQuantity = true;
        } else {
            verifyCartProductQuantity = false;
        }
    }

    public void verifyCartPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@class='col-sm-6']/a"))));
    }

    public WebElement getProceedToCheckoutButton() {
        return driver.findElement(
                By.xpath("//div[@class='col-sm-6']/a"));
    }

    public void verifyCheckoutDialogIsShown() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[@id='checkoutModal']"))));
    }

    public WebElement getRegisterLoginDialogButton() {
        return driver.findElement(
                By.xpath("//div[@id='checkoutModal']//a[@href='/login']"));
    }

    public boolean verifyAddressDetails() {
        List<WebElement> deliveryAddressDetailsElements = driver.findElements(
                By.xpath("//*[@id='address_delivery']/li"));
        List<WebElement> billingAddressDetailsElements = driver.findElements(
                By.xpath("//*[@id='address_invoice']/li"));
        int delivery = 0;
        int billing = 0;
        for (int i = 0; i < listOfDetails.size(); i++) {
            String string = listOfDetails.get(i);
            if (deliveryAddressDetailsElements.stream().anyMatch(element -> element.getText().contains(string)) &&
                    billingAddressDetailsElements.stream().anyMatch(element -> element.getText().contains(string))) {
                delivery++;
                billing++;
            }

        }
        if (delivery > 0 && billing > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> listOfAddressDetails(List<String> lista) {
        return listOfDetails = lista;
    }

    public boolean verifyReviewYourOrder() {
        List<WebElement> productsInCart = driver.findElements(
                By.xpath("//tbody/tr[contains(@id,'product')]"));
        newList.add("Blue Top");
        newList.add("Men Tshirt");
        newList.add("Sleeveless Dress");
        int num = 0;
        for (int i = 0; i < productsInCart.size(); i++) {
            if (i < newList.size() && productsInCart.get(i).findElement(
                    By.xpath("td[contains(@class,'description')]")).getText().contains(newList.get(i))) {
                num++;
            }
        }
        if (num == 3) {
            return true;
        } else {
            return false;
        }
    }

    public WebElement getDescriptionInput() {
        new Actions(driver).scrollToElement(driver.findElement(
                By.xpath("//textarea"))).perform();
        return driver.findElement(
                By.xpath("//textarea"));
    }

    public WebElement getPlaceOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable((
                By.xpath("//a[@href='/payment']"))));
        return driver.findElement(
                By.xpath("//a[@href='/payment']"));
    }

    public WebElement getNameOnCardInput() {
        return driver.findElement(
                By.xpath("//input[@name='name_on_card']"));
    }

    public WebElement getCardNumberInput() {
        return driver.findElement(
                By.xpath("//input[@name='card_number']"));
    }

    public WebElement getCVCInput() {
        return driver.findElement(
                By.xpath("//input[@name='cvc']"));
    }

    public WebElement getExpirationMonthInput() {
        return driver.findElement(
                By.xpath("//input[@name='expiry_month']"));
    }

    public WebElement getExpirationYearInput() {
        return driver.findElement(
                By.xpath("//input[@name='expiry_year']"));
    }

    public WebElement getPayAndComfirmOrderButton() {
        return driver.findElement(
                By.xpath("//button[@id='submit']"));
    }

    public void getYourOrderPlacedSuccessfullyMessage() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[contains(@class, 'col-sm-9 col-sm-offset-1')]"))));
    }

    public WebElement getDeleteProductFromCartButton(int productNumber) {
        return driver.findElement(
                By.xpath("//tbody/tr[" + productNumber + "]/td[@class='cart_delete']/a"));
    }

    public void verifyTableBodyNumberOfRowsZero() {
        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//tbody/tr"), 0));
    }

    public void setCartProductElements() {
        cartProductElements = driver.findElements(
                By.xpath("//tbody/tr[contains(@id,'product')]"));
    }

    public boolean reviewCartProducts(int productNumber) {

        int num = 0;
        for (int i = 0; i < cartProductElements.size(); i++) {
            String attributeValueCartProduct;
            String attributeValueProduct;
            attributeValueCartProduct = cartProductElements.get(i).findElement(
                    By.xpath("td[contains(@class, 'description')]/h4/a")).getAttribute("href");
            attributeValueProduct = driver.findElement(
                    By.xpath("//a[@href='/product_details/" + productNumber + "']/../../h4/a"))
                    .getAttribute("href");
            if (attributeValueCartProduct.contains(attributeValueProduct)) {
                num++;
            }
        }
        return num == cartProductElements.size();
    }

    public WebElement getDownloadInvoiceButton() {
        new Helper().downloadFile("https://automationexercise.com/" +
                driver.findElement(
                        By.xpath("//a[contains(@href, '/download_invoice/')]"))
                        .getAttribute("href"), "downloads/invoice.txt");
        return driver.findElement(
                By.xpath("//a[contains(@href, '/download_invoice/')]"));
    }
}
