package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Gate extends CommonEntity {
    @Id
    @GeneratedValue(generator = "gate_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gate_id_generator", sequenceName = "gate_seq", allocationSize = 1)
    private int id;

    private String name;
    private String photoUrl;



    @ManyToOne
    GateType gateType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinTable(name = "gate_admin_updates",
            joinColumns = @JoinColumn(name = "gate_id"),
            inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();

}
