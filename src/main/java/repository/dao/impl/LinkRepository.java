package repository.dao.impl;

import org.springframework.stereotype.Repository;
import repository.dao.entities.Link;

@Repository
public class LinkRepository extends AbstractRepository<Link> {

    public LinkRepository() {
        super(Link.class);
    }

}
