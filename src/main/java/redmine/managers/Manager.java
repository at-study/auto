package redmine.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import redmine.Property;
import redmine.db.DataBaseConnection;

/**
 * Менеджер для хранения сущностей, необходимых для взаимодействия с тестируемой системой
 */

public class Manager {
    public final static DataBaseConnection dbConnection = new DataBaseConnection();
    //TODO изменить на ThreadLocal когда будет многопточность
    private static WebDriver driver;

    /**
     * Получить экземпляр драйвера (ленивая инициализация)
     *
     * @return драйвер
     */

    public static WebDriver driver() {
        if (driver == null) {
            driver = getPropertyDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Закрыть браузер, очистить драйвер.
     */

    public static void driverQuit() {
        driver.quit();
        driver = null;
    }

    /**
     * Открыть страницу Redmine
     */
    public static void openPage(String uri) {
        driver().get(Property.getStringProperty("ui.url") + uri);
    }

    /**
     * Возвращает экземпляр драйвера в зависимости от значения в properties-файле
     *
     * @return драйвер
     */
    private static WebDriver getPropertyDriver() {
        switch (Property.getStringProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", Property.getStringProperty("webdriver.chrome.driver"));
                return new ChromeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", Property.getStringProperty("webdriver.gecko.driver"));
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Неизвестный тип браузера");
        }
    }
}
