package testng_explain.human;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testng_explain_model.Human;

public class HumanTests {
    Human human;

    @BeforeMethod
    public void prepareFixtures() {
        human = new Human(10, "Иван");
    }

    @Test
    public void testPositiveHumanAge() {
        human.setAge(15);

        Assert.assertEquals(human.getAge(), 15, "Валидация возраста");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "^Возраст не может быть отрицательным$")
    public void testNegativeHumanAge() {
        human.setAge(-15);
    }
}
