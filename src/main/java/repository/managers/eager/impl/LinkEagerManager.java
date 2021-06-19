package repository.managers.eager.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import repository.dao.DaoRepository;
import repository.dao.entities.Link;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.impl.LinkLazyManager;

@Service
public class LinkEagerManager extends LinkLazyManager implements EagerManager<Link> {

    public LinkEagerManager(DaoRepository<Link> repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Link load(Link link, Session session) {
        link = getRepository().findById(getRepository().getTemplatedClass(), link.getLinkid(), session);
        return link;
    }

}
