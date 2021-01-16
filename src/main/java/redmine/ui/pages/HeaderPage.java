package redmine.ui.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Заголовок страницы
 */

public class HeaderPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='top-menu']/ul//a")
    public List<WebElement> headerElements;

    @FindBy(xpath = "//div[@id='quick-search']/form/input[@type='text']")
    public WebElement searchInput;

    @FindBy(xpath = "//a[@class='home']")
    private WebElement home;

    @FindBy(xpath = "//a[@class='projects']")
    public WebElement projects;

    @FindBy(xpath = "//a[@class='administration']")
    public WebElement administration;

    @FindBy(xpath = "//div[@id='loggedas']")
    private WebElement loggedAs;


    public String loggedAs() {
        return loggedAs.getText();
    }
}
