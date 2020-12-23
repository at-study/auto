package redmine.model.user;

import redmine.db.requests.UserRequests;
import redmine.model.Generatable;

/**
 * Класс-модель пользователя в системе.
 */
public class User implements Generatable<User> {

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
