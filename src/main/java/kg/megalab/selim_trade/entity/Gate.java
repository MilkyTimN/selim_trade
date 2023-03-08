package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Gate {
    @Id
    @GeneratedValue(generator = "gate_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gate_id_generator", sequenceName = "gate_seq", allocationSize = 1)
    private int id;

    private String name;
    private String photoUrl;

    @CreationTimestamp
    private Date created_date;
    @UpdateTimestamp
    private Date updated_date;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

    @ManyToOne
    GateType gateType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gate_admin_updates",
            joinColumns = @JoinColumn(name = "gate_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Admin> updatedBy;

}
