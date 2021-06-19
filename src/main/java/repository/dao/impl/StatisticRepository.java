package repository.dao.impl;

import org.springframework.stereotype.Repository;
import repository.dao.entities.Statistic;

@Repository
public class StatisticRepository extends AbstractRepository<Statistic> {

    public StatisticRepository() {
        super(Statistic.class);
    }

}
