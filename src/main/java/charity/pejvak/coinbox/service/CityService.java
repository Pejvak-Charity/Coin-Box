package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchCityException;
import charity.pejvak.coinbox.model.City;
import charity.pejvak.coinbox.model.Zone;
import charity.pejvak.coinbox.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;


    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities() {
        return cityRepository.findAll();
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
}
