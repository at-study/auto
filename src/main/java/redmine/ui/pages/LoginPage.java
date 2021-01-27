package redmine.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
import redmine.managers.Manager;
import redmine.ui.pages.helpers.CucumberName;

/**
 * Страница входа
 */

@CucumberName("Вход в систему")
public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginElement;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordElement;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id = 'flash_error']")
    private WebElement flashError;

    @Step("Вход в систему Redmine с логином {0} и паролем {1}")
    public void login(String login, String password) {
        loginElement.sendKeys(login);
        passwordElement.sendKeys(password);
        submitButton.click();
        Manager.takeScreenshot();
    }

    public String errorMessage() {
        return flashError.getText();
    }
}
