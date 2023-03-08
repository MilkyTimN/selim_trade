package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderInProgress {
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

    @CreationTimestamp
    private LocalDate created_date;
    @UpdateTimestamp
    private LocalDate updated_date;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_admin_update",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Admin> updatedBy;
}
