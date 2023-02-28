package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
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
