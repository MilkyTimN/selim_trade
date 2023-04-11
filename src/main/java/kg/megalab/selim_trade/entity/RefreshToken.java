package kg.megalab.selim_trade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_token_generator")
    @SequenceGenerator(
            name = "r_token_generator",
            sequenceName = "tokens_sequence",
            allocationSize = 1
    )
    private int id;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Date expiryDate;
}
