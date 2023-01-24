package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchCityException;
import charity.pejvak.coinbox.model.City;
import charity.pejvak.coinbox.model.Province;
import charity.pejvak.coinbox.model.Zone;
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

    public City getCity(long cityId){
        return cityRepository.findById(cityId).orElseThrow(() -> {
            throw new NoSuchCityException();
        });
    }

    public City addCity(City city) {
        return cityRepository.saveAndFlush(city);
    }

    public City updateCity(long cityId, City city) {
        City oldCity = cityRepository.findById(cityId).orElseThrow(() -> {
            throw new NoSuchCityException();
        });

        oldCity.setName(city.getName());

        return cityRepository.saveAndFlush(oldCity);
    }

    public City deleteCity(long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(() -> {
            throw new NoSuchCityException();
        });
        cityRepository.delete(city);
        return city;
    }
    public City addZone(long cityId, Zone zone){
        City city = cityRepository.findById(cityId).orElseThrow(() -> {throw new NoSuchCityException();});
        city.addZone(zone);

        return cityRepository.saveAndFlush(city);

    }

    public City updateCity(City city) {
        return cityRepository.saveAndFlush(city);
    }
}
