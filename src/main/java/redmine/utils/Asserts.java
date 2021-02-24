package redmine.utils;

import org.testng.Assert;

import io.qameta.allure.Step;

public class Asserts {

    @Step("Сравнение переменных на равенство actual: {0}, expected: {1}")
    public static void assertEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Сравнение переменных на неравенство actual: {0}, expected: {1}")
    public static void assertNotEquals(Object actual, Object expected) {
        Assert.assertNotEquals(actual, expected);
    }

    @Step("Сравнение на истину утверждения: {0}")
    public static void assertTrue(Boolean condition) {
        Assert.assertTrue(condition);
    }

    @Step("Сравнение на ложь утверждения: {0}")
    public static void assertFalse(Boolean condition) {
        Assert.assertFalse(condition);
    }
}
