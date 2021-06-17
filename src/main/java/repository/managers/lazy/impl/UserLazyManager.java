package repository.managers.lazy.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.DaoRepository;
import repository.dao.entities.User;
import repository.managers.lazy.LazyManager;

@Service
@RequiredArgsConstructor
public class UserLazyManager implements LazyManager<User> {

    @Autowired
    private final DaoRepository<User> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<User> getRepository() {
        return repository;
    }

}
