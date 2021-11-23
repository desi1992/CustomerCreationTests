package backend;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class CustomerCreationTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp () {
        System.setProperty ("webdriver.chrome.driver", "C:\\Program Files\\Maven\\Browser Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get ("http://shop.pragmatic.bg/admin/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }


    @Test
    public void successfulCreationOfNewCustomer(){

        WebElement userName = driver.findElement(By.id("input-username"));
        userName.sendKeys("admin");
        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("parola123!");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("#menu-customer>a")).click();
        driver.findElement(By.xpath("//*[@id=\"collapse5\"]/li[1]/a")).click();
        WebElement addCustomerButton = driver.findElement(By.cssSelector(".pull-right>a"));
        addCustomerButton.click();
        WebElement inputFirstName = driver.findElement(By.id("input-firstname"));
        inputFirstName.sendKeys("Dispina");
        WebElement inputLastName = driver.findElement(By.id("input-lastname"));
        inputLastName.sendKeys("Savopulo");

        String randomEmail = RandomStringUtils.randomAlphabetic(7) + "@somemail.com";
        WebElement inputEmail = driver.findElement(By.id("input-email"));
        inputEmail.sendKeys(randomEmail);

        String randomTelephone = RandomStringUtils.randomNumeric(10);
        WebElement inputTelephone = driver.findElement(By.id("input-telephone"));
        inputTelephone.sendKeys(randomTelephone);

        WebElement inputPasswordUser = driver.findElement(By.id("input-password"));
        inputPasswordUser.sendKeys("parola123!");
        WebElement inputConfirmPassword = driver.findElement(By.id("input-confirm"));
        inputConfirmPassword.sendKeys("parola123!");

        Select selectNewsletterField = new Select(driver.findElement(By.id("input-newsletter")));
        selectNewsletterField.selectByValue("1");

        WebElement saveButton = driver.findElement(By.xpath("//div[@class='pull-right']/button"));
        saveButton.click();

        //TODO MY HOMEWORK STARTS HERE

        WebElement inputSearchByEmailField = driver.findElement(By.id("input-email"));
        inputSearchByEmailField.sendKeys("CgsqfWp@somemail.com");

        WebElement searchButtonFilter = driver.findElement(By.id("button-filter"));
        searchButtonFilter.click();

        String filteredCustomerField = driver.findElement(By.cssSelector("#form-customer > table > tbody > tr > td:nth-child(3)")).getText();
        assertEquals("CgsqfWp@somemail.com", filteredCustomerField);


    }
}
