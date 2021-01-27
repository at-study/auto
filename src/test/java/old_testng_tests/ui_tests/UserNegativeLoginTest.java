package old_testng_tests.ui_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import redmine.managers.Manager;
import redmine.model.user.User;
import redmine.ui.pages.LoginPage;

import static redmine.ui.pages.helpers.Pages.getPage;

public class UserNegativeLoginTest {
    private User user;

    @BeforeMethod
    public void prepareFixtures() {
        user = new User().generate();

        //TODO должно реализовываться при генерации пользователя
        user.setLogin("qqqqqqqq");
        user.setPassword("qqqqqqqqqqqqqqqqqdgagewgwgsdgsvsvsd");

        Manager.openPage("login");
    }


    @Test(description = "Негативный логин (неправильный пароль)")
    @Description("Вход под учетной записью пользователя с неправильным паролем")
    public void userNegativeLoginTest() {

        getPage(LoginPage.class).login(user.getLogin(), user.getPassword());

        Assert.assertEquals(getPage(LoginPage.class).errorMessage(), "Неправильное имя пользователя или пароль");
    }

    @AfterMethod
    public void tearDown() {
        Manager.driverQuit();
    }
}
