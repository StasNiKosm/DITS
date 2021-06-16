package dao.repository.eager;

import dao.entities.Literature;
import dao.entities.Question;
import dao.repository.DaoRepository;
import dao.services.LiteratureService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LiteratureEagerRepository extends LiteratureService implements EagerRepositoryLoader<Literature> {

    @Autowired
    private EagerRepositoryLoader<Literature> literatureEagerRepository;

    public LiteratureEagerRepository(@Autowired EagerRepositoryLoader<Literature> literatureEagerRepository, DaoRepository<Literature> repository, SessionFactory sessionFactory) {
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
