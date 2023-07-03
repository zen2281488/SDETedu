package UITests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.WayAutorisation;
import utils.BrowserInit;
import utils.ConfProperties;

@Epic("Тесты Авторизации.")
@Feature("Задание U3 тест авторизации с использованием DataProvider")
public class DataProviderAuthTest {
    private WebDriver browser;
    private WayAutorisation wayAutorisation;

    @BeforeMethod
    @Step("Инициализация браузера")
    public void setUp() {
        browser = BrowserInit.getWebdriver();
        wayAutorisation = new WayAutorisation(browser);
    }

    @Test(dataProvider = "loginData", dataProviderClass = utils.DataProviderUserData.class)
    public void testLogin(String username, String password) {
        browser.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername(username).sendPassword(password).sendUserDescription(password).clickSubmitButton();
        Assert.assertTrue("Авторизация не прошла. Проверьте корректность введенных данных.", wayAutorisation.waitSuccessLoginText());
    }

    @AfterMethod
    @Step("Очиска данных.")
    public void clear() {
        browser.quit();
    }
}