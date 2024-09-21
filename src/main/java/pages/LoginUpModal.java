package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginUpModal extends AbsBasePage {

    public LoginUpModal(WebDriver driver){
        super(driver);
    }


    @FindBy(id = "login2")
    WebElement loginUpButton;

    @FindBy(id = "loginusername")
    WebElement usernameField;

    @FindBy(id = "loginpassword")
    WebElement passwordField;

    @FindBy(xpath = "//button[@onclick='logIn()']")
    WebElement loginUpModalButton;

    @Step("Логинимся с именем пользователя: {username} и паролем: {password}")
    public void LogIn(String username, String password){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(loginUpButton));
        loginUpButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(usernameField));
        usernameField.sendKeys(username);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.sendKeys(password);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(loginUpModalButton));
        loginUpModalButton.click();

    }
}
