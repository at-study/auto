package testng_explain;

import org.testng.annotations.Test;

public class MyFirstTest extends AbstractTest {


    @Test(testName = "Первый тест", description = "Имя нашего первого теста", enabled = false)
    public void myFirstTest() {
        System.out.println("First Test");
       // throw new RuntimeException("fail test");
    }

    @Test(testName = "Второй тест", description = "Имя нашего второго теста", enabled = false)
    public void mySecondTest() {
        System.out.println("Second Test");
    }

}
