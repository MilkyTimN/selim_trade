package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "advantages")
@Data
public class Advantage {
    @Id
    @GeneratedValue(generator = "advantage_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "advantage_id_generator", sequenceName = "advantage_seq", allocationSize = 1)
    private int id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @CreationTimestamp
    private LocalDate created_date;
    @UpdateTimestamp
    private LocalDate updated_date;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private Admin admin;

}
