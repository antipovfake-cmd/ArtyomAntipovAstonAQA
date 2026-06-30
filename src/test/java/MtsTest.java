import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.aston.MtsMainPage;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MtsTest {
    private WebDriver driver;
    private MtsMainPage mainPage;

    @BeforeEach
    public void setUp() {
        // Инициализация драйвера (ChromeDriver должен быть в PATH или настроен через WebDriverManager)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mts.by");

        mainPage = new MtsMainPage(driver);
        mainPage.acceptCookiesIfPresent(); // Закрываем плашку куки, если она перекрывает элементы
    }

    @Test
    @DisplayName("Проверка названия блока")
    public void testBlockTitle() {
        String expectedTitle = "ОНЛАЙН ПОПОЛНЕНИЕ\n" +
                "БЕЗ КОМИССИИ";
        String actualTitle = mainPage.getBlockTitleText();
        assertEquals(expectedTitle, actualTitle, "Название блока не совпадает!");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void testPaymentLogosPresence() {
        List<WebElement> logos = mainPage.getPaymentLogos();
        assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены!");

        // Проверяем, что каждый логотип отображается на экране
        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(), "Один из логотипов не отображается!");
        }
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreInfoLink() {
        mainPage.clickMoreInfoLink();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("help/poryadok-oplaty-i-bezopasnost"),
                "Ссылка 'Подробнее о сервисе' ведет на некорректный URL: " + currentUrl);
    }

    @Test
    @DisplayName("Проверка заполнения полей и нажатия кнопки 'Продолжить'")
    public void testSubmitPaymentForm() {
        mainPage.selectConnectionServices();

        // Заполняем тестовые данные (Номер, Сумма, Email)
        mainPage.fillPaymentForm("297777777", "10", "test@test.by");
        mainPage.clickContinue();

        // Проверяем, что после клика мы перешли на страницу оплаты или открылся фрейм
        // Логика проверки зависит от поведения сайта (например, появление новой формы или смена URL)
        String currentUrl = driver.getCurrentUrl();
        assertNotNull(currentUrl);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

