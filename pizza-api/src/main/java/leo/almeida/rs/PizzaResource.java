package leo.almeida.rs;

import java.util.List;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import leo.almeida.model.Pizza;

@Path("/pizza")
public class PizzaResource {

    @Transactional
    public void init(@Observes StartupEvent ev) {
        Pizza pizza1 = new Pizza();
        pizza1.description = "Pizza marguerita";
        pizza1.persist();

        Pizza pizza2 = new Pizza(); 
        pizza2.description = "Pizza Paulista";
        pizza2.persist();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pizza> getAll() {
        return Pizza.listAll();
    }
}
