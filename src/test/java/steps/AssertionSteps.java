package steps;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import redmine.managers.Context;
import redmine.ui.pages.helpers.CucumberPageObjectHelper;
import redmine.utils.BrowserUtils;

public class AssertionSteps {

    @И("Значение переменной {string} равно {int}")
    public void assertResult(String stashId, Integer expectedResult) {
        Integer result = Context.getStash().get(stashId, Integer.class);
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

}
