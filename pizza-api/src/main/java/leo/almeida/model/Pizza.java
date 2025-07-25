package leo.almeida.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Pizza extends PanacheEntity {
    public String description;

    public Pizza() {
    }

    public static Pizza persist(String description) {
        Pizza pizza = new Pizza();
        pizza.description = description;
        pizza.persist();

        return pizza;
    }

    @Override
    public String toString() {
        return "Pizza [description=" + description + "]";
    }

}
