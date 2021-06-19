package repository.managers.eager.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import repository.dao.DaoRepository;
import repository.dao.entities.Statistic;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.impl.StatisticLazyManager;

@Service
public class StatisticEagerManager extends StatisticLazyManager implements EagerManager<Statistic> {

    public StatisticEagerManager(DaoRepository<Statistic> repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Statistic load(Statistic statistic, Session session) {
        statistic = getRepository().findById(getRepository().getTemplatedClass(), statistic.getStatisticid(), session);
        return statistic;
    }

}
