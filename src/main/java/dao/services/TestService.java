package dao.services;

import dao.entities.Test;
import dao.repository.DaoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService implements RepositoryService<Test> {

    @Autowired
    private final DaoRepository<Test> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Test> getRepository() {
        return repository;
    }

}
