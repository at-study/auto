package redmine.ui.pages;

import org.openqa.selenium.support.PageFactory;

import lombok.SneakyThrows;
import redmine.managers.Manager;

/**
 * Класс для получения PageObject с инициализированными элементами
 */

public class Pages {

    @SneakyThrows
    public static <T extends AbstractPage> T getPage(Class<T> clazz) {
        T page = clazz.newInstance();
        PageFactory.initElements(Manager.driver(), page);
        return page;
    }

}
