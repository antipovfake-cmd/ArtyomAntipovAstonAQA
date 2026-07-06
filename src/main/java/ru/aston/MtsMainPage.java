package ru.aston;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MtsMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Баннеры и общие элементы
    private final By cookieAcceptButton = By.id("cookie-agree");
    private final By cookieContainer = By.className("cookie");

    // Элементы выпадающего списка выбора услуг
    private final By servicesDropdownHeader = By.className("select__header");
    private final By connectionServiceOption = By.xpath("//p[@class='select__option' and text()='Услуги связи']");
    private final By homeInternetOption = By.xpath("//p[@class='select__option' and text()='Домашний интернет']");
    private final By installmentOption = By.xpath("//p[@class='select__option' and text()='Рассрочка']");
    private final By debtOption = By.xpath("//p[@class='select__option' and text()='Задолженность']");

    // Поля формы "Услуги связи"
    private final By phoneNumberField = By.id("connection-phone");
    private final By connectionSumField = By.id("connection-sum");
    private final By connectionEmailField = By.id("connection-email");
    private final By connectionContinueButton = By.xpath("//form[@id='pay-connection']//button[contains(text(),'Продолжить')]");

    // Поля формы "Домашний интернет"
    private final By internetAccountField = By.id("internet-phone");
    private final By internetSumField = By.id("internet-sum");
    private final By internetEmailField = By.id("internet-email");

    // Поля формы "Рассрочка"
    private final By installmentAccountField = By.id("score-instalment");
    private final By installmentSumField = By.id("instalment-sum");
    private final By installmentEmailField = By.id("instalment-email");

    // Поля формы "Задолженность"
    private final By debtAccountField = By.id("score-instalment");
    private final By debtSumField = By.id("instalment-sum");
    private final By debtEmailField = By.id("instalment-email");



    public MtsMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Принять куки, если баннер присутствует")
    public void acceptCookiesIfPresent() {
        try {
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            acceptBtn.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieContainer));
        } catch (Exception e) {
            System.out.println("Баннер Cookie отсутствует или уже закрыт.");
        }
    }

    private void openDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(servicesDropdownHeader)).click();
    }

    @Step("Выбрать услугу 'Услуги связи'")
    public void selectConnectionServices() {
        openDropdown();
        safeClick(connectionServiceOption);
    }

    public void selectHomeInternet() {
        openDropdown();
        safeClick(homeInternetOption);
    }

    @Step("Выбрать услугу 'Рассрочка'")
    public void selectInstallment() {
        openDropdown();
        safeClick(installmentOption);
    }

    @Step("Выбрать услугу 'Задолженность'")
    public void selectDebt() {
        openDropdown();
        safeClick(debtOption);
    }

    // Методы получения плейсхолдеров (надписей в незаполненных полях)
    @Step("Получить плейсхолдер поля")
    public String getPlaceholder(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute("placeholder");
    }

    @Step("Заполнить форму 'Услуги связи': телефон {phone}, сумма {sum}, email {email}")
    public void fillConnectionForm(String phone, String sum, String email) {
        setInputText(phoneNumberField, phone);
        setInputText(connectionSumField, sum);
        setInputText(connectionEmailField, email);
    }

    @Step("Нажать кнопку 'Продолжить'")
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(connectionContinueButton)).click();
    }

    private void setInputText(By locator, String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        input.clear();
        input.sendKeys(text);
    }

    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (Exception e) {
            // Если обычный клик перехвачен анимацией, кликаем напрямую через JavaScript Executor
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    // Геттеры локаторов для тестов
    public By getPhoneNumberField() { return phoneNumberField; }
    public By getConnectionSumField() { return connectionSumField; }
    public By getConnectionEmailField() { return connectionEmailField; }
    public By getInternetAccountField() { return internetAccountField; }
    public By getInternetSumField() { return internetSumField; }
    public By getInternetEmailField() { return internetEmailField; }
    public By getInstallmentAccountField() { return installmentAccountField; }
    public By getInstallmentSumField() { return installmentSumField; }
    public By getInstallmentEmailField() { return installmentEmailField; }
    public By getDebtAccountField() { return debtAccountField; }
    public By getDebtSumField() { return debtSumField; }
    public By getDebtEmailField() { return debtEmailField; }
}
