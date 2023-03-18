package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "advantages")
@Data
public class Advantage {
    @Id
    @GeneratedValue(generator = "advantage_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "advantage_id_generator", sequenceName = "advantage_seq", allocationSize = 1)
    private int id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @CreationTimestamp
    private Date created_date;

    @ManyToOne
    GateType gateType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Admin createdBy;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "advantage_admin_updates",
//            joinColumns = @JoinColumn(name = "advantage_id"),
//            inverseJoinColumns = @JoinColumn(name = "admin_id"))
//    private List<Admin> updatedBy;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "advantage_admin_updates",
    joinColumns = @JoinColumn(name = "advantage_id"),
    inverseJoinColumns = @JoinColumn(name = "updated_by_id"))
    private List<UpdatedBy> updatedByList = new ArrayList<>();


}
