package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BrowserInit {
    private static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    static {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

    public static WebDriver getWebdriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver;
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webdriver.set(driver);
        return driver;
    }

    public static synchronized void closeWebdriver() {
        if (webdriver.get() != null) {
            webdriver.get().quit();
            webdriver.remove();
        }
    }
}