package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchCityException;
import charity.pejvak.coinbox.model.area.City;
import charity.pejvak.coinbox.model.area.Province;
import charity.pejvak.coinbox.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;



    public Page<City> getCities(Province province, Pageable pageable) {
        return cityRepository.findAllByProvince(province,pageable);
    }

    public City getCity(int provinceId,long cityId){
        return cityRepository.findByProvinceIdAndId(provinceId,cityId).orElseThrow(() -> {
            throw new NoSuchCityException();
        });
    }

    public City addCity(City city) {
        return cityRepository.saveAndFlush(city);
    }


    public City deleteCity(long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(() -> {
            throw new NoSuchCityException();
        });
        cityRepository.delete(city);
        return city;
    }

}
