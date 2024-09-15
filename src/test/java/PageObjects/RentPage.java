package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RentPage {
    //WebDriver
    private final WebDriver driver;
    //Заголовок страницы "Про аренду"
    private final By rentHeading = By.xpath(".//div[@class='Order_Header__BZXOb']");
    //Поле "Когда привезти самокат"
    private final By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Выбор даты в выпадающем календаре
    private final By chooseDate = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");
    //Поле "Срок аренды"
    private final By rentTime = By.xpath(".//div[@class='Dropdown-root']");
    //Выбор количества суток аренды в выпадающем списке
    private final By chooseRentTime = By.xpath(".//div[@class='Dropdown-option']");
    //Поле с выбором цвета самоката(чекбокс)
    private final By scooterColor = By.xpath(".//label[contains(@class, '3wxSf')]");
    //Поле "Комментарий для курьера"
    private final By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private final By orderButton = By.xpath(".//div[contains(@class, '1xGrp')]/button[text()='Заказать']");
    //Кнопка "Да" в окне "Хотите оформить заказ"
    private final By yesButton = By.xpath(".//button[text()='Да']");
    //Заголовок окна "Заказ оформлен"
    private final By orderPlacedWindow = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    //Конструктор
    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод лжидания прогрузки старницы
    public void waitForLoadRentPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(rentHeading));
    }

    //Метод ожидания прогрузки элемента страницы
    private void ElementLoadOnRentPage(By elementToLoad) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(elementToLoad)));
    }

    //Метод для клика по элементу из выпадающих списков
    private void chooseFromDropdownList(By dropdownElements, String elementToChoose) {
        List<WebElement> elementsFiltered = driver.findElements(dropdownElements);
        for (WebElement element : elementsFiltered) {
            if (element.getText().equals(elementToChoose)) {
                element.click();
                break;
            }
        }
    }

    //Метод для клика по выбранной дате
    private void clickSelectedDate() {
        driver.findElement(chooseDate).click();
    }

    //Метод для заполнения поля "Когда привезти самокат"
    public void setWhenToDeliverScooter(String date) {
        driver.findElement(deliveryDateField).sendKeys(date);
        ElementLoadOnRentPage(chooseDate);
        clickSelectedDate();
    }

    //Метод для раскрытия выпадающего списка сроков аренды
    private void clickRentTime() {
        driver.findElement(rentTime).click();
    }

    //Метод для заполнения поля "Срок аренды"
    public void setRentTime(String days) {
        clickRentTime();
        chooseFromDropdownList(chooseRentTime, days);
    }

    //Метод для выбора цвета самоката
    public void chooseScooterColor(String color) {
        chooseFromDropdownList(scooterColor, color);
    }

    //Метод для заполнения поля "Комментарий для курьера"
    public void setCommentForCourier(String comment) {
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    //Метод для нажания по кнопке "Заказать"
    private void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Метод для нажатия по кнопке "Да"
    private void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    //Метод для оформления заказа
    public void placingAnOrder() {
        clickOrderButton();
        ElementLoadOnRentPage(yesButton);
        clickYesButton();
    }

    //Метод для получения текста об успешном формировании заказа
    public String getOrderPlacedWindow() {
        return driver.findElement(orderPlacedWindow).getText();
    }
}
