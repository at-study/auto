package allure_tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AllureTests {

    @Test
    public void assertException() {
        Assert.assertEquals(2, 3);
    }

    @Test
    public void runtimeException() {
        throw new RuntimeException();
    }

    @Test
    public void checkedException() throws IOException {
        throw new IOException();
    }

}
