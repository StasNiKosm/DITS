package dao.services;

import dao.DaoPerson;
import dao.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private DaoPerson daoPerson;

    public PersonService(DaoPerson daoPerson) {
        this.daoPerson = daoPerson;
    }

    public void savePerson(Person person) {
        daoPerson.save(person);
    }

    public Person getPersonById(int id) {
        return daoPerson.findByID(id);
    }

    public void update(Person person) {
        daoPerson.update(person);
    }

    @Autowired
    public void setDaoPerson(DaoPerson daoPerson) {
        this.daoPerson = daoPerson;
    }

    public DaoPerson getDaoPerson() {
        return daoPerson;
    }
}
