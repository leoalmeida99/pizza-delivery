package leo.almeida.pizza;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import leo.almeida.model.Pizza;
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
}
