package dao.repository;

import dao.entities.Test;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository extends AbstractRepository<Test> {

    public TestRepository() {
        super(Test.class);
    }

}
