package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.CoinBoxUserRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinBoxUserRequestLogRepository extends JpaRepository<CoinBoxUserRequestLog,Long> {
}
