import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.aston.MtsMainPage;
import ru.aston.MtsPaymentPage;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@Epic("Финансовые операции")
@Feature("Оплата услуг")
@Story("Проверка форм онлайн пополнения МТС")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MtsTest {
    private WebDriver driver;
    private MtsMainPage mainPage;
    private MtsPaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mts.by");

        mainPage = new MtsMainPage(driver);
        paymentPage = new MtsPaymentPage(driver);

        mainPage.acceptCookiesIfPresent();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("1. Проверка надписей (placeholder) в незаполненных полях всех вариантов оплаты")
    @Description("Тест проверяет корректность названий подсказок (placeholders) в незаполненных полях ввода для всех типов услуг.")
    public void testPlaceholdersInAllPaymentOptions() {
        // 1. Услуги связи
        mainPage.selectConnectionServices();
        assertEquals("Номер телефона", mainPage.getPlaceholder(mainPage.getPhoneNumberField()));
        assertEquals("Сумма", mainPage.getPlaceholder(mainPage.getConnectionSumField()));
        assertEquals("E-mail для отправки чека", mainPage.getPlaceholder(mainPage.getConnectionEmailField()));

        // 2. Домашний интернет
        mainPage.selectHomeInternet();
        assertEquals("Номер абонента", mainPage.getPlaceholder(mainPage.getInternetAccountField()));
        assertEquals("Сумма", mainPage.getPlaceholder(mainPage.getInternetSumField()));
        assertEquals("E-mail для отправки чека", mainPage.getPlaceholder(mainPage.getInternetEmailField()));

        // 3. Рассрочка
        mainPage.selectInstallment();
        // Актуальный плейсхолдер сайта МТС
        assertEquals("Номер счета на 44", mainPage.getPlaceholder(mainPage.getInstallmentAccountField()));
        assertEquals("Сумма", mainPage.getPlaceholder(mainPage.getInstallmentSumField()));
        assertEquals("E-mail для отправки чека", mainPage.getPlaceholder(mainPage.getInstallmentEmailField()));

        // 4. Задолженность
        mainPage.selectDebt();
        // Проверяем, что возвращает вкладка Задолженность (обычно совпадает с Рассрочкой или Интернетом)
        String debtPlaceholder = mainPage.getPlaceholder(mainPage.getDebtAccountField());
        assertTrue(debtPlaceholder.equals("Номер счета на 44") || debtPlaceholder.equals("Номер абонента"),
                "Неожиданный плейсхолдер на вкладке Задолженность: " + debtPlaceholder);
        assertEquals("Сумма", mainPage.getPlaceholder(mainPage.getDebtSumField()));
        assertEquals("E-mail для отправки чека", mainPage.getPlaceholder(mainPage.getDebtEmailField()));
    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("2. Проверка корректности данных и элементов в появившемся окне оплаты")
    @Description("Заполняет форму валидными тестовыми данными, переходит во фрейм платежного шлюза bePaid и валидирует отображение реквизитов.")
    public void testPaymentIframeDataVerification() {
        String testPhone = "297777777";
        String testSum = "10.00";
        String testEmail = "test@test.by";

        // Шаг 1: Заполняем форму и переходим далее
        mainPage.selectConnectionServices();
        mainPage.fillConnectionForm(testPhone, testSum, testEmail);
        mainPage.clickContinue();

        // Шаг 2: Переключаем контекст WebDriver на платежный iframe
        paymentPage.switchToPaymentIframe();

        // Шаг 3: Проверяем корректность отображения суммы и номера телефона
        String actualAmount = paymentPage.getPaymentAmount();
        String actualPhone = paymentPage.getPaymentPhoneNumber();
        String buttonText = paymentPage.getSubmitButtonText();

        assertTrue(actualAmount.contains(testSum), "Сумма в описании платежа отображается некорректно!");
        assertTrue(actualPhone.contains(testPhone), "Номер телефона в описании платежа отображается некорректно!");
        assertTrue(buttonText.contains(testSum), "Сумма на кнопке 'Оплатить' не совпадает с введенной!");

        // Шаг 4: Проверяем надписи в незаполненных полях карты
        String cardNumberText = paymentPage.getCardFieldLabelText(paymentPage.getCardNumberLabel());
        assertTrue(cardNumberText.toLowerCase().contains("номер"),
                "Текст поля номера карты не содержит слово 'номер'. Актуальный текст: " + cardNumberText);

        String cardExpiryText = paymentPage.getCardFieldLabelText(paymentPage.getCardExpiryLabel());
        assertTrue(cardExpiryText.toLowerCase().contains("срок") || cardExpiryText.contains("ММ"),
                "Текст поля срока действия не содержит 'срок' или 'ММ'. Актуальный текст: " + cardExpiryText);

        String cardCvcText = paymentPage.getCardFieldLabelText(paymentPage.getCardCvcLabel());
        assertTrue(cardCvcText.toUpperCase().contains("CVC") || cardCvcText.toUpperCase().contains("CVV"),
                "Текст поля CVC не содержит 'CVC'/'CVV'. Актуальный текст: " + cardCvcText);

        String cardHolderText = paymentPage.getCardFieldLabelText(paymentPage.getCardHolderLabel());
        assertTrue(cardHolderText.toLowerCase().contains("имя") || cardHolderText.toLowerCase().contains("держатель"),
                "Текст поля держателя карты некорректен. Актуальный текст: " + cardHolderText);

        // Шаг 5: Проверяем наличие иконок платежных систем во фрейме карты
        List<WebElement> logos = paymentPage.getPaymentLogos();
        assertFalse(logos.isEmpty(), "Иконки платежных систем внутри платежной формы не найдены!");

        for (WebElement logo : logos) {
            String imgSrc = logo.getAttribute("src");
            String imgClass = logo.getAttribute("class");

            assertNotNull(imgSrc, "У логотипа отсутствует атрибут src!");
            assertFalse(imgSrc.isEmpty(), "Ссылка на картинку логотипа пустая!");
        }

        // Шаг 6: Возвращаем контекст драйвера на основную страницу
        paymentPage.switchToDefaultContext();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

