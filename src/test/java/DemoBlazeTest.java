
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.LoginUpModal;
import pages.ProductsPage;
import pages.SignUpModal;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import utils.FakerDataGenerator;


public class DemoBlazeTest {

    private WebDriver driver;
    private SignUpModal signUpModal;
    private LoginUpModal loginUpModal;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private FakerDataGenerator fakerData;

    @BeforeEach
    void setUp() {
        driver = new WebDriverFactory().create();
        fakerData = new FakerDataGenerator();
        signUpModal = new SignUpModal(driver);
        loginUpModal = new LoginUpModal(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterEach
    void tearDown() {
        if(driver != null){
            driver.close();
            driver.quit();
        }
    }

    @Test
    @Description("Проверка процесса регистрации, логина, добавления товаров в корзину и оформления заказа")
    @Tag("DemoBlaze")
    @Tag("Allure")
    void test() {
        signUpModal.open("/");

        String username = fakerData.generateUsername();
        String password = fakerData.generatePassword();
        String name = fakerData.generateFullName();
        String country = fakerData.generateCountry();
        String city = fakerData.generateCity();
        String card = fakerData.generateCreditCard();


        signUpModal.SignUp(username, password);

        loginUpModal.LogIn(username, password);

        productsPage.AddPhoneToCartAndCheckPrice();
        productsPage.AddLaptopToCartAndCheckPrice();
        productsPage.AddMonitorToCartAndCheckPrice();

        cartPage.CheckTotalPriceInCart();
        cartPage.PlaceOrder(name, country, city, card);
        cartPage.CheckOrderDate();
    }
}
