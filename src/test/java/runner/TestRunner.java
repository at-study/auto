package runner;

import java.lang.reflect.Method;

import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import redmine.managers.Context;
import redmine.managers.Manager;

@CucumberOptions (
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/cucumber.json"
        },
        glue = {"steps", "hooks"},
        features = "src/test/resources",
        tags = {"@generation_sample"}
)
@Listeners({TestNGListenerImpl.class})
public class TestRunner extends AbstractTestNGCucumberTests implements ITest {

    //TODO изменить на ThreadLocal
    private static String testCaseName;

    @BeforeClass(alwaysRun = true)
    @Override
    public void setUpClass() throws Exception {
        super.setUpClass();
    }

    @Override
    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass(alwaysRun = true)
    @Override
    public void tearDownClass() throws Exception {
        super.tearDownClass();
    }

    @BeforeMethod
    public void beforeMethod(Method name, Object[] testData) {
        testCaseName = testData[0].toString();
    }

    @AfterMethod
    public void afterMethod(Method name, Object[] testDate) {
        //TODO реализовать сохранение контекста в отчете.
        Context.saveStashToAllure();
        Context.clearStash();
        Manager.driverQuit();
    }

    @Override
    public String getTestName() {
        return null;
    }
}
