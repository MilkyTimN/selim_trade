package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NewsPhoto {
    @Id
    @GeneratedValue(generator = "news_photo_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "news_photo_id_generator", sequenceName = "news_photo_se", allocationSize = 1)
    private int id;
    @ManyToOne
    private News news;

    private String photoUrl;

}
