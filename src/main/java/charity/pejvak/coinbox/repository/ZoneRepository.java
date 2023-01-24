package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.City;
import charity.pejvak.coinbox.model.Province;
import charity.pejvak.coinbox.model.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone,Long> {
Page<Zone> findAllByCityProvince(Province province,City city,Pageable pageable);
}
