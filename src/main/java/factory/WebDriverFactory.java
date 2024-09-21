package factory;

import exeptions.BrowserNotSupported;
import factory.impl.ChromeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private String browser = System.getProperty("browser");

    public WebDriver create() throws BrowserNotSupported {
        switch (browser){
            case "chrome":{
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            }
        }
        throw new BrowserNotSupported(browser);
    }
}
