package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.area.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Integer> {
}
