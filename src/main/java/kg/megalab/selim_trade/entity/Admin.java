package kg.megalab.selim_trade.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import kg.megalab.selim_trade.entity.enums.ERole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "admins")
@Getter
@Setter
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(generator = "admin_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "admin_id_generator", sequenceName = "admin_seq", allocationSize = 1)
    private int id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @CreationTimestamp
    private Date created_date;
    @LastModifiedDate
    private Date updated_date;

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "admin_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles = new HashSet<>();

    private boolean active;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
//    private List<Advantage> advantageList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
    private Set<News> newsSet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
