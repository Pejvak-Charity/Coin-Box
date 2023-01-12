package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.City;
import charity.pejvak.coinbox.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    Page<City> findAllByProvince(Province province, Pageable pageable);

}
