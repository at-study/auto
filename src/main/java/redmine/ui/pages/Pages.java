package redmine.ui.pages;

import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import redmine.managers.Manager;

/**
 * Класс для получения PageObject с инициализированными элементами
 */

public class Pages {

    /**
     * Получение экземпляра PageObject с инициализированными элементами
     *
     * @param clazz - класс, представляющий нужную страницу
     * @param <T>   - имя класса страницы
     * @return экземпляр класса PageObject
     */

    @SneakyThrows
    public static <T extends AbstractPage> T getPage(Class<T> clazz) {
        return Allure.step("Обращение к странице " + clazz.getSimpleName(), () -> {
            T page = clazz.newInstance();
            PageFactory.initElements(Manager.driver(), page);
            Manager.takeScreenshot();
            return page;
        });
    }

}
