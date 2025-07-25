package leo.almeida.pizza;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import leo.almeida.model.Location;
import leo.almeida.model.Pizza;
import leo.almeida.model.Store;
import leo.almeida.rs.PizzaResource;


@QuarkusTest
public class PizzaTest {
    @Inject
    PizzaResource pizzaResource;

    @BeforeAll
    @Transactional
    public static void beforeAll() {
        Store store = new Store();
        store.name = "Pizzaria Romanesca";
        store.code = "__default__";
        store.persist();
    }

    @Test
    public void testSanity() {
        List<Pizza> ps = pizzaResource.getAll();
        assertFalse(ps.isEmpty());
    }

    @Test
    void testTesteAsPizza() {
        given()
            .when().get("/pizza")
            .then()
                .statusCode(200);
                
    }

    @Test
    public void TestOrderPizzaFlow() {
        // given dado uma variavel
        Location location = Location.current();
        // when fazer alguma operação com essa variavel
        Store store = Store.findNearest(location);
        // then fazer alguma verificação com os asserts
        assertNotNull(store);
    }

}
