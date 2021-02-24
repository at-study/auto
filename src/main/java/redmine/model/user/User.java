package redmine.model.user;

import lombok.Data;
import lombok.experimental.Accessors;
import redmine.db.requests.UserRequests;
import redmine.model.Generatable;

/**
 * Класс-модель пользователя в системе.
 */
@Data
@Accessors(chain = true)
public class User implements Generatable<User> {
    private String login = "admin";
    private String password = "admin123";
    private Boolean admin = false;
    private Integer status = 1;
    // generateRandomString(40, "0123456789abcdef");

    public String getApiKey() {
        //TODO Изменить на генерацию ключа API
        //  return "f02b2da01a468c4116be898911481d1b928c15f9"; aadmin
        return "1f141a250bd38124d589f4dac5d9a23d970ff98a";
    }

    @Override
    public User read() {
        return null;
    }

    @Override
    public User update() {
        return null;
    }

    @Override
    public User create() {
        return UserRequests.createUser(this);
    }

}
