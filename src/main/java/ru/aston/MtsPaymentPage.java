package ru.aston;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MtsPaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локатор самого iframe (платежного окна)
    private final By paymentIframe = By.xpath("//iframe[contains(@src, 'bepaid') or contains(@class, 'iframe')]");

    // Элементы внутри фрейма оплаты
    private final By paymentAmountText = By.xpath("//span[contains(@class, 'pay-description__cost') or contains(text(), 'BYN')]");
    private final By paymentPhoneNumberText = By.xpath("//span[contains(@class, 'pay-description__text') or contains(text(), '375')]");
    private final By submitButtonWithAmount = By.cssSelector("button.bepaid-btn, button[type='submit'], .pay-button, button.btn-pay");

    // Незаполненные поля банковской карты
    private final By cardNumberLabel = By.xpath("//div[contains(@class, 'card-number')]//label|//label[contains(text(), 'Номер')]");
    private final By cardExpiryLabel = By.xpath("//div[contains(@class, 'expiration')]//label|//label[contains(text(), 'Срок') or contains(text(), 'ММ')]");
    private final By cardCvcLabel = By.xpath("//div[contains(@class, 'cvc')]//label|//label[contains(text(), 'CVC') or contains(text(), 'CVD')]");
    private final By cardHolderLabel = By.xpath("//div[contains(@class, 'card-holder')]//label|//label[contains(text(), 'Имя') or contains(text(), 'Держатель')]");

    // Иконки платежных систем на форме карты
    private final By cardSystemLogos = By.xpath("//div[contains(@class, 'cards-brands')]//img | //ul[contains(@class, 'payment-systems')]//li");

    public MtsPaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Увеличено время ожидания для загрузки фрейма
    }

    @Step("Переключить контекст на фрейм оплаты")
    public void switchToPaymentIframe() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(paymentIframe));
        driver.switchTo().frame(iframe);
    }

    @Step("Вернуть контекст на основную страницу")
    public void switchToDefaultContext() {
        driver.switchTo().defaultContent();
    }

    @Step("Получить сумму платежа из фрейма")
    public String getPaymentAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentAmountText)).getText();
    }

    @Step("Получить номер телефона из фрейма")
    public String getPaymentPhoneNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentPhoneNumberText)).getText();
    }

    @Step("Получить текст кнопки подтверждения оплаты")
    public String getSubmitButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(submitButtonWithAmount)).getText();
    }

    public String getCardFieldLabelText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }

    public List<WebElement> getPaymentLogos() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cardSystemLogos));
    }

    // Геттеры локаторов полей карты
    public By getCardNumberLabel() { return cardNumberLabel; }
    public By getCardExpiryLabel() { return cardExpiryLabel; }
    public By getCardCvcLabel() { return cardCvcLabel; }
    public By getCardHolderLabel() { return cardHolderLabel; }
}

