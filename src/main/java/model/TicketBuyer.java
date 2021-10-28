package model;

import enums.TypePerSonBuy;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = " ticket_buyer")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketBuyer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "type_buy", nullable = false)
    private String typePersonBuy;

    @OneToMany(mappedBy = "ticketBuyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invoice> invoices;

}