package leo.almeida.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class PizzaCategory extends PanacheEntity {
    public String name;

    @Column(precision = 10, scale = 2)
    public BigDecimal price; 

    @ManyToOne
    @JsonIgnore
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

    public PizzaCategory() {
    }

    public static PizzaCategory persist(Store store, String name, String price) {
        PizzaCategory category = new PizzaCategory();
        category.name = name;
        category.store = store;
        category.pizzas = new ArrayList<>();
        category.price = new BigDecimal(price);
        category.persist();

        return category;
    }

    public void addPizzas(Pizza... ps) {
        this.pizzas.addAll(Arrays.asList(ps));
    }

    public static List<PizzaCategory> listByStore(Store store) {
        List<PizzaCategory> result = list("store", Sort.by("price").ascending(), store);
        return result;
    }

}
