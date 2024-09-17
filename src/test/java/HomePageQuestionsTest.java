import PageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.junit.runners.Parameterized;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class HomePageQuestionsTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    private final String homePageUrl = "https://qa-scooter.praktikum-services.ru";
    private final int numberOfElement;
    private final String expectedQuestionText;
    private final String expectedAnswerText;

    public HomePageQuestionsTest(int numberOfAccordionItem, String expectedQuestionText, String expectedAnswerText) {
        this.numberOfElement = numberOfAccordionItem;
        this.expectedQuestionText = expectedQuestionText;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters(name = "Текст в блоке\"Вопросы о важном\". Проверяемый элемент: {1}")
    public static Object[][] setTestData() {
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." }
        };
    }
    @Test
    public void AccordionCheck() {
        WebDriver driver = driverRule.getDriver();
        driver.get(homePageUrl);
        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadPage();
        homePage.clickCookieButton();
        homePage.scrollToFooter();
        homePage.clickOnQuestions(this.numberOfElement);

        assertTrue("Ответ под вопросом не открылся: " + this.numberOfElement,
                homePage.checkAnswersOpenedUp(this.numberOfElement));
        assertEquals("Проблемы с текстом в Вопросах аккордеона: " + this.numberOfElement,
                this.expectedQuestionText, homePage.getQuestionsText(this.numberOfElement));
        assertEquals("Проблемы с текстом в Ответах аккордеона: " + this.numberOfElement,
                this.expectedAnswerText, homePage.getAnswersText(this.numberOfElement));
    }
}