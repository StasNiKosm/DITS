package dao.entities;

import dao.AbstractDao;
import org.hibernate.Session;

public class DaoLiterature extends AbstractDao<Literature> {

    @Override
    public Literature findByID(int id) {
        return findByID(id, Literature.class);
    }

    @Override
    public void update(Literature literature) {
        commitOperation(
            session -> session.update(literature)
        );
    }

    @Override
    public void deepUpdate(Literature literature, Session session) {
        session.update(literature);
    }

    @Override
    public void save(Literature literature) {
        commitOperation(
            session -> session.save(literature)
        );
    }

    @Override
    public void deepSave(Literature literature, Session session) {
        session.save(literature);
    }

}
