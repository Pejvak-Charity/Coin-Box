package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchProvinceExistsException;
import charity.pejvak.coinbox.model.City;
import charity.pejvak.coinbox.model.Province;
import charity.pejvak.coinbox.repository.ProvinceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Province> getProvinces() {
        return provinceRepository.findAll();
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


    Logger logger = LoggerFactory.getLogger(ProvinceService.class);
}
