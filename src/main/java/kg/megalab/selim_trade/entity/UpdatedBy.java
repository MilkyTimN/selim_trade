package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class UpdatedBy {
    @Id
    @GeneratedValue(generator = "updated_by_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "updated_by_id_generator", sequenceName = "updated_by_seq", allocationSize = 1)
    private long id;

    private String username;
    @CreationTimestamp
    private Date date;

    public UpdatedBy(String username, Date date) {
        this.username = username;
        this.date = date;
    }
}
