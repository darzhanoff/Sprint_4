import PageObjects.HomePage;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class HomePageIncorectOrderStatusTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private WebDriver driver;

    @Test
    public void testOrderNotFoundMessage() {
        driver = driverRule.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);
        boolean isMessageDisplayed = homePage.checkOrderStatus("12345");
        assertTrue("Текст о ненайденном заказе не отображается", isMessageDisplayed);
    }
}
