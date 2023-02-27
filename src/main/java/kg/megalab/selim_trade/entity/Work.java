package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "our_works")
public class Work {
    @Id
    @GeneratedValue(generator = "work_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "work_id_generator", sequenceName = "work_seq", allocationSize = 1)
    private int id;
    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "tb_pictures_id", referencedColumnName = "id")
//    private Picture picture;
    private String photoUrl;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "gate_type_id")
    private Set<GateTypes> gateTypes;
    @CreationTimestamp
    private LocalDate created_date;
    @UpdateTimestamp
    private LocalDate updated_date;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "work_admin_update",
            joinColumns = @JoinColumn(name = "work_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Admin> updatedBy;

}
