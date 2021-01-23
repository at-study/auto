package rest_tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import redmine.model.dto.UserCreationError;
import redmine.model.dto.UserDto;
import redmine.model.dto.UserInfo;

import static io.restassured.RestAssured.given;
import static redmine.utils.StringGenerators.randomEmail;
import static redmine.utils.StringGenerators.randomEnglishLowerString;
import static redmine.utils.StringGenerators.randomEnglishString;
import static redmine.utils.gson.GsonHelper.getGson;

public class MyFirstRestTest {

    @Test
    public void restRequestTest() {
        given().baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .request(Method.GET, "roles.json")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test
    public void createUserTest() {
        String apiKey = "f02b2da01a468c4116be898911481d1b928c15f9";
        String login = randomEnglishLowerString(8);
        String firstName = randomEnglishString(12);
        String lastName = randomEnglishString(12);
        String mail = randomEmail();

        UserDto user = new UserDto()
                .setUser(new UserInfo()
                        .setLogin(login)
                        .setFirstname(firstName)
                        .setLastname(lastName)
                        .setMail(mail)
                        .setPassword("1qaz@WSX")
                );
        String body = getGson().toJson(user);

        Response response = given().baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", apiKey)
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 201);
        String responseBody = response.getBody().asString();

        UserDto createdUser = getGson().fromJson(responseBody, UserDto.class);

        Assert.assertEquals(createdUser.getUser().getLogin(), login);
        Assert.assertEquals(createdUser.getUser().getFirstname(), firstName);
        Assert.assertEquals(createdUser.getUser().getLastname(), lastName);
        Assert.assertNull(createdUser.getUser().getPassword());
        Assert.assertEquals(createdUser.getUser().getMail(), mail);
        Assert.assertNull(createdUser.getUser().getLast_login_on());
        Assert.assertEquals(createdUser.getUser().getStatus().intValue(), 1);
        Assert.assertFalse(createdUser.getUser().getAdmin());

    }

    @Test
    public void createUserWithInvalidPassword() {
        String apiKey = "f02b2da01a468c4116be898911481d1b928c15f9";
        String login = randomEnglishLowerString(8);
        String firstName = randomEnglishString(12);
        String lastName = randomEnglishString(12);
        String mail = randomEmail();
        String password = String.valueOf(new Random().nextInt(500000) + 100000);

        String body = String.format("{\n" +
                "    \"user\": {\n" +
                "        \"login\": \"%s\",\n" +
                "        \"firstname\": \"%s\",\n" +
                "        \"lastname\": \"%s\",\n" +
                "        \"mail\": \"%s\",\n" +
                "        \"password\": \"%s\" \n" +
                "    }\n" +
                "}", login, firstName, lastName, mail, password);

        Response response = given().contentType(ContentType.JSON)
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .header("X-Redmine-API-Key", apiKey)
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 422);

        UserCreationError errors = getGson().fromJson(response.getBody().asString(), UserCreationError.class);

        Assert.assertEquals(errors.getErrors().size(), 1);
        Assert.assertEquals(errors.getErrors().get(0), "Пароль недостаточной длины (не может быть меньше 8 символа)");
    }


}
