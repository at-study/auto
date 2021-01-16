package ui_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import redmine.managers.Manager;
import redmine.model.user.User;
import redmine.utils.BrowserUtils;

import static redmine.managers.Manager.driver;

public class YandexTest {

    private User user;

    @BeforeMethod
    public void prepareFixtures() {
        driver().navigate().to("https://yandex.ru");
    }


    @Test
    public void yandexTest() {

        WebElement element = driver().findElement(By.xpath("//div[starts-with(@class,'b-inline') and .//a[text()='EUR']]"));

        Actions actions = new Actions(driver());
        actions.moveToElement(element).build().perform();

        WebElement hint = driver().findElement(By.xpath("//div[contains(@class,'item_hint_visible')]"));

        Assert.assertTrue(BrowserUtils.isElementCurrentlyPresent(hint));
    }

    @AfterMethod
    public void tearDown() {
        Manager.driverQuit();
    }

}
