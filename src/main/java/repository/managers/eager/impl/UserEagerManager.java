package repository.managers.eager.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.DaoRepository;
import repository.dao.entities.User;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.impl.UserLazyManager;

@Service
public class UserEagerManager extends UserLazyManager implements EagerManager<User> {

    public UserEagerManager(@Autowired DaoRepository<User> repository, @Autowired SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public User load(User user, Session session) {
        return super.read(user.getUserId(), session);
    }

}
