package steps;

import cucumber.api.java.ru.И;
import redmine.managers.Context;
import redmine.managers.Manager;
import redmine.model.user.User;
import redmine.ui.pages.LoginPage;

import static redmine.ui.pages.helpers.Pages.getPage;

public class LoginSteps {

    @И("Открыт браузер на главной странице")
    public void openBrowser() {
        Manager.openPage("login");
    }

    @И("Авторизоваться пользователем {string}")
    public void authorizeBy(String userStashId) {
        User user = Context.get(userStashId, User.class);
        getPage(LoginPage.class).login(
                user.getLogin(), user.getPassword()
        );
    }

}
