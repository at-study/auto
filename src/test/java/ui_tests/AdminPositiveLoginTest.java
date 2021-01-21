package ui_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import redmine.managers.Manager;
import redmine.model.user.User;
import redmine.ui.pages.HeaderPage;
import redmine.ui.pages.LoginPage;

import static redmine.ui.pages.Pages.getPage;

public class AdminPositiveLoginTest {
    private User user;

    @DataProvider(name = "usersProvider")
    public static Object[][] usersProvider() {
        return new Object[][]{
                {"tqtqyiru", "NMmDvSEMfm"},
                {"admin", "admin123"},
        };
    }

    @BeforeMethod
    public void prepareFixtures(Object[] testArgs) {
        //TODO должно реализовываться при генерации пользователя
        String login = (String) testArgs[0];
        String password = (String) testArgs[1];

        user = new User().generate().setLogin(login).setPassword(password);

        Manager.openPage("login");
    }


    @Test(dataProvider = "usersProvider", description = "Позитивный логин")
    @Description("Вход под учетной записью пользователя")
    @Parameters(value = {"Логин", "Пароль"})
    public void adminPositiveLoginTest(String login, String password) {

        getPage(LoginPage.class).login(user.getLogin(), user.getPassword());

        Assert.assertEquals(getPage(HeaderPage.class).loggedAs(), "Вошли как " + user.getLogin());

    }

    @AfterMethod
    public void tearDown() {
        Manager.driverQuit();
    }

}
