package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "type_seat", nullable = false)
    private String typeSeat;

    @Column(name = "price", nullable = false)
    private int price;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invoice> invoices;

}
