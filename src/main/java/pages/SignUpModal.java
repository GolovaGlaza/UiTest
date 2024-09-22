package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
public class SignUpModal extends AbsBasePage {

    public SignUpModal(WebDriver driver){
        super(driver);
    }



    @FindBy(id = "signin2")
    WebElement signUpButton;

    @FindBy(id = "sign-username")
    WebElement usernameField;

    @FindBy(id = "sign-password")
    WebElement passwordField;

    @FindBy(xpath = "//div[@class='modal fade show']/div/div")
    WebElement signUpModalContainer;

    @FindBy(xpath = "//div[@id='signInModal']/div/div/div[3]/button[2]")
    WebElement signUpModalButton;

    @Step("Регистрация с именем пользователя: {username} и паролем: {password}")
    public void SignUp(String username, String password){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(signUpButton));
        signUpButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(signUpModalContainer));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(signUpModalButton));
        signUpModalButton.click();
        waiter.waitForCondition(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}

