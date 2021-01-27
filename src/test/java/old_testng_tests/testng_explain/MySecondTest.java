package old_testng_tests.testng_explain;

import org.testng.annotations.Test;

public class MySecondTest extends AbstractTest {

    @Test(enabled = false)
    public void myThirdTestMethod() {
        System.out.println("Third Test");
    }

    @Test(expectedExceptions = {RuntimeException.class}, enabled = false)
    public void myFourthTestMethod() {
        System.out.println("Fourth Test");
       // throw new IllegalStateException("");
        throw new IllegalArgumentException();
    }

}
