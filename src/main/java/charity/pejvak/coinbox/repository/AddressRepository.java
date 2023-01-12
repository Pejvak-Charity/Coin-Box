package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.Address;
import charity.pejvak.coinbox.model.City;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.model.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Page<Address> findAllByCity(City city, Pageable pageable);
    Page<Address> findAllByCityAndZone(City city, Zone zone,Pageable pageable);
    Page<Address> findAllByUser(User user,Pageable pageable);
}
