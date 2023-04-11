package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "news")
@Setter
@Getter
public class News extends CommonEntity {
    @Id
    @GeneratedValue(generator = "news_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "news_id_generator", sequenceName = "news_se", allocationSize = 1)
    private int id;

    private String photoUrl;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    private List<NewsPhoto> photos = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinTable(name = "news_admin_updates",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();

}
