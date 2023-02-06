package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.CoinBoxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinBoxTypeRepository extends JpaRepository<CoinBoxType,Long> {
}
