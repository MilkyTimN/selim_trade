package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import kg.megalab.selim_trade.entity.enums.EStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderInProgress extends CommonEntity {
    @Id
    @GeneratedValue(generator = "order_in_progress_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_in_progress_id_generator", sequenceName = "order_progress_seq", allocationSize = 1)
    private int id;
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne
    private GateType gateType;
    @ManyToOne
    private Gate gate;

    private String name;
    private String phoneNumber;



    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinTable(name = "order_in_progress_admin_updates",
            joinColumns = @JoinColumn(name = "order_in_progress_id"),
            inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();
}
