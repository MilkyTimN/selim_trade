package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Boolean existsByAdmin(Admin admin);
    Optional<RefreshToken> findByAdmin(Admin admin);

    void deleteByAdmin(Admin admin);
}
