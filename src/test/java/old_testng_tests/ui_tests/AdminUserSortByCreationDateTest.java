package old_testng_tests.ui_tests;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import redmine.managers.Manager;
import redmine.model.user.User;
import redmine.ui.pages.AdministrationUsersPage;
import redmine.ui.pages.LoginPage;
import redmine.utils.Asserts;

import static redmine.ui.pages.helpers.Pages.getPage;

public class AdminUserSortByCreationDateTest {

    private User user;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final Comparator<String> DATE_COMPARATOR_ASC = (s1, s2) -> {
        LocalDateTime date1 = LocalDateTime.parse(s1, FORMATTER);
        LocalDateTime date2 = LocalDateTime.parse(s2, FORMATTER);
        return date1.compareTo(date2);
    };

    @BeforeMethod
    public void prepare() {
        user = new User().generate();

        Manager.openPage("users");

        getPage(LoginPage.class).login(user.getLogin(), user.getPassword());
    }

    @Test
    public void administrationUserPageCreatedOnSortingTest() {

        assertThatCreatedOnNotSorted();

        getPage(AdministrationUsersPage.class).sortByCreatedOn.click();
        getPage(AdministrationUsersPage.class).sortByCreatedOn.click();

        assertThatCreatedOnSorted();

    }

    @Step("Проверка, что таблица НЕ отсортирована по дате создания")
    private void assertThatCreatedOnNotSorted() {
        List<String> actualNotSortedList = getPage(AdministrationUsersPage.class).createdOn
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> notExpectedSortedList = actualNotSortedList.stream()
                .sorted(DATE_COMPARATOR_ASC)
                .collect(Collectors.toList());

        Asserts.assertNotEquals(actualNotSortedList, notExpectedSortedList);
    }

    @Step("Проверка, что таблица отсортирована по дате создания")
    private void assertThatCreatedOnSorted() {
        List<String> actualCreatedOnList = getPage(AdministrationUsersPage.class).createdOn
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> expectedCreatedOnList = actualCreatedOnList.stream()
                .sorted(DATE_COMPARATOR_ASC)
                .collect(Collectors.toList());

        Asserts.assertEquals(actualCreatedOnList, expectedCreatedOnList);
    }

}
