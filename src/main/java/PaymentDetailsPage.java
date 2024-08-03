import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.Locale;

public class PaymentDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By serviceDropdownLocator = By.className("select__header");
    private By connectionPhoneFieldLocator = By.id("connection-phone");
    private By connectionSumFieldLocator = By.id("connection-sum");
    private By connectionEmailFieldLocator = By.id("connection-email");
    private By internetPhoneFieldLocator = By.id("internet-phone");
    private By internetSumFieldLocator = By.id("internet-sum");
    private By internetEmailFieldLocator = By.id("internet-email");
    private By instalmentScoreFieldLocator = By.id("score-instalment");
    private By instalmentSumFieldLocator = By.id("instalment-sum");
    private By instalmentEmailFieldLocator = By.id("instalment-email");
    private By arrearsScoreFieldLocator = By.id("score-arrears");
    private By arrearsSumFieldLocator = By.id("arrears-sum");
    private By arrearsEmailFieldLocator = By.id("arrears-email");
    private By costLocator = By.className("pay-description__cost");
    private By paymentButtonLocator = By.xpath("//button[text()=' Оплатить  5.00 BYN ']");
    private By phoneLocator = By.xpath("//div[@class='pay-description__text']/span");
    private By creditCardNumberLabelLocator = By.xpath("//input[@formcontrolname='creditCard']/following-sibling::label");
    private By expirationDateLabelLocator = By.xpath("//input[@formcontrolname='expirationDate']/following-sibling::label");
    private By cvcLabelLocator = By.xpath("//input[@formcontrolname='cvc']/following-sibling::label");
    private By holderLabelLocator = By.xpath("//input[@formcontrolname='holder']/following-sibling::label");
    private By paymentLogosLocator = By.xpath("//div[contains(@class, 'cards-brands__container') or contains(@class, 'cards-brands_random')]/child::img");


    public PaymentDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPECTED_CONDITION_TIMEOUT));
    }

    public void selectServiceType(String serviceType) {
        WebElement dropdown = driver.findElement(serviceDropdownLocator);
        dropdown.click();
        dropdown.findElement(By.xpath("//option[contains(text(), '" + serviceType + "')]")).click();
    }

    public WebElement getPaymentTypeFirstField(String serviceType) {
        return getWebElement(serviceType, connectionPhoneFieldLocator, internetPhoneFieldLocator,
                instalmentScoreFieldLocator, arrearsScoreFieldLocator);
    }

    public WebElement getSumField(String serviceType) {
        return getWebElement(serviceType, connectionSumFieldLocator, internetSumFieldLocator,
                instalmentSumFieldLocator, arrearsSumFieldLocator);
    }

    public WebElement getEmailField(String serviceType) {
        return getWebElement(serviceType, connectionEmailFieldLocator, internetEmailFieldLocator,
                instalmentEmailFieldLocator, arrearsEmailFieldLocator);
    }

    private WebElement getWebElement(String serviceType,
                                     By paymentTypeFirstFieldLocator, By paymentTypeSecondFieldLocator,
                                     By paymentTypeThirdFieldLocator, By paymentTypeFourthFieldLocator) {
        switch (serviceType) {
            case "Услуги связи":
                return driver.findElement(paymentTypeFirstFieldLocator);
            case "Домашний интернет":
                return driver.findElement(paymentTypeSecondFieldLocator);
            case "Рассрочка":
                return driver.findElement(paymentTypeThirdFieldLocator);
            case "Задолженность":
                return driver.findElement(paymentTypeFourthFieldLocator);
            default:
                throw new IllegalArgumentException("Некорректный тип услуги: " + serviceType);
        }
    }

    public String getPaymentCost() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(costLocator)).getText();
    }

    public String getPaymentButtonText() {
        return driver.findElement(paymentButtonLocator).getText();
    }

    public String getPhoneDescription() {
        return driver.findElement(phoneLocator).getText();
    }

    public String getCreditCardNumberLabel() {
        return driver.findElement(creditCardNumberLabelLocator).getText();
    }
    public String getExpirationDateLabel() {
        return driver.findElement(expirationDateLabelLocator).getText();
    }

    public String getCvcLabel() {
        return driver.findElement(cvcLabelLocator).getText();
    }

    public String getHolderLabel() {
        return driver.findElement(holderLabelLocator).getText();
    }

    public int getPaymentLogosCount() {
        return driver.findElements(paymentLogosLocator).size();
    }

    public String formatValue(String value) {
        double doubleValue = Double.parseDouble(value);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("#.00", symbols);
        return decimalFormat.format(doubleValue);
    }
}