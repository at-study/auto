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

}
