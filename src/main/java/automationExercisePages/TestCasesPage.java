package automationExercisePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCasesPage extends BasePage {
    public TestCasesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void verifyUserNavigatedToTestCasesPage() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//div[contains(@class, 'col-sm')]/h2"))));
    }
}
