package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchZoneExistsException;
import charity.pejvak.coinbox.model.Zone;
import charity.pejvak.coinbox.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Zone> getZones() {
        return zoneRepository.findAll();
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