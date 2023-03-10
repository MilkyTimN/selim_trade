package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NewOrder {
    @Id
    @GeneratedValue(generator = "orders_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orders_id_generator", sequenceName = "orders_seq", allocationSize = 1)
    private int id;
    private String name;
    private String phoneNumber;
    @Column(columnDefinition = "TEXT")
    private String message;
}
