package steps;

import java.util.Map;

import cucumber.api.java.ru.Пусть;
import redmine.managers.Context;
import redmine.model.user.User;

public class GenerationSteps {

    @Пусть("Существует пользователь {string} с параметрами:")
    public void generateAndSaveUser(String stashId, Map<String, String> params) {
        User user = new User();
        if (params.containsKey("login")) {
            user.setLogin(params.get("login"));
        }
        if (params.containsKey("password")) {
            user.setPassword(params.get("password"));
        }
        if (params.containsKey("admin")) {
            user.setAdmin(Boolean.parseBoolean(params.get("admin")));
        }
        if (params.containsKey("status")) {
            user.setStatus(Integer.parseInt(params.get("status")));
        }
        user.generate();
        Context.getStash().put(stashId, user);
    }

}
