package redmine.db.requests;

import redmine.model.user.User;

public class UserRequests {

    public static User createUser(User user) {
        // Запрос в БД
        // установка id значением, сгенерированным в БД
        return user;
    }

}
