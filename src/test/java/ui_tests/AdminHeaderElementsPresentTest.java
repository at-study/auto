package ui_tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import redmine.utils.BrowserUtils;

import static redmine.ui.pages.Pages.getPage;

public class AdminHeaderElementsPresentTest {

    private User user;

    @DataProvider(name = "usersProvider")
    public static Object[][] usersProvider() {
        return new Object[][]{
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


    @Test(dataProvider = "usersProvider", description = "Проверка элементов заголовка страницы. Вход администратором")
    @Description("Проверка элементов заголовка страницы. Вход администратором. Должны отображаться элементы \"Домашняя страница\", \"Моя страница\", \"Проекты\", \"Администрирование\", \"Помощь\"")
    @Parameters(value = {"Логин", "Пароль"})
    public void adminPositiveLoginTest(String login, String password) {

        getPage(LoginPage.class).login(user.getLogin(), user.getPassword());

        List<String> texts = getPage(HeaderPage.class).headerElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> expectedTexts = Arrays.asList("Домашняя страница", "Моя страница", "Проекты", "Администрирование", "Помощь");

        Assert.assertEquals(texts, expectedTexts);

        Assert.assertTrue(BrowserUtils.isElementCurrentlyPresent(getPage(HeaderPage.class).searchInput));

        new Actions(Manager.driver())
                .moveToElement(getPage(HeaderPage.class).searchInput)
                .click()
                .sendKeys("Поиск чего-то")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        Manager.js().executeScript("//Выполнить какой-то Javascript код", "Аргументы", "кода");
    }

    @AfterMethod
    public void tearDown() {
        Manager.driverQuit();
    }

}
