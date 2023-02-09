package automationExercisePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage extends BasePage{
    public ContactUsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public void verifyGetInTouchIsVisible(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[contains(@class, 'col-sm')]/h2"))));
    }
    public WebElement getNameInput(){
        return driver.findElement(
                By.xpath("//input[@name='name']"));
    }
    public WebElement getEmailInput(){
        return driver.findElement(
                By.xpath("//input[@name='email']"));
    }
    public WebElement getSubjectInput(){
        return driver.findElement(
                By.xpath("//input[@name='subject']"));
    }
    public WebElement getMessageInput(){
        return driver.findElement(
                By.xpath("//textarea[@name='message']"));
    }
    public WebElement getUploadFileInput(){
        return driver.findElement(
                By.xpath("//input[@name='upload_file']"));
    }
    public WebElement getSubmitButton(){
        return driver.findElement(
                By.xpath("//input[@name='submit']"));
    }
    public WebElement verifySuccess(){
        return driver.findElement(
                By.xpath("//div[contains(@class, 'status alert alert-success')]"));
    }
    public void switchToAlert(){
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
    }
}
