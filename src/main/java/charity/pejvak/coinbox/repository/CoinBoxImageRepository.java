package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.CoinBoxImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinBoxImageRepository extends JpaRepository<CoinBoxImage,Integer> {
}
