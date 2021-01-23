package rest_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import redmine.api.implementations.RestApiClient;
import redmine.api.implementations.RestRequest;
import redmine.api.interfaces.ApiClient;
import redmine.api.interfaces.HttpMethods;
import redmine.api.interfaces.Request;
import redmine.api.interfaces.Response;
import redmine.model.dto.UserDto;
import redmine.model.dto.UserInfo;
import redmine.model.user.User;
import redmine.utils.Asserts;

import static redmine.utils.StringGenerators.randomEmail;
import static redmine.utils.StringGenerators.randomEnglishLowerString;
import static redmine.utils.StringGenerators.randomEnglishString;
import static redmine.utils.gson.GsonHelper.getGson;

public class RedmineApiTests {

    private User user;
    private ApiClient apiClient;


    @BeforeMethod
    public void prepareFixtures() {
        user = new User().generate();
        apiClient = new RestApiClient(user);
    }

    @Test
    public void testRoleGet() {
        Request request = new RestRequest("roles.json", HttpMethods.GET, null, null, null);
        Response response = apiClient.executeRequest(request);

        Asserts.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getHeaders().containsKey("Content-Type"));
    }

    @Test
    public void testUserCreation() {
        UserDto createdUser = createUser();

    }

    @Step("Создание пользователя")
    private UserDto createUser() {
        UserDto userToCreateDto = new UserDto()
                .setUser(new UserInfo()
                        .setLogin(randomEnglishLowerString(8))
                        .setFirstname(randomEnglishString(12))
                        .setLastname(randomEnglishString(12))
                        .setMail(randomEmail())
                        .setPassword("1qaz@WSX")
                );

        Response response = apiClient.executeRequest(
                new RestRequest("users.json", HttpMethods.POST, null, null, getGson().toJson(userToCreateDto))
        );

        Asserts.assertEquals(response.getStatusCode(), 201);

        UserDto createdUserDto = response.getBody(UserDto.class);

        Asserts.assertEquals(createdUserDto.getUser().getLogin(), userToCreateDto.getUser().getLogin());
        Asserts.assertEquals(createdUserDto.getUser().getFirstname(), userToCreateDto.getUser().getFirstname());
        Asserts.assertEquals(createdUserDto.getUser().getLastname(), userToCreateDto.getUser().getLastname());
        Assert.assertNull(createdUserDto.getUser().getPassword());
        Asserts.assertEquals(createdUserDto.getUser().getMail(), userToCreateDto.getUser().getMail());
        Assert.assertNull(createdUserDto.getUser().getLast_login_on());
        Asserts.assertEquals(createdUserDto.getUser().getStatus(), 1);
        Assert.assertFalse(createdUserDto.getUser().getAdmin());
        return createdUserDto;
    }

}
