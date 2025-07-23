package leo.almeida.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Pizza extends PanacheEntity {
    public String description;

    public Pizza() {
    }

    @Override
    public String toString() {
        return "Pizza [description=" + description + "]";
    }

}
