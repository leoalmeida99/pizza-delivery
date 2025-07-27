package leo.almeida.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.transaction.Transactional;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Store extends PanacheEntity {
    public String name;
    public String code;

    public Store() {
    }

    @Transactional
    public static Store persist(String name, String code) {
        Store store = new Store();
        store.name = name;
        store.code = code;
        store.persist();

        return store;
    }

    public static Store findNearest() {
        return findNearest(Location.current());
    }

    public static Store findNearest(Location location) {
        Store store = Store.find("code", "__default__").firstResult();

        return store;
    }

}
