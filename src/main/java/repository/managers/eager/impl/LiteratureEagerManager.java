package repository.managers.eager.impl;

import org.springframework.stereotype.Service;
import repository.dao.entities.Literature;
import repository.dao.DaoRepository;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.impl.LiteratureLazyManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LiteratureEagerManager extends LiteratureLazyManager implements EagerManager<Literature> {

    @Autowired
    private final EagerManager<Literature> literatureEagerRepository;

    public LiteratureEagerManager(@Autowired EagerManager<Literature> literatureEagerRepository, DaoRepository<Literature> repository, SessionFactory sessionFactory) {
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
