package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.NewsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsPhotoRepository extends JpaRepository<NewsPhoto, Integer> {

}
