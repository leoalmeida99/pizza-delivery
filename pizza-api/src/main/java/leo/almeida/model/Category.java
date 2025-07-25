package leo.almeida.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Category extends PanacheEntity {
    public String name;

    @Column(precision = 10, scale = 2)
    public BigDecimal price; 

    public Category() {
    }

    @ManyToOne
    public Store store;

    @ManyToMany(cascade = {
        CascadeType.MERGE,
        CascadeType.PERSIST
    })
    @JoinTable(name = "pizza_category",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    public List<Pizza> pizzas;

    public static Category persist(Store store, String name) {
        Category category = new Category();
        category.name = name;
        category.store = store;
        category.pizzas = new ArrayList<>();     
        category.persist();

        return category;
    }

    public void addPizzas(Pizza... ps) {
        this.pizzas.addAll(pizzas);
    }

    public static List<Category> listByStore(Store store) {
        List<Category> result = list("store", Sort.by("price").ascending(), store);
        return result;
    }

}
