package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.area.Address;
import charity.pejvak.coinbox.model.user.User;
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
