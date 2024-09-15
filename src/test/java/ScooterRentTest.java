import PageObjects.HomePage;
import PageObjects.RentPage;
import PageObjects.ScooterForWhomPage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;


@RunWith(Parameterized.class)
public class ScooterRentTest {
    private final String homePageUrl = "https://qa-scooter.praktikum-services.ru";
    private final String name, surname, address, station, telephone, deliveryDate, rentTime, scooterColor, comment;
    private final String succesfulOrderText = "Заказ оформлен";

    @Rule
    public DriverRule driverRule = new DriverRule();

    public ScooterRentTest(String name,
                           String surname,
                           String address,
                           String station,
                           String telephone,
                           String deliveryDate,
                           String rentTime,
                           String scooterColor,
                           String comment
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.telephone = telephone;
        this.deliveryDate = deliveryDate;
        this.rentTime = rentTime;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }
    @Parameterized.Parameters(name = "Оформление заказаю. Позитивный сценарий. ПОльзователь: {0} {1}")
    public static Object[][] setDataForOrder() {
        return new Object[][] {
                {"Аскар", "Курманбеков", "Усачева, 3,", "Черкизовская", "88008888080", "17.09.2024", "сутки", "серая безысходность", "Жду самокат"},
                {"Мадина", "Керимжанова", "Зубовский бульвар, 37", "Сокольники", "88008888088", "25.09.2024", "шестеро суток", "чёрный жемчуг", "Очень жду самокат"}
        };
    }
    //Тест для кнопки шапке
    @Test
    public void makeOrderwithButtonUp() {
        WebDriver driver = driverRule.getDriver();
        driver.get(homePageUrl);
        HomePage homePage = new HomePage(driver);
        RentPage rentPage = new RentPage(driver);
        ScooterForWhomPage scooterForWhomPage = new ScooterForWhomPage(driver);

        homePage.waitForLoadPage();
        homePage.clickCookieButton();
        homePage.clickOnOrderButtonUp();
        scooterForWhomPage.waitForLoadPage();
        scooterForWhomPage.setNameField(this.name);
        scooterForWhomPage.setSurnameField(this.surname);
        scooterForWhomPage.setAddressField(this.address);
        scooterForWhomPage.setMetroStationField(this.station);
        scooterForWhomPage.setTelephoneField(this.telephone);
        scooterForWhomPage.clickNextButton();
        rentPage.waitForLoadRentPage();
        rentPage.setWhenToDeliverScooter(this.deliveryDate);
        rentPage.setRentTime(this.rentTime);
        rentPage.chooseScooterColor(this.scooterColor);
        rentPage.setCommentForCourier(this.comment);
        rentPage.placingAnOrder();

        String succesMessage = rentPage.getOrderPlacedWindow();
        assert succesMessage.contains(succesfulOrderText) : "Заказ не сформирован";
    }

    //Тест для кнопки посередине сайта
    @Test
    public void makeOrderwithButtonDown() {
        WebDriver driver = driverRule.getDriver();
        driver.get(homePageUrl);
        HomePage homePage = new HomePage(driver);
        RentPage rentPage = new RentPage(driver);
        ScooterForWhomPage scooterForWhomPage = new ScooterForWhomPage(driver);

        homePage.waitForLoadPage();
        homePage.clickCookieButton();
        homePage.scrollToOrderButton();
        homePage.clickOnOrderButtonDown();
        scooterForWhomPage.waitForLoadPage();
        scooterForWhomPage.setNameField(this.name);
        scooterForWhomPage.setSurnameField(this.surname);
        scooterForWhomPage.setAddressField(this.address);
        scooterForWhomPage.setMetroStationField(this.station);
        scooterForWhomPage.setTelephoneField(this.telephone);
        scooterForWhomPage.clickNextButton();
        rentPage.waitForLoadRentPage();
        rentPage.setWhenToDeliverScooter(this.deliveryDate);
        rentPage.setRentTime(this.rentTime);
        rentPage.chooseScooterColor(this.scooterColor);
        rentPage.setCommentForCourier(this.comment);
        rentPage.placingAnOrder();

        String succesMessage = rentPage.getOrderPlacedWindow();
        assert succesMessage.contains(succesfulOrderText) : "Заказ не сформирован";
    }
}