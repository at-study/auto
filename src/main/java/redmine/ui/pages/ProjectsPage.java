package redmine.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import redmine.managers.Manager;
import redmine.ui.pages.helpers.CucumberName;

@CucumberName("Проекты")
public class ProjectsPage extends AbstractPage {

    @CucumberName("Проекты")
    @FindBy(xpath = "//div[@id='projects-index']//li/div[@class='root']/a")
    public List<WebElement> projectNames;

    public WebElement projectName(String projectName) {
        return Manager.driver()
                .findElement(By.xpath("//div[@id='projects-index']//li/div[@class='root']/a[text()='" + projectName + "']"));
    }

    public WebElement projectDescription(String projectName) {
        return Manager.driver()
                .findElement(By.xpath("//div[@id='projects-index']//li/div[@class='root']/a[text()='" + projectName + "']/following-sibling::div/p"));
    }
}
