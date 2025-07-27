package leo.almeida.pizza;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import leo.almeida.model.Location;
import leo.almeida.model.Person;
import leo.almeida.model.Pizza;
import leo.almeida.model.PizzaCategory;
import leo.almeida.model.Store;
import leo.almeida.model.Ticket;
import leo.almeida.rs.PizzaResource;


@QuarkusTest
public class PizzaTest {
    @Inject
    PizzaResource pizzaResource;

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
    public void testFindNearestStore() {
        // given dado uma variavel
        Location location = Location.current();
        // when fazer alguma operação com essa variavel
        Store store = Store.findNearest(location);
        // then fazer alguma verificação com os asserts
        assertNotNull(store);

        Log.infof("[id: " + store.id + ", store name: " + store.name + "]");
    }

    @Test
    public void testAddToTicket() {
        // GIVEN
        Store storeRomanesca = Store.persist("Pizzaria teste", "__teste__");

        PizzaCategory categoryTradicional = PizzaCategory.persist(storeRomanesca, "Tradicional", "10.99");
        Pizza pizzaMarguerita = Pizza.persist("Pizza marguerita");
        Pizza pizzaPaulista = Pizza.persist("Pizza Paulista"); 
        categoryTradicional.addPizzas(pizzaMarguerita, pizzaPaulista);

        Person leo = Person.persist("Léo Almeida", "leonardo@teste.com", "13 991010101");

        // WHEN
        Ticket ticket = Ticket.persist(leo, leo.phone, "Rua 12346", "apto 1");
        ticket.addItem(pizzaMarguerita, categoryTradicional.price, 2);
        ticket.addItem(pizzaPaulista, categoryTradicional.price, 1);
        BigDecimal precoAtual = ticket.getValue();

        // THEN
        BigDecimal valorEsperado = new BigDecimal("32.97");
        assertEquals(precoAtual, valorEsperado);
    }

}
