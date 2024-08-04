import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsByOnlinePaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By cookieAgreeButton = By.id("cookie-agree");
    private By blockTitle = By.xpath("//h2[contains(normalize-space(), 'Онлайн пополнение без комиссии')]");
    private By paymentLogos = By.xpath("//*[@class=\"pay__partners\"]/ul/li/img");
    private By moreInfoLink = By.linkText("Подробнее о сервисе");
    private By phoneInput = By.id("connection-phone");
    private By sumInput = By.id("connection-sum");
    private By emailInput = By.id("connection-email");
    private By continueButton = By.xpath("//*[@id=\"pay-connection\"]/button");
    private By bepaidIframe = By.className("bepaid-iframe");
    private By payDescriptionCost = By.className("pay-description__cost");

    public MtsByOnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPECTED_CONDITION_TIMEOUT));
    }

    public void closeCookieAgreement() {
        List<WebElement> cookieAgreeButtonList = driver.findElements(cookieAgreeButton);
        if (!cookieAgreeButtonList.isEmpty()) {
            cookieAgreeButtonList.get(0).click();
        }
    }

    public boolean isBlockTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle)).isDisplayed();
    }

    public int getPaymentLogosCount() {
        return driver.findElements(paymentLogos).size();
    }

    public void clickMoreInfoLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(moreInfoLink)).click();
    }

    public void fillPaymentForm(String phone, String sum, String email) {
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(sumInput).sendKeys(sum);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(continueButton).click();
    }

    public void switchToIframe() {
        driver.switchTo().frame(driver.findElement(bepaidIframe));
    }

    public boolean isPayDescriptionCostDisplayed() {
        return driver.findElement(payDescriptionCost).isDisplayed();
    }

    public By getPayDescriptionCostLocator() {
        return payDescriptionCost;
    }
}