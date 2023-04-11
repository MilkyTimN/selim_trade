package kg.megalab.selim_trade.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonEntity {
    @CreationTimestamp
    private Date created_date;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Admin createdBy;
}
