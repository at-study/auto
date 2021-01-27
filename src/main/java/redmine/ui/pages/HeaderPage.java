package redmine.ui.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import redmine.ui.pages.helpers.CucumberName;

/**
 * Заголовок страницы
 */

@CucumberName("Заголовок")
public class HeaderPage extends AbstractPage {

    @CucumberName("Элементы заголовка")
    @FindBy(xpath = "//div[@id='top-menu']/ul//a")
    public List<WebElement> headerElements;

    @CucumberName("Строка поиска")
    @FindBy(xpath = "//div[@id='quick-search']/form/input[@type='text']")
    public WebElement searchInput;

    @CucumberName("Домашняя страница")
    @FindBy(xpath = "//a[@class='home']")
    private WebElement home;

    @CucumberName("Проекты")
    @FindBy(xpath = "//a[@class='projects']")
    public WebElement projects;

    @CucumberName("Администрирование")
    @FindBy(xpath = "//a[@class='administration']")
    public WebElement administration;

    @CucumberName("Вошли как")
    @FindBy(xpath = "//div[@id='loggedas']")
    private WebElement loggedAs;


    public String loggedAs() {
        return loggedAs.getText();
    }
}
