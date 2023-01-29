package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.Address;
import charity.pejvak.coinbox.model.City;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.model.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Page<Address> findAllByUser(User user,Pageable pageable);

    Optional<Address> findByUser_IdAndId(long userId, long addressId);
}
