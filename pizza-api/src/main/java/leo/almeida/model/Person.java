package leo.almeida.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public String email;
    public String phone;

    public Person() {
    }

    @Transactional
    public static Person persist(String name, String email, String phone) {
        Person person = new Person();
        person.name = name;
        person.email = email;
        person.phone = phone;
        person.persist();

        return person;
    }
}
