package dao.services;

import dao.entities.Literature;
import dao.repository.DaoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiteratureService implements RepositoryService<Literature> {

    @Autowired
    private final DaoRepository<Literature> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Literature> getRepository() {
        return repository;
    }
}
