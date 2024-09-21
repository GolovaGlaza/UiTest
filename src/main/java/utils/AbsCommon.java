package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import utils.waiters.Waiter;

public abstract class AbsCommon {

    protected WebDriver driver;
    protected Actions actions;
    protected Waiter waiter;


    public AbsCommon(WebDriver driver){
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiter = new Waiter(driver);

        PageFactory.initElements(driver, this);
    }
}
