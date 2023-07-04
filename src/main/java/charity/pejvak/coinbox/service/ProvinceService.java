package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchProvinceExistsException;
import charity.pejvak.coinbox.model.area.City;
import charity.pejvak.coinbox.model.area.Province;
import charity.pejvak.coinbox.repository.ProvinceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public Province addProvince(Province province) {
        return provinceRepository.saveAndFlush(province);
    }

    public Page<Province> getProvinces(Pageable pageable) {
        return provinceRepository.findAll(pageable);
    }

    public Province deleteProvince(int provinceId) {
        if (logger.isDebugEnabled())
            logger.debug("Try to delete Province with id: "+provinceId);
        Province province = provinceRepository.findById(provinceId).orElseThrow(() -> {
            if (logger.isDebugEnabled())
                logger.debug("Couldn't found Province with Id: "+provinceId);
            throw new NoSuchProvinceExistsException("No Such Province Exists with id: "+provinceId);
        });
        if (logger.isDebugEnabled())
            logger.debug("Deleting Province ...");
        provinceRepository.delete(province);
        return province;
    }

    public Province addCity(int provinceId, City city) {

        Province province = provinceRepository.findById(provinceId).orElseThrow(() -> {
            throw new NoSuchProvinceExistsException("No Such Province Exists with id: "+provinceId);
        });

        province.addCity(city);
        return provinceRepository.saveAndFlush(province);
    }

    public Province getProvinceById(int id){
        return provinceRepository.findById(id).orElseThrow(() -> {throw new NoSuchProvinceExistsException("No Such Province Exists With Id: "+id);});
    }


    Logger logger = LoggerFactory.getLogger(ProvinceService.class);

    public Province updateProvince(int provinceId, Province province) {
        Province oldProvince = getProvinceById(provinceId);

        oldProvince.setName(province.getName());

        return provinceRepository.saveAndFlush(oldProvince);

    }
    public Province updateProvince(Province province){
        return provinceRepository.saveAndFlush(province);
    }
}
