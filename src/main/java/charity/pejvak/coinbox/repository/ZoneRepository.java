package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone,Long> {

}
