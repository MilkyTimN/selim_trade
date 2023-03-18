package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "news")
@Data
public class News {
    @Id
    @GeneratedValue(generator = "news_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "news_id_generator", sequenceName = "news_se", allocationSize = 1)
    private int id;

    //    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "tb_pictures_id", referencedColumnName = "id")
//    private Picture picture;
    private String photoUrl;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @CreationTimestamp
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinTable(name = "news_admin_updates",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();

}
