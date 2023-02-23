package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findAllByCoinBoxType_Id(long coinBoxTypeId);
}
