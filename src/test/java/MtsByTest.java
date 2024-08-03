import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        assertEquals(Constants.EXPECTED_PAYMENT_LOGOS_COUNT, mtsByOnlinePaymentPage.getPaymentLogosCount(),
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
        mtsByOnlinePaymentPage.fillPaymentForm(Constants.PHONE, Constants.SUM, Constants.EMAIL);
        mtsByOnlinePaymentPage.switchToIframe();

        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPECTED_CONDITION_TIMEOUT)).until(ExpectedConditions.
                visibilityOfElementLocated(mtsByOnlinePaymentPage.getPayDescriptionCostLocator()));

        assertTrue(mtsByOnlinePaymentPage.isPayDescriptionCostDisplayed(),
                "Сумма пополнения не отображается во фрейме");
    }

    @Test
    @DisplayName("Проверка надписей незаполненных полей для каждого варианта оплаты услуг выпадающего списка")
    public void testPaymentTypeUnfilledFields() {
        checkUnfilledFieldPlaceholder("Услуги связи");
        checkUnfilledFieldPlaceholder("Домашний интернет");
        checkUnfilledFieldPlaceholder("Рассрочка");
        checkUnfilledFieldPlaceholder("Задолженность");
    }

    private void checkUnfilledFieldPlaceholder(String serviceType) {
        PaymentDetailsPage paymentDetailsPage = new PaymentDetailsPage(driver);
        paymentDetailsPage.selectServiceType(serviceType);

        WebElement paymentTypeFirstField = paymentDetailsPage.getPaymentTypeFirstField(serviceType);
        WebElement sumField = paymentDetailsPage.getSumField(serviceType);
        WebElement emailField = paymentDetailsPage.getEmailField(serviceType);

        String firstFieldPlaceholderText = paymentTypeFirstField.getAttribute("placeholder");
        switch (serviceType) {
            case "Услуги связи":
                assertEquals("Номер телефона", firstFieldPlaceholderText,
                        "Ошибка: надпись поля ввода номера телефона не корректна");
                break;
            case "Домашний интернет":
                assertEquals("Номер абонента", firstFieldPlaceholderText,
                        "Ошибка: надпись поля ввода номера абонента не корректна");
                break;
            case "Рассрочка":
                assertEquals("Номер счета на 44", firstFieldPlaceholderText,
                        "Ошибка: надпись поля ввода номера счета на 44 не корректна");
                break;
            case "Задолженность":
                assertEquals("Номер счета на 2073", firstFieldPlaceholderText,
                        "Ошибка: надпись поля ввода номера счета на 2073 не корректна");
                break;
        }
        assertEquals("Сумма", sumField.getAttribute("placeholder"),
                "Ошибка: надпись поля ввода суммы не корректна");
        assertEquals("E-mail для отправки чека", emailField.getAttribute("placeholder"),
                "Ошибка: надпись поля ввода email не корректна");
    }

    @Test
    @DisplayName("Проверка корректности отображения реквизитов для оплаты в открывшемся окне")
    public void testIframePaymentDetails() {
        mtsByOnlinePaymentPage.fillPaymentForm(Constants.PHONE, Constants.SUM, Constants.EMAIL);
        driver.switchTo().frame(driver.findElement(By.className("bepaid-iframe")));

        PaymentDetailsPage paymentDetailsPage = new PaymentDetailsPage(driver);

        // Проверка корректности отображения суммы
        assertEquals(paymentDetailsPage.formatValue(Constants.SUM) + " BYN",
                paymentDetailsPage.getPaymentCost(),
                "Ошибка: Сумма пополнения во фрейме отображается некорректно");

        // Проверка текста на кнопке
        assertEquals("Оплатить " + paymentDetailsPage.formatValue(Constants.SUM) + " BYN",
                paymentDetailsPage.getPaymentButtonText(),
                "Ошибка: Сумма пополнения во фрейме на кнопке отображается некорректно");

        // Проверка номера телефона
        assertEquals("Оплата: Услуги связи Номер:375" + Constants.PHONE,
                paymentDetailsPage.getPhoneDescription(),
                "Ошибка: Номер телефона во фрейме отображается некорректно");

        // Проверка надписей полей ввода реквизитов карты
        assertEquals("Номер карты", paymentDetailsPage.getCreditCardNumberLabel(),
                "Ошибка: надпись поля ввода номера карты не корректна");
        assertEquals("Срок действия", paymentDetailsPage.getExpirationDateLabel(),
                "Ошибка: надпись поля ввода срока действия карты не корректна");
        assertEquals("CVC", paymentDetailsPage.getCvcLabel(),
                "Ошибка: надпись поля ввода cvc не корректна");
        assertEquals("Имя держателя (как на карте)", paymentDetailsPage.getHolderLabel(),
                "Ошибка: надпись поля ввода имени держателя карты не корректна");

        // Проверка количества логотипов платёжных систем
        assertEquals(Constants.EXPECTED_PAYMENT_LOGOS_COUNT, paymentDetailsPage.getPaymentLogosCount(),
                "Ошибка: Логотипы платёжных систем не отображаются корректно");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}