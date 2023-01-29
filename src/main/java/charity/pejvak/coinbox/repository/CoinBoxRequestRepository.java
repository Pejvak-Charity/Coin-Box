package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.CoinBoxUserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinBoxRequestRepository extends JpaRepository<CoinBoxUserRequest,Long> {
}
