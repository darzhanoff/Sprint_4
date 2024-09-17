import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import PageObjects.HomePage;
import static org.junit.Assert.assertTrue;

public class HomePageLogoTest {
    private final String HomePageUrl = "https://qa-scooter.praktikum-services.ru";

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void yandexLinkCheck() {
        WebDriver driver = driverRule.getDriver();
        driver.get(HomePageUrl);
        HomePage homePage = new HomePage(driver);

        homePage.clickCookieButton();
        assertTrue("Логотип Яндекс не переходит по ожидаемому URL",
                homePage.yandexLogoLinkOpenedInNewTab());
    }

    @Test
    public void scooterLinkCheck() {
        WebDriver driver = driverRule.getDriver();
        driver.get(HomePageUrl);
        HomePage homePage = new HomePage(driver);

        homePage.clickCookieButton();
        assertTrue("Логотип Самокат не переходит по ожидаемому URL",
                homePage.scooterLogoRedirection()
        );
    }
}
