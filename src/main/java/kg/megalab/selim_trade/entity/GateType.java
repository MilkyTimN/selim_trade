package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class GateType extends CommonEntity {
    @Id
    @GeneratedValue(generator = "gate_type_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gate_type_id_generator", sequenceName = "gate_type_seq", allocationSize = 1)
    private int id;
    private String backgroundUrl;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "gate_type_id", referencedColumnName = "id")
    private List<Advantage> advantageList = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "gate_type_id", referencedColumnName = "id")
    private List<Gate> gateList = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinTable(name = "gate_type_admin_updates",
            joinColumns = @JoinColumn(name = "gate_type_id"),
            inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();


}
