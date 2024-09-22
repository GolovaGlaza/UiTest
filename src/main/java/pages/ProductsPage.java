package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends AbsBasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Phones')]")
    WebElement phonesCategoryButton;

    @FindBy(xpath = "//a[contains(text(),'Laptops')]")
    WebElement laptopsCategoryButton;

    @FindBy(xpath = "//a[contains(text(),'Monitors')]")
    WebElement monitorsCategoryButton;

    @FindBy(xpath = "//a[contains(text(),'Samsung galaxy s6')]")
    WebElement samsungGalaxy6Card;

    @FindBy(xpath = "//a[contains(text(),'Sony vaio i5')]")
    WebElement sonyVaioI5Card;

    @FindBy(xpath = "//a[contains(text(),'Apple monitor 24')]")
    WebElement appleMonitor23Card;

    @FindBy(xpath = "//a[@onclick='addToCart(1)']")
    WebElement addPhoneToCartButton;

    @FindBy(xpath = "//a[@onclick='addToCart(8)']")
    WebElement addLaptopToCartButton;

    @FindBy(xpath = "//a[@onclick='addToCart(10)']")
    WebElement addMonitorToCartButton;

    @FindBy(xpath = "//a[contains(text(),'Home')]")
    WebElement homeButton;

    @FindBy(xpath = "//a[contains(text(),'Samsung galaxy s6')]/following::h5[1]")
    WebElement samsungPriceInList;

    @FindBy(xpath = "//h3[@class='price-container']")
    WebElement samsungPriceInCard;

    @FindBy(xpath = "//a[contains(text(),'Sony vaio i5')]/following::h5[1]")
    WebElement sonyVaioPriceInList;

    @FindBy(xpath = "//h3[@class='price-container']")
    WebElement sonyVaioPriceInCard;

    @FindBy(xpath = "//a[contains(text(),'Apple monitor 24')]/following::h5[1]")
    WebElement appleMonitorPriceInList;

    @FindBy(xpath = "//h3[@class='price-container']")
    WebElement appleMonitorPriceInCard;

    @Step("Добавляем телефон в корзину и проверяем цену:")
    public void AddPhoneToCartAndCheckPrice() {
        for (int i = 0; i < 3; i++) {
            try {
                String priceInList = getPhonePriceInList();
                String priceInCard = getPhonePriceInCard();
                checkPrice(priceInList, priceInCard);

                addPhoneToCart();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Поймано исключение StaleElementReferenceException. Повторяем попытку...");
            }
        }
    }

    private String getPhonePriceInList() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(phonesCategoryButton));
        phonesCategoryButton.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(samsungPriceInList));
        return samsungPriceInList.getText().replaceAll("[^0-9]", "");
    }

    private String getPhonePriceInCard() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(samsungGalaxy6Card));
        samsungGalaxy6Card.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(samsungPriceInCard));
        return samsungPriceInCard.getText().replaceAll("[^0-9]", "");
    }

    private void checkPrice(String priceInList, String priceInCard) {
        Assertions.assertEquals(priceInList, priceInCard, "Цена на карточке товара не соответствует общей цене в корзине");
        logPriceCheck(priceInList, priceInCard);
    }

    @Step("Проверка цен: цена в списке {priceInList}, цена в корзине {priceInCard}")
    private void logPriceCheck(String priceInList, String priceInCard) {
        Allure.addAttachment("Проверка общей цены",
                String.format("Ожидаемая цена: %s, Фактическая цена: %s", priceInList, priceInCard));
    }

    private void addPhoneToCart() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addPhoneToCartButton));
        addPhoneToCartButton.click();

        waiter.waitForCondition(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(homeButton));
        homeButton.click();
    }

    @Step("Добавляем ноутбук в корзину и проверяем цену")
    public void AddLaptopToCartAndCheckPrice() {
        for (int i = 0; i < 3; i++) {
            try {
                String priceInList = getLaptopPriceInList();
                String priceInCard = getLaptopPriceInCard();
                checkPrice(priceInList, priceInCard);

                addLaptopToCart();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Поймано исключение StaleElementReferenceException. Повторяем попытку...");
            }
        }
    }

    private String getLaptopPriceInList() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(laptopsCategoryButton));
        laptopsCategoryButton.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(sonyVaioPriceInList));
        return sonyVaioPriceInList.getText().replaceAll("[^0-9]", "");
    }

    private String getLaptopPriceInCard() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(sonyVaioI5Card));
        sonyVaioI5Card.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(sonyVaioPriceInCard));
        return sonyVaioPriceInCard.getText().replaceAll("[^0-9]", "");
    }

    private void addLaptopToCart() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addLaptopToCartButton));
        addLaptopToCartButton.click();

        waiter.waitForCondition(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(homeButton));
        homeButton.click();
    }

    @Step("Добавляем монитор в корзину и проверяем цену")
    public void AddMonitorToCartAndCheckPrice() {
        for (int i = 0; i < 3; i++) {
            try {
                String priceInList = getMonitorPriceInList();
                String priceInCard = getMonitorPriceInCard();
                checkPrice(priceInList, priceInCard);

                addMonitorToCart();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Поймано исключение StaleElementReferenceException. Повторяем попытку...");
            }
        }
    }

    private String getMonitorPriceInList() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(monitorsCategoryButton));
        monitorsCategoryButton.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(appleMonitorPriceInList));
        return appleMonitorPriceInList.getText().replaceAll("[^0-9]", "");
    }

    private String getMonitorPriceInCard() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(appleMonitor23Card));
        appleMonitor23Card.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(appleMonitorPriceInCard));
        return appleMonitorPriceInCard.getText().replaceAll("[^0-9]", "");
    }

    private void addMonitorToCart() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addMonitorToCartButton));
        addMonitorToCartButton.click();

        waiter.waitForCondition(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(homeButton));
        homeButton.click();
    }
}