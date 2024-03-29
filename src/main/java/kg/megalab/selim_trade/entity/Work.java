package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "our_works")
public class Work {
    @Id
    @GeneratedValue(generator = "work_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "work_id_generator", sequenceName = "work_seq", allocationSize = 1)
    private int id;
    private String photoUrl;

    @CreationTimestamp
    private Date created_date;

}
