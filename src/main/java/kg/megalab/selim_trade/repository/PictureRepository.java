package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Boolean existsByUrl(String url);
    Optional<Picture> findByUrl(String url);
}
