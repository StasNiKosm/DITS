package repository.dao.impl;

import repository.dao.entities.User;

public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }

}
