package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ticket_buyer_id", nullable = false)
    private TicketBuyer ticketBuyer;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", ticketBuyer=" + ticketBuyer.getName() +
                ", ticket=" + ticket.getTypeSeat() +
                ", quantity=" + quantity +
                '}';
    }
}
