package ru.aston;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By blockTitle = By.xpath("//div[@class='pay__wrapper']/h2");
    private final By paymentLogos = By.xpath("//div[@class='pay__partners']//img");
    private final By moreInfoLink = By.xpath("//div[@class='pay__wrapper']/a");
    // Точный ID кнопки "Принять" из вашего HTML
    private final By cookieAcceptButton = By.id("cookie-agree");
    // Класс самого контейнера баннера для отслеживания его исчезновения
    private final By cookieContainer = By.className("cookie");

    // Форма оплаты "Услуги связи"
    // Кнопка-стрелка для раскрытия выпадающего списка
    private final By servicesDropdownHeader = By.className("select__header");
    // Сам элемент "Услуги связи" внутри раскрывающегося списка
    private final By connectionServiceOption = By.xpath("//p[@class='select__option' and text()='Услуги связи']");
    private final By phoneNumberField = By.id("connection-phone");
    private final By sumField = By.id("connection-sum");
    private final By emailField = By.id("connection-email");
    private final By continueButton = By.xpath("//form[@id='pay-connection']//button[contains(text(),'Продолжить')]");


    public MtsMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            acceptBtn.click();

            // Ждем полного исчезновения баннера с экрана
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieContainer));
        } catch (Exception e) {
            System.out.println("Баннер Cookie не был обработан: " + e.getMessage());
        }
    }

    public String getBlockTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle)).getText();
    }

    public List<WebElement> getPaymentLogos() {
        return driver.findElements(paymentLogos);
    }

    public void clickMoreInfoLink() {
        wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink)).click();
    }

    public void selectConnectionServices() {
        // 1. Кликаем по шапке дропдауна, чтобы открыть список вариантов
        wait.until(ExpectedConditions.elementToBeClickable(servicesDropdownHeader)).click();

        // 2. Кликаем по пункту "Услуги связи"
        wait.until(ExpectedConditions.elementToBeClickable(connectionServiceOption)).click();
    }

    public void fillPaymentForm(String phone, String sum, String email) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberField));
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        WebElement sumInput = driver.findElement(sumField);
        sumInput.clear();
        sumInput.sendKeys(sum);

        WebElement emailInput = driver.findElement(emailField);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}
