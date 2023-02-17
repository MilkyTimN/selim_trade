package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class GateTypes {
    @Id
    @GeneratedValue(generator = "gate_type_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gate_type_id_generator", sequenceName = "gate_type_seq", allocationSize = 1)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_pictures_id", referencedColumnName = "id")
    private Picture picture;
    private String name;
    @CreationTimestamp
    private LocalDate created_date;
    @UpdateTimestamp
    private LocalDate updated_date;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gate_admin_updates",
    joinColumns = @JoinColumn(name = "gate_id"),
    inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Admin> updatedBy;


}
