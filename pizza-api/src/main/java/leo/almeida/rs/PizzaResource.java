package leo.almeida.rs;

import java.util.List;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import leo.almeida.model.Pizza;
import leo.almeida.model.PizzaCategory;
import leo.almeida.model.Store;

@Path("/pizza")
public class PizzaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pizza> getAll() {
        List<Pizza> pizzas = Pizza.listAll();
        return pizzas;
    }

    @GET
    @Path("tudo")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> pegarTudo() {
        Store store = Store.findNearest();
        List<PizzaCategory> categories = PizzaCategory.listByStore(store);
        Map<String, Object> result = Map.of(
                "store" , store,
                "categories", categories
        );
        return result;
    }
}
