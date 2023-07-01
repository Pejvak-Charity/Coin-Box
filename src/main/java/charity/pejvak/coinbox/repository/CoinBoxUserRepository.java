package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.CoinBoxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinBoxUserRepository extends JpaRepository<CoinBoxUser, Long> {
    List<CoinBoxUser> findByUserId(Long userId);
}
