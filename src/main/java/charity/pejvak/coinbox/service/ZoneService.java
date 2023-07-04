package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchZoneExistsException;
import charity.pejvak.coinbox.model.area.City;
import charity.pejvak.coinbox.model.area.Province;
import charity.pejvak.coinbox.model.area.Zone;
import charity.pejvak.coinbox.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ZoneService {
    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public Zone addZone(Zone zone) {
        return zoneRepository.saveAndFlush(zone);
    }


    public Page<Zone> getZones(Province province, City city, Pageable pageable) {
        return zoneRepository.findAllByCity_ProvinceIdAndCityId(province.getId(), city.getId(), pageable);
    }

    public Zone getZone(int provinceId, long cityId, int zoneId) {
        return zoneRepository.findByIdAndCity_IdAndCity_Province_Id(zoneId, cityId, provinceId).orElseThrow(() -> {
            throw new NoSuchZoneExistsException("Zone not found in this city and province");
        });
    }

    public Zone deleteZone(long zoneId) {
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> {
            throw new NoSuchZoneExistsException("No Such Zone Exists with id: " + zoneId);
        });

        zoneRepository.delete(zone);
        return zone;
    }

    public Zone updateZone(long zoneId, Zone zone) {
        Zone oldzone = zoneRepository.findById(zoneId).orElseThrow(() -> {
            throw new NoSuchZoneExistsException("No Such Zone Exists with id: " + zoneId);
        });
        oldzone.setName(zone.getName());
        oldzone.setDescription(zone.getDescription());
        return zoneRepository.saveAndFlush(oldzone);
    }
}
