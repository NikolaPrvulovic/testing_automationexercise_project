package automationExerciseTests;

import automationExercisePages.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public abstract class BaseTest {
    protected ChromeOptions options;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseURL = "https://automationexercise.com";
    protected HomePage homePage;
    protected NavPage navPage;
    protected LoginSignUpPage loginSignUpPage;
    protected ContactUsPage contactUsPage;
    protected TestCasesPage testCasesPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected Random random;
    protected int randInt;
    protected String name;
    protected String email;
    protected String password;
    protected String productSearch;
    protected JavascriptExecutor javascriptExecutor;
    protected String firstName;
    protected String lastName;
    protected String company;
    protected String street1;
    protected String street2;
    protected String country;
    protected String state;
    protected String city;
    protected String zipCode;
    protected String mobileNumber;
    protected List<String> listOfDetals;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        random = new Random();
        randInt = random.nextInt(2000);

        name = "dzoni";
        email = "nikolaprv" + randInt + "@gmail.com";
        password = "1234512345";

        firstName = "Nikola";
        lastName = "Prvulovic";
        company = "TestCompany";
        street1 = "Street1";
        street2 = "Street2";
        country = "United States";
        state = "California";
        city = "San Francisco";
        zipCode = "94115";
        mobileNumber = "123456123456";
        listOfDetals = new ArrayList<>(Arrays.asList(firstName, lastName, company, street1,
                street2, country, state, city, zipCode, mobileNumber));

        productSearch = "";


    }

    @BeforeMethod
    public void beforeMethod() {
        options = new ChromeOptions();
        options.addArguments(                                               //adblocker because website is full of ads
                "load-extension=C:\\Users\\user\\AppData\\Local\\Google\\Chrome\\" +
                        "User Data\\Default\\Extensions\\ggdpplfehdighdpleoegjefnpefgpgfh\\1.0.10_0");
        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        javascriptExecutor = (JavascriptExecutor) driver;

        homePage = new HomePage(driver, wait);
        navPage = new NavPage(driver, wait);
        loginSignUpPage = new LoginSignUpPage(driver, wait);
        contactUsPage = new ContactUsPage(driver, wait);
        testCasesPage = new TestCasesPage(driver, wait);
        productsPage = new ProductsPage(driver, wait);
        cartPage = new CartPage(driver, wait);

        driver.get(this.baseURL);
        homePage.verifyHomePageVisible();
    }


    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("dd-MM-yyyy__hh-mm-ss").format(new Date());
            Files.copy(file.toPath(), new File("screenshots/" + result.getName() +
                    " - " + timestamp + ".png").toPath());
        }
        driver.quit();

    }

    @AfterClass
    public void afterClass() {
    }
}
