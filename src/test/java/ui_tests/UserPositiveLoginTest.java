package ui_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import redmine.managers.Manager;
import redmine.model.user.User;
import redmine.ui.pages.HeaderPage;
import redmine.ui.pages.LoginPage;

import static redmine.ui.pages.Pages.getPage;

public class UserPositiveLoginTest {
    private User user;

    @BeforeMethod
    public void prepareFixtures() {
        user = new User().generate();

        //TODO должно реализовываться при генерации пользователя
        user.setLogin("tqtqyiru");
        user.setPassword("NMmDvSEMfm");

        Manager.openPage("login");
    }


    @Test
    public void userPositiveLoginTest() {

        new LoginPage().login(user.getLogin(), user.getPassword());

        Assert.assertEquals(getPage(HeaderPage.class).loggedAs(), "Вошли как " + user.getLogin());
    }

    @AfterMethod
    public void tearDown() {
        Manager.driverQuit();
    }
}
