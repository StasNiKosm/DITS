package repository.managers.eager.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import repository.dao.entities.Link;
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
    private final EagerManager<Link> linkEagerManager;

    public LiteratureEagerManager(@Autowired EagerManager<Link> linkEagerManager, DaoRepository<Literature> repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
        this.linkEagerManager = linkEagerManager;
    }

    @Override
    public Literature load(Literature literature, Session session) {
        literature = getRepository().findById(getRepository().getTemplatedClass(), literature.getLiteratureId(), session);
        Hibernate.initialize(literature.getLinks());
        linkEagerManager.loadAll(literature.getLinks(), session);
        return literature;
    }

}
