package leo.almeida.model;

import java.beans.Transient;
import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;

@Entity
public class TicketItem extends PanacheEntity {
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    public Ticket ticket;

    @ManyToOne
    public Pizza pizza;
    public BigDecimal price;
    public Integer qty;

    public TicketItem() {
    }

    @Transactional
    public static TicketItem persist(Ticket ticket, Pizza pizza, BigDecimal price, Integer quantity) {
        TicketItem ticketItem = new TicketItem();
        ticketItem.ticket = ticket;
        ticketItem.pizza = pizza;
        ticketItem.price = price;
        ticketItem.qty = quantity;
        ticketItem.persist();

        return ticketItem;
    }

    @Transient
    public BigDecimal getValue() {
        return price.multiply(BigDecimal.valueOf(qty));
    }

}
