package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ScooterForWhomPage {
    //WebDriver
    private final WebDriver driver;
    //Заголовок страницы "Для кого самокат"
    private final By heading = By.xpath(".//div[contains(@class, 'BZXOb')]");
    //Поле "Имя"
    private final By nameField = By.xpath(".//input[@placeholder ='* Имя']");
    //Поле "Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder ='* Фамилия']");
    //Поле "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder ='* Адрес: куда привезти заказ']");
    //Поле "Станция метро"
    private final By metroStationField = By.xpath(".//input[@placeholder ='* Станция метро']");
    //Окно выпадающего списка
    private final By metroStationDropDownList = By.xpath(".//div[@class ='select-search__select']");
    //Список со станциями
    private final By metroStation = By.xpath(".//button[starts-with(@class, 'Order_SelectOption')]");
    //Поле "Телефон"
    private final By telephoneField = By.xpath(".//input[@placeholder ='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[contains(@class, '1CSJM')]");

    //Конструктор
    public ScooterForWhomPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод ожидания прогрузки страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(heading));
    }

    //Метод ожидания прогрузки элемента страницы(выпадающий список)
    private void ElementLoadOnScooterPage(By elementToLoad) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(elementToLoad)));
    }

    //Метод для клика по элементу из выпадающих списков
    private void chooseElementFromDropdownList(By dropdownElements, String elementToChoose) {
        List<WebElement> elementsFiltered = driver.findElements(dropdownElements);
        for (WebElement element : elementsFiltered) {
            if (element.getText().equals(elementToChoose)) {
                element.click();
                break;
            }
        }
    }

    //Метод для заполнения поля "Имя"
    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //Метод для заполнения поля "Фамилия"
    public void setSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    //Метод для заполнения поля "Адресс"
    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //Метод для заполнения поля "Станция метро"
    public void setMetroStationField(String metro) {
        driver.findElement(metroStationField).sendKeys(metro);
        ElementLoadOnScooterPage(metroStationDropDownList);
        chooseElementFromDropdownList(metroStation, metro);
    }

    //Метод для заполнения поля "Телефон"
    public void setTelephoneField(String number) {
        driver.findElement(telephoneField).sendKeys(number);
    }

    //Метод клика по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}
