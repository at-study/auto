package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import redmine.api.implementations.RestApiClient;
import redmine.api.implementations.RestRequest;
import redmine.api.interfaces.ApiClient;
import redmine.api.interfaces.HttpMethods;
import redmine.api.interfaces.Request;
import redmine.api.interfaces.Response;
import redmine.cucumber.ParametersValidator;
import redmine.managers.Context;
import redmine.model.dto.UserDto;
import redmine.model.dto.UserInfo;
import redmine.model.user.User;
import redmine.utils.Asserts;

import static redmine.utils.StringGenerators.randomEmail;
import static redmine.utils.StringGenerators.randomEnglishString;
import static redmine.utils.gson.GsonHelper.getGson;

public class ApiSteps {

    @И("Отправить запрос на создание пользователя через API пользователем {string}")
    public void sendApiRequest(String userStashId) {
        User user = Context.get(userStashId, User.class);
        ApiClient apiClient = new RestApiClient(user);
        UserDto body = new UserDto()
                .setUser(new UserInfo()
                        .setLogin(randomEnglishString(10))
                        .setPassword(randomEnglishString(10))
                        .setMail(randomEmail())
                        .setLastname(randomEnglishString(10))
                        .setFirstname(randomEnglishString(10))
                        .setAdmin(false)
                        .setStatus(1)
                );
        Request request = new RestRequest("users.json", HttpMethods.POST, null, null, getGson().toJson(body));
        Response response = apiClient.executeRequest(request);
        Context.put("last_response", response);
    }

    @То("Статус-код ответа {int}")
    public void assertStatusCode(int expectedStatusCode) {
        Response response = Context.get("last_response", Response.class);
        Asserts.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @И("Вывести на экран строку {string}")
    public void printString(String rawString) {
        String result = ParametersValidator.replaceCucumberVariables(rawString);
        System.out.println(result);
    }
}
