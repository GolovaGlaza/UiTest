package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CartPage extends AbsBasePage {

    public CartPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "cartur")
    WebElement cartButton;

    @FindBy(xpath = "//tbody[@id='tbodyid']/tr[1]/td[3]")
    WebElement samsungPrice;

    @FindBy(xpath = "//tbody[@id='tbodyid']/tr[2]/td[3]")
    WebElement appleMonitorPrice;

    @FindBy(xpath = "//tbody[@id='tbodyid']/tr[3]/td[3]")
    WebElement sonyVaioPrice;

    @FindBy(id = "totalp")
    WebElement totalPrice;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    WebElement placeOrderButton;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "country")
    WebElement countryField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "card")
    WebElement creditCardField;

    @FindBy(id = "month")
    WebElement monthField;

    @FindBy(id = "year")
    WebElement yearField;

    @FindBy(xpath = "//button[@onclick='purchaseOrder()']")
    WebElement purchaseButton;

    @FindBy(xpath = "//p[@class='lead text-muted ']")
    WebElement confirmationDetails;

    @Step("Переходим в корзину и проверяем, что общая цена посчитана верно")
    public void CheckTotalPriceInCart(){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();

        //waiter.waitForCondition(ExpectedConditions.visibilityOf(productsTable));
        waiter.waitForCondition(ExpectedConditions.visibilityOf(samsungPrice));
        String phonePriceOnTable = samsungPrice.getText().replaceAll("[^0-9]", "");

        waiter.waitForCondition(ExpectedConditions.visibilityOf(appleMonitorPrice));
        String monitorPriceOnTable = appleMonitorPrice.getText().replaceAll("[^0-9]", "");

        waiter.waitForCondition(ExpectedConditions.visibilityOf(sonyVaioPrice));
        String laptopPriceOnTable = sonyVaioPrice.getText().replaceAll("[^0-9]", "");

        double expectedTotalPrice = Double.parseDouble(phonePriceOnTable)
                + Double.parseDouble(monitorPriceOnTable)
                + Double.parseDouble(laptopPriceOnTable);

        double actualTotalPrice = Double.parseDouble(totalPrice.getText().replaceAll("[^0-9]", ""));

        Assertions.assertEquals(expectedTotalPrice, actualTotalPrice, "Цены не совпадают");
    }
    @Step("Оформляем заказ")
    public void PlaceOrder(String name, String country, String city, String card) {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);

        countryField.sendKeys(country);
        cityField.sendKeys(city);
        creditCardField.sendKeys(card);

        LocalDate currentDate = LocalDate.now();
        String currentMonth = String.valueOf(currentDate.getMonthValue());
        String currentYear = String.valueOf(currentDate.getYear());

        monthField.sendKeys(currentMonth);
        yearField.sendKeys(currentYear);


        monthField.sendKeys(currentMonth);
        yearField.sendKeys(currentYear);

        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(purchaseButton));
        purchaseButton.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(confirmationDetails));
    }

    @Step("Проверяем, что дата в конце заказа совпадает с датой в системе")
    public void CheckOrderDate() {
        String confirmationText = confirmationDetails.getText();

        String[] lines = confirmationText.split("\n");
        String orderDateLine = lines[lines.length - 1]; // Обычно дата в последней строке
        String orderDate = orderDateLine.replace("Date: ", "").trim(); // Убираем лишние слова

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Формат даты

        Assertions.assertEquals(currentDate.format(formatter), orderDate, "Дата заказа не совпадает с текущей");
    }
}
