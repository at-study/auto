package steps;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import redmine.cucumber.ParametersValidator;
import redmine.managers.Context;
import redmine.model.role.IssuesVisibility;
import redmine.model.role.Role;
import redmine.model.role.RolePermissions;
import redmine.model.role.TimeEntriesVisibility;
import redmine.model.role.UsersVisibility;
import redmine.ui.pages.helpers.CucumberPageObjectHelper;
import redmine.utils.Asserts;
import redmine.utils.BrowserUtils;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class AssertionSteps {

    @И("Значение переменной {string} равно {int}")
    public void assertResult(String stashId, Integer expectedResult) {
        Integer result = Context.get(stashId, Integer.class);
        Assert.assertEquals(result, expectedResult);
    }

    @То("На главной странице отображается поле {string}")
    public void assertProjectElementIsDisplayed(String fieldName) {
        Assert.assertTrue(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy("Заголовок", fieldName))
        );
    }

    @То("На главной странице не отображается поле {string}")
    public void assertProjectElementIsNotDisplayed(String fieldName) {
        Assert.assertFalse(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy("Заголовок", fieldName))
        );
    }

    @И("На странице {string} отображается элемент {string}")
    public void assertFieldIsDisplayed(String pageName, String fieldName) {
        WebElement element = CucumberPageObjectHelper.getElementBy(pageName, fieldName);
        Assert.assertTrue(
                BrowserUtils.isElementCurrentlyPresent(element)
        );
    }

    @И("На странице {string} не отображается элемент {string}")
    public void assertFieldIsNotDisplayed(String pageName, String fieldName) {
        WebElement element = CucumberPageObjectHelper.getElementBy(pageName, fieldName);
        Assert.assertFalse(
                BrowserUtils.isElementCurrentlyPresent(element)
        );
    }


    @То("Роль {string} имеет параметры:")
    public void assertRoleParameters(String roleStashId, Map<String, String> parameters) {
        ParametersValidator.validateRoleParameters(parameters);
        Role role = Context.get(roleStashId, Role.class);

        if (parameters.containsKey("Позиция")) {
            Asserts.assertEquals(
                    role.getPosition(),
                    parseInt(parameters.get("Позиция"))
            );
        }
        if (parameters.containsKey("Встроенная")) {
            Asserts.assertEquals(
                    role.getBuiltin(),
                    parseInt(parameters.get("Встроенная"))
            );
        }
        if (parameters.containsKey("Задача может быть назначена этой роли")) {
            Asserts.assertEquals(
                    role.getAssignable(),
                    parseBoolean(parameters.get("Задача может быть назначена этой роли"))
            );
        }
        if (parameters.containsKey("Видимость задач")) {
            Asserts.assertEquals(
                    role.getIssuesVisibility(),
                    IssuesVisibility.of(parameters.get("Видимость задач"))
            );
        }
        if (parameters.containsKey("Видимость пользователей")) {
            Asserts.assertEquals(
                    role.getUsersVisibility(),
                    UsersVisibility.of(parameters.get("Видимость пользователей"))
            );
        }
        if (parameters.containsKey("Видимость трудозатрат")) {
            Asserts.assertEquals(
                    role.getTimeEntriesVisibility(),
                    TimeEntriesVisibility.of(parameters.get("Видимость трудозатрат"))
            );
        }
        if (parameters.containsKey("Права")) {
            RolePermissions expectedPermissions = Context.get(parameters.get("Права"), RolePermissions.class);
            RolePermissions actualPermissions = role.getPermissions();
            Asserts.assertEquals(actualPermissions, expectedPermissions);
        }

    }
}
