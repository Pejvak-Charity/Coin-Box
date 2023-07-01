package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.CoinBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinBoxRepository extends JpaRepository<CoinBox,Long> {
}
