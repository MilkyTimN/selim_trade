package kg.megalab.selim_trade.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "admins")
@Data
public class Admin {
    @Id
    @GeneratedValue(generator = "admin_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "admin_id_generator", sequenceName = "admin_seq", allocationSize = 1)
    private int id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @CreationTimestamp
    private LocalDate created_date;
    @UpdateTimestamp
    private LocalDate updated_date;

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "admin_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
    private List<Advantage> advantageList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
    private Set<News> newsSet;
}
