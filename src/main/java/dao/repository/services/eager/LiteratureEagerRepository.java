package dao.repository.services.eager;

import dao.entities.Literature;
import dao.intefaces.DaoRepository;
import dao.intefaces.EagerRepositoryService;
import dao.repository.services.LiteratureService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LiteratureEagerRepository extends LiteratureService implements EagerRepositoryService<Literature> {

    @Autowired
    private final EagerRepositoryService<Literature> literatureEagerRepository;

    public LiteratureEagerRepository(@Autowired EagerRepositoryService<Literature> literatureEagerRepository, DaoRepository<Literature> repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
        this.literatureEagerRepository = literatureEagerRepository;
    }

    @Override
    public Literature load(Literature literature, Session session) {
        literature = getRepository().findById(getRepository().getTemplatedClass(), literature.getLiteratureId(), session);
        //Hibernate.initialize(literature.getLiterature());
        //literatureEagerRepository.loadAll(literature.getLiterature(), session);
        return literature;
    }

}
