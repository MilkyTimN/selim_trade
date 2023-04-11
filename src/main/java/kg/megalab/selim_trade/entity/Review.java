package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review extends CommonEntity {
    @Id
    @GeneratedValue(generator = "review_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "review_id_generator", sequenceName = "review_seq", allocationSize = 1)
    private int id;
    private String photoUrl;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String text;
    private String gate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinTable(name = "review_admin_updates",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();
}
