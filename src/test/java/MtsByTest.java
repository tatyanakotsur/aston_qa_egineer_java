import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTest {
    private WebDriver driver;
    private MtsByOnlinePaymentPage mtsByOnlinePaymentPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://mts.by");
        mtsByOnlinePaymentPage = new MtsByOnlinePaymentPage(driver);

        // Закрытие окна согласия с куками, если оно отображается
        mtsByOnlinePaymentPage.closeCookieAgreement();
    }

    @Test
    @DisplayName("Проверка отображения названия блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        assertTrue(mtsByOnlinePaymentPage.isBlockTitleDisplayed(),
                "Ошибка: Название блока 'Онлайн пополнение без комиссии' не отображается");
    }

    @Test
    @DisplayName("Проверка корректного отображения логотипов платёжных систем")
    public void testPaymentLogos() {
        assertEquals(5, mtsByOnlinePaymentPage.getPaymentLogosCount(),
                "Ошибка: Логотипы платёжных систем не отображаются корректно");
    }

    @Test
    @DisplayName("Проверка работоспособности ссылки 'Подробнее о сервисе'")
    public void testMoreInfoLink() {
        mtsByOnlinePaymentPage.clickMoreInfoLink();
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Ошибка: Ссылка 'Подробнее о сервисе' не работает");
    }

    @Test
    @DisplayName("Проверка работы кнопки 'Продолжить' и отображения суммы пополнения во фрейме")
    public void testContinueButton() {
        mtsByOnlinePaymentPage.fillPaymentForm(Constants.phone, Constants.sum, Constants.email);
        mtsByOnlinePaymentPage.switchToIframe();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                visibilityOfElementLocated(mtsByOnlinePaymentPage.getPayDescriptionCostLocator()));

        assertTrue(mtsByOnlinePaymentPage.isPayDescriptionCostDisplayed(),
                "Сумма пополнения не отображается во фрейме");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}