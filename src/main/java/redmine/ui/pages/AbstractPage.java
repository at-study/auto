package redmine.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import redmine.ui.pages.helpers.CucumberName;

/**
 * Абстрактная страница
 */

public abstract class AbstractPage {

    @CucumberName
    @FindBy(xpath = "//div")
    private WebElement field;

}
