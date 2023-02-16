package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "pictures")
@Data
public class Picture {
    @Id
    @GeneratedValue(generator = "picture_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "picture_id_generator", sequenceName = "picture_seq", allocationSize = 1)
    private int id;
    @Column(length = 2000)
    private String url;
    @CreationTimestamp
    private LocalDate created_date;
    @UpdateTimestamp
    private LocalDate updated_date;

}
