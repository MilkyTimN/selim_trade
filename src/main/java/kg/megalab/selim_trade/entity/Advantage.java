package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advantages")
@Getter
@Setter
public class Advantage extends CommonEntity {
    @Id
    @GeneratedValue(generator = "advantage_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "advantage_id_generator", sequenceName = "advantage_seq", allocationSize = 1)
    private int id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    GateType gateType;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "advantage_admin_updates",
            joinColumns = @JoinColumn(name = "advantage_id"),
            inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();


}
