import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class ShopAsGuestTest {

    WebDriver driver;
    @BeforeMethod
    public void setUp () {
        System.setProperty ("webdriver.chrome.driver", "C:\\Program Files\\Maven\\Browser Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get ("http://shop.pragmatic.bg/index.php?route=product/product&product_id=40");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void shopAsGuest(){
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.cssSelector("#cart-total")).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".text-right>a:nth-of-type(2)")));
        driver.findElement(By.cssSelector(".text-right>a:nth-of-type(2)")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[value='guest']")));

        driver.findElement(By.cssSelector("[value='guest']")).click();
        driver.findElement(By.id("button-account")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("input-payment-zone")));
        WebElement inputFirstNameField = driver.findElement(By.id("input-payment-firstname"));
        inputFirstNameField.sendKeys("Dessy");

        WebElement paymentCountry = driver.findElement(By.id("input-payment-country"));
        Select paymentCountrySelect = new Select(paymentCountry);
        paymentCountrySelect.selectByValue("33");

        WebElement inputPaymentZone = driver.findElement(By.id("input-payment-zone"));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//select[@id='input-payment-zone']/option[@value='505']")));
        Select paymentZoneCountrySelect = new Select(inputPaymentZone);
        paymentZoneCountrySelect.selectByValue("494");

        //TODO MY HOMEWORK STARTS HERE

        WebElement inputPaymentLastnameField = driver.findElement(By.id("input-payment-lastname"));
        inputPaymentLastnameField.sendKeys("Sa");

        String randomEmail = RandomStringUtils.randomAlphabetic(7) + "@somegmail.com";
        WebElement inputPaymentEmailField = driver.findElement(By.id("input-payment-email"));
        inputPaymentEmailField.sendKeys(randomEmail);

        String randomTelephone = RandomStringUtils.randomNumeric(10);
        WebElement inputPaymentTelephoneField = driver.findElement(By.id("input-payment-telephone"));
        inputPaymentTelephoneField.sendKeys(randomTelephone);

        WebElement inputPaymentCompanyField = driver.findElement(By.id("input-payment-company"));
        inputPaymentCompanyField.sendKeys("Firma 1 OOD");

        WebElement inputPaymentAddress1Field = driver.findElement(By.id("input-payment-address-1"));
        inputPaymentAddress1Field.sendKeys("ul.Karaslavov 2");

        WebElement inputPaymentCityField = driver.findElement(By.id("input-payment-city"));
        inputPaymentCityField.sendKeys("Silistra");

        String randomPostcode = RandomStringUtils.randomNumeric(4);
        WebElement inputPaymentPostcodeField = driver.findElement(By.id("input-payment-postcode"));
        inputPaymentPostcodeField.sendKeys(randomPostcode);

        WebElement buttonGuestClick = driver.findElement(By.id("button-guest"));
        buttonGuestClick.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("button-shipping-method")));

        WebElement textAriaCommentField = driver.findElement(By.xpath("//*[@id=\"collapse-shipping-method\"]/div/p[4]/textarea"));
        textAriaCommentField.sendKeys("Jelaia dostavka do ofis na kurier");

        WebElement continueButtonShippingMethod = driver.findElement(By.id("button-shipping-method"));
        continueButtonShippingMethod.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("button-payment-method")));
        WebElement checkBoxPaymentButton = driver.findElement(By.xpath("//*[@class=\"pull-right\"]/input[@type=\"checkbox\"]"));
        checkBoxPaymentButton.click();

        WebElement continueButtonPaymentMethod = driver.findElement(By.id("button-payment-method"));
        continueButtonPaymentMethod.click();


        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("button-confirm")));
        WebElement confirmOrderButton = driver.findElement(By.id("button-confirm"));
        confirmOrderButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#content > div > div > a")));

        String contentTextPlacedOrder = driver.findElement(By.cssSelector("#content > h1")).getText();
        assertEquals("Your order has been placed!", contentTextPlacedOrder);

        WebElement continueToHomePageButton = driver.findElement(By.cssSelector("#content > div > div > a"));
        continueToHomePageButton.click();

        // neshto novo za promqna na faila
        ///moreeee


    }
}
