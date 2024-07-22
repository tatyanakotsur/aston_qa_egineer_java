import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsByTest {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://mts.by");

        // Проверка названия блока
        WebElement blockTitle = driver.findElement(
                By.xpath("//h2[contains(normalize-space(), 'Онлайн пополнение без комиссии')]"));
        if (blockTitle.isDisplayed()) {
            System.out.println("Название блока 'Онлайн пополнение без комиссии' отображается корректно");
        } else {
            System.out.println("Ошибка: Название блока 'Онлайн пополнение без комиссии' не отображается");
        }

        // Проверка наличия логотипов платёжных систем
        List<WebElement> paymentLogos = driver.findElements(
                By.xpath("//*[@class=\"pay__partners\"]/ul/li/img"));
        if (paymentLogos.size() == 5) {
            System.out.println("Логотипы платёжных систем отображаются корректно");
        } else {
            System.out.println("Ошибка: Логотипы платёжных систем не отображаются");
        }

        List<WebElement> cookieAgreeButtonList = driver.findElements(By.id("cookie-agree"));
        if (!cookieAgreeButtonList.isEmpty()) {
            cookieAgreeButtonList.get(0).click();
        }

        // Проверка работы ссылки "Подробнее о сервисе"
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        moreInfoLink.click();
        if (driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey")) {
            System.out.println("Ссылка 'Подробнее о сервисе' работает корректно");
        } else {
            System.out.println("Ошибка: Ссылка 'Подробнее о сервисе' не работает");
        }

        driver.navigate().back();

        // Заполнение полей и проверка работы кнопки "Продолжить"
        By phoneNumberInputBy = By.id("connection-phone");
        WebElement phoneNumberInput = waitForWebElement(driver, Duration.ofSeconds(5), phoneNumberInputBy);
        phoneNumberInput.sendKeys("297777777");
        By sumInputBy = By.id("connection-sum");
        WebElement sumInput = waitForWebElement(driver, Duration.ofSeconds(5), sumInputBy);
        sumInput.sendKeys("5");
        By emailInputBy = By.id("connection-email");
        WebElement emailInput = waitForWebElement(driver, Duration.ofSeconds(5), emailInputBy);
        emailInput.sendKeys("toyota_2510@mail.ru");

        By continueButtonBy = By.xpath("//*[@id=\"pay-connection\"]/button");
        WebElement continueButton = waitForWebElement(driver, Duration.ofSeconds(5), continueButtonBy);
        continueButton.click();

        By openedIframeBy = By.className("bepaid-iframe");
        WebElement openedIframe = waitForWebElement(driver, Duration.ofSeconds(25), openedIframeBy);
        if (openedIframe.isDisplayed()) {
            System.out.println("Кнопка 'Продолжить' работает корректно");
        } else {
            System.out.println("Ошибка: Кнопка 'Продолжить' не работает");
        }

        driver.switchTo().frame(openedIframe);

        By payDescriptionCostBy = By.className("pay-description__cost");
        if (waitForWebElement(driver, Duration.ofSeconds(10), payDescriptionCostBy).isDisplayed()) {
            System.out.println("Сумма пополнения отображается во фрейме корректно");
        } else {
            System.out.println("Сумма пополнения не отображается во фрейме");
        }

        driver.switchTo().defaultContent();
        driver.quit();
    }
    public static WebElement waitForWebElement(WebDriver driver, Duration duration, By by) {
        return new WebDriverWait(driver, duration).
                until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}