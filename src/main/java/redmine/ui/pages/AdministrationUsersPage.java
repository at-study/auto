package redmine.ui.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница "Пользователи" в меню "Администрирование"
 */

public class AdministrationUsersPage extends AbstractPage {

    @FindBy(xpath = "//table[@class='list users']//a[text()='Создано']")
    public WebElement sortByCreatedOn;

    @FindBy(xpath = "//tr[@class='user active']/td[@class='created_on']")
    public List<WebElement> createdOn;

}
