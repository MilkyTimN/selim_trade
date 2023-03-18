package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table
public class GateType {
    @Id
    @GeneratedValue(generator = "gate_type_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gate_type_id_generator", sequenceName = "gate_type_seq", allocationSize = 1)
    private int id;
    private String backgroundUrl;
    private String name;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="gate_type_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Advantage> advantageList = new ArrayList<>();
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="gate_type_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Gate> gateList = new ArrayList<>();
    @CreationTimestamp
    private Date created_date;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gate_type_admin_updates",
            joinColumns = @JoinColumn(name = "gate_type_id"),
            inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();


}
