package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "our_works")
public class Work {
    @Id
    @GeneratedValue(generator = "work_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "work_id_generator", sequenceName = "work_seq", allocationSize = 1)
    private int id;
    private String photoUrl;

    @CreationTimestamp
    private Date created_date;
    @UpdateTimestamp
    private Date updated_date;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "work_admin_update",
            joinColumns = @JoinColumn(name = "work_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Admin> updatedBy;

}
