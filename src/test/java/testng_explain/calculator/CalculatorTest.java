package testng_explain.calculator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng_explain_model.Calculator;

public class CalculatorTest {

    @DataProvider(name = "positiveProvider")
    public static Object[][] positiveProvider() {
        return new Object[][]{
                {6, 2, 3},
                {8, 2, 4},
                {6, 1, 6},
                {0, 9, 0}
        };
    }

    @DataProvider(name = "negativeProvider", parallel = true)
    public static Object[][] negativeProvider() {
        return new Object[][]{
                {6, 0},
                {0, 0}
        };
    }


    @Test(dataProvider = "positiveProvider")
    public void positiveCalculationTest(int number1, int number2, int expectedResult) {
        int actualResult = Calculator.divide(number1, number2);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dataProvider = "negativeProvider", expectedExceptions = {ArithmeticException.class})
    public void negativeCalculationTest(int number1, int number2) {
        Calculator.divide(number1, number2);
    }

}
