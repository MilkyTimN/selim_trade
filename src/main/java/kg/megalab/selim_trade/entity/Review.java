package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(generator = "review_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "review_id_generator", sequenceName = "review_seq", allocationSize = 1)
    private int id;
    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "tb_pictures_id", referencedColumnName = "id")
//    private Picture picture;
    private String photoUrl;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String text;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchased_gate_id", referencedColumnName = "id")
    private Gate gate;
    @CreationTimestamp
    private Date created_date;
    @UpdateTimestamp
    private Date updated_date;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "review_admin_update",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Admin> updatedBy;
}
