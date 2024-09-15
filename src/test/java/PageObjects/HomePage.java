package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class HomePage {
    //WebDriver
    private final WebDriver driver;
    //Кнопка заказа в шапке
    private final By orderButtonUp = By.xpath(".//div[starts-with(@class, 'Header_Nav')]//button[contains(@class, 'Button_Button')]");
    //Кнопка заказа снизу
    private final By orderButtonDown = By.xpath(".//div[starts-with(@class, 'Home_FinishButton')]//button[contains(@class, 'Button_Button')]");
    //Логотип "Яндекс" в шапке
    private final By yandexLogo = By.xpath(".//a[@class='Header_LogoYandex__3TSOI']");
    //Логотип "Самокат" в шапке
    private final By scooterLogo = By.xpath(".//a[@class='Header_LogoScooter__3lsAR']");
    //Кнопка "Принять" в куки
    private final By cookie = By.xpath(".//button[@id='rcc-confirm-button']");
    //Заголовок "Самокат на пару дней"
    private final By upperHeading = By.xpath(".//div[@class='Home_Header__iJKdX']");
    //Заголовок "Вопросы о важном"
    private final By underHeading = By.xpath(".//div[starts-with(@class, 'Home_FourPart__1uthg')]//div[contains(@class, 'Home_SubHeader__zwi_E')]");
    //Вопросы в разделе "Вопросы о важном"
    private final By accordionHeading = By.xpath(".//div[@class='accordion__button']");
    //Ответы в разделе "Вопросы о важном"
    private final By accordionPanel = By.xpath(".//div[@class='accordion__panel']/p");
    //Кнопка "Статус заказа"
    private final By statusButton = By.xpath(".//button[@class='Header_Link__1TAG7']");
    //Поле ввода номера заказа
    private final By orderNumberEntryField = By.xpath(".//input[@placeholder='Введите номер заказа']");
    //Кнопка "Go!"
    private final By goButton = By.xpath(".//button[contains(@class, '28dPO')]");
    //Надпись "Такого заказа нет"
    private final By orderNotExistText = By.xpath(".//img[@alt='Not found']");

    //Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод ожидания прогрузки главной страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(upperHeading));
    }

    //Метод нажатия по кнопке "Принять" куки
    public void clickCookieButton() {
        driver.findElement(cookie).click();
    }

    //Метод скролла до раздела "Вопросы о важном"
    public void scrollToFooter() {
        WebElement element = driver.findElement(underHeading);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод скролла до кнопки "Заказать"
    public void scrollToOrderButton() {
        WebElement element = driver.findElement(orderButtonDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод для получения текста из раздела "Вопросы о важном" (Вопросы)
    public String getQuestionsText(int index) {
        return driver.findElements(accordionHeading).get(index).getText();
    }

    //Метод для получения текста из раздела "Вопросы о важном" (Ответы)
    public String getAnswersText(int index) {
        return driver.findElements(accordionPanel).get(index).getText();
    }

    //Метод клика по Вопросу в разделе "Вопросы о важном"
    public void clickOnQuestions(int index) {
        driver.findElements(accordionHeading).get(index).click();
    }

    //Метод проверки что Ответы в разделе "Вопросы о важном" раскрылись
    public boolean checkAnswersOpenedUp(int index) {
        return driver.findElements(accordionPanel).get(index).isDisplayed();
    }

    //Метод нажатия кнопки "Заказать" в шапке
    public void clickOnOrderButtonUp() {
        driver.findElement(orderButtonUp).click();
    }

    //Метод нажатия кнопки "Заказать" снизу
    public void clickOnOrderButtonDown() {
        driver.findElement(orderButtonDown).click();
    }

    //Метод нажатия по логотипу "Самокат" и проверка перехода на главную страницу
    public boolean scooterLogoRedirection() {
        driver.findElement(scooterLogo).click();
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("https://qa-scooter.praktikum-services.ru/");
    }

    //Метод нажатия по логотипу "Яндекс" и проверка открытия нового окна с главной страницей Яндекса
    public boolean yandexLogoLinkOpenedInNewTab() {
        String originalWindow = driver.getWindowHandle();
        driver.findElement(yandexLogo).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                String newWindowUrl = driver.getCurrentUrl();
                return newWindowUrl.equals("https://yandex.ru/");
            }
        }
        return false;
    }

    //Метод нажатия по кнопке "Статус заказа"
    public void clickStatusButton() {
        driver.findElement(statusButton).click();
    }

    //Метод нажатия по кнопке "Go!"
    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    //Метод ввода номера заказа
    public void enterOrderNumber(String number) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement orderNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberEntryField));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", orderNumber);  // Прокрутить страницу до элемента
        orderNumber.clear();  // Очистка поля на случай, если оно уже содержит текст
        orderNumber.sendKeys(number);
    }

    //Метод проверки наличия надписи "Такого заказа нет"
    public boolean isOrderNotFoundMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notFoundMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(orderNotExistText));
        return notFoundMessage.isDisplayed();
    }

    //Метод ввода не существующего номера заказа и проверка отображения на странице надписи "Такого заказа нет"
    public boolean checkOrderStatus(String incorrectOrderNumber) {
        clickStatusButton();
        enterOrderNumber(incorrectOrderNumber);
        clickGoButton();
        return isOrderNotFoundMessage();
    }
}
