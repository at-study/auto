package old_testng_tests.ui_tests;

import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import redmine.managers.Manager;
import redmine.model.user.User;
import redmine.ui.pages.HeaderPage;
import redmine.ui.pages.LoginPage;
import redmine.utils.BrowserUtils;
import redmine.utils.StringGenerators;

import static redmine.ui.pages.helpers.Pages.getPage;

public class UserVisibleElementsTest {

    private User user;

    @BeforeMethod
    public void prepareFixtures() {
        user = new User().generate();

        //TODO должно реализовываться при генерации пользователя
        user.setLogin("tqtqyiru");
        user.setPassword("NMmDvSEMfm");

        Manager.openPage("login");
    }


    @Test(description = "Проверка видимости элементов \"Администрирование\", \"Проекты\". Логин обычным пользователем")
    @Description("Проверка видимости элементов \"Администрирование\", \"Проекты\". Логин обычным пользователем")
    public void userPositiveLoginTest() throws IOException {

        getPage(LoginPage.class).login(user.getLogin(), user.getPassword());

        Assert.assertTrue(BrowserUtils.isElementCurrentlyPresent(
                getPage(HeaderPage.class).projects
        ));

        Assert.assertFalse(BrowserUtils.isElementCurrentlyPresent(
                getPage(HeaderPage.class).administration
        ));

        Manager.waiter().until(
                ExpectedConditions.elementToBeClickable(getPage(HeaderPage.class).projects)
        );

        byte[] screenshot = Manager.takeScreenshot();
        FileOutputStream stream = new FileOutputStream("target\\" + StringGenerators.randomEnglishLowerString(8) + ".png");
        stream.write(screenshot);
        stream.flush();
        stream.close();

    }

    @AfterMethod
    public void tearDown() {
        Manager.driverQuit();
    }
}
