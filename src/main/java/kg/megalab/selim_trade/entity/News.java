package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
public class News {
    @Id
    @GeneratedValue(generator = "news_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "news_id_generator", sequenceName = "news_se", allocationSize = 1)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_pictures_id", referencedColumnName = "id")
    private Picture picture;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @CreationTimestamp
    private LocalDate created_date;
    @UpdateTimestamp
    private LocalDate updated_date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;

}
