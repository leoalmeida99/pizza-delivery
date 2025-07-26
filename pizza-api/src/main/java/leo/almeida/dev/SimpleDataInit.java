package leo.almeida.dev;

import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import leo.almeida.model.Pizza;
import leo.almeida.model.PizzaCategory;
import leo.almeida.model.Store;

public class SimpleDataInit {
    @Inject
    LaunchMode mode;

    @Transactional
    public void init(@Observes StartupEvent ev) {
        if (LaunchMode.NORMAL.equals(mode)) {
            return;
        }

        Store storeRomanesca = Store.persist("Pizzaria Romanesca", "__default__");

        PizzaCategory categoryTradicional = PizzaCategory.persist(storeRomanesca, "Tradicional", "10.99");
        Pizza pizzaMarguerita = Pizza.persist("Pizza marguerita");
        Pizza pizzaPauslita = Pizza.persist("Pizza Paulista"); 
        categoryTradicional.addPizzas(pizzaMarguerita, pizzaPauslita);

        PizzaCategory categoryPremium = PizzaCategory.persist(storeRomanesca, "Premium", "14.99");
        Pizza pizza4Queijos = Pizza.persist("4 Queijos");
        Pizza pizzaNapolitana = Pizza.persist("Napolitana");
        Pizza pizzaVegana = Pizza.persist("Vegana");
        categoryPremium.addPizzas(pizza4Queijos, pizzaNapolitana, pizzaVegana);
    }

}
