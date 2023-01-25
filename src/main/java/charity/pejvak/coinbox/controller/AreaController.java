package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.CityRequest;
import charity.pejvak.coinbox.componenet.ProvinceRequest;
import charity.pejvak.coinbox.componenet.ZoneRequest;
import charity.pejvak.coinbox.exception.NoSuchCityException;
import charity.pejvak.coinbox.exception.NoSuchProvinceExistsException;
import charity.pejvak.coinbox.exception.NoSuchZoneExistsException;
import charity.pejvak.coinbox.model.City;
import charity.pejvak.coinbox.model.Province;
import charity.pejvak.coinbox.model.Zone;
import charity.pejvak.coinbox.service.CityService;
import charity.pejvak.coinbox.service.ProvinceService;
import charity.pejvak.coinbox.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class AreaController {


    private final ProvinceService provinceService;

    private final CityService cityService;

    private final ZoneService zoneService;

    @GetMapping("/provinces")
    public ResponseEntity<Map<String, Object>> getProvinces(@RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "50") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Province> provincePage = provinceService.getProvinces(pageable);
        return ResponseEntity.ok(toResponse(provincePage));
    }

    @GetMapping("/provinces/{provinceId}")
    public Province getProvince(@PathVariable int provinceId) {
        return provinceService.getProvinceById(provinceId);
    }

    @PostMapping("/provinces")
    public Province addProvince(@RequestBody ProvinceRequest provinceRequest) {
        return provinceService.addProvince(Province.builder().name(provinceRequest.getName()).build());
    }

    @PutMapping("/provinces/{provinceId}")
    public Province deleteProvince(@PathVariable int provinceId, @RequestBody ProvinceRequest provinceRequest) {
        return provinceService.updateProvince(provinceId, Province.builder().name(provinceRequest.getName()).build());
    }

    @DeleteMapping("/provinces/{provinceId}")
    public Province deleteProvince(@PathVariable int provinceId) {
        return provinceService.deleteProvince(provinceId);
    }


    @GetMapping("/provinces/{provinceId}/cities")
    public ResponseEntity<Map<String, Object>> getCities(@PathVariable(name = "provinceId") int provinceId,
                                                         @RequestParam(required = false, defaultValue = "0") int page,
                                                         @RequestParam(required = false, defaultValue = "50") int pageSize) {
        Province province = provinceService.getProvinceById(provinceId);

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<City> cityPage = cityService.getCities(province, pageable);


        return ResponseEntity.ok(toResponse(cityPage));
    }

    @GetMapping("/provinces/{provinceId}/cities/{cityId}")
    public ResponseEntity<City> getCity(@PathVariable(name = "provinceId") int provinceId,
                                        @PathVariable(name = "cityId") long cityId) {
        City city = provinceService.getProvinceById(provinceId)
                .getCities().stream()
                .filter(cityFilter -> cityFilter.getId() == cityId).findFirst().orElseThrow(() -> {
                    throw new NoSuchCityException("City not found with provinceId: "+provinceId+" and cityId: "+cityId);
                });

        return ResponseEntity.ok(city);
    }

    @PostMapping("/provinces/{provinceId}/cities")
    public ResponseEntity<City> addCity(@PathVariable(name = "provinceId") int provinceId, @RequestBody CityRequest cityRequest) {
        Province province = provinceService.getProvinceById(provinceId);
        City city = new City();
        city.setName(cityRequest.getName());
        city.setProvince(province);
        city = cityService.addCity(city);
        province.addCity(city);
        provinceService.updateProvince(province);
        return ResponseEntity.ok(city);
    }

    @PutMapping("/provinces/{provinceId}/cities/{cityId}")
    public ResponseEntity<City> updateCity(@PathVariable(name = "provinceId") int provinceId,
                                           @PathVariable(name = "cityId") long cityId,
                                           @RequestBody CityRequest cityRequest) {
        Province province = provinceService.getProvinceById(provinceId);

        City city = province.getCities().stream().filter(filterCity -> filterCity.getId() == cityId).findFirst().orElseThrow(() -> {
            throw new NoSuchProvinceExistsException("Province not found with id:" + provinceId);
        });

        city.setName(cityRequest.getName());
        city = cityService.addCity(city);

        return ResponseEntity.ok(city);
    }

    @DeleteMapping("/provinces/{provinceId}/cities/{cityId}")
    public ResponseEntity<City> deleteCity(@PathVariable(name = "provinceId") int provinceId,
                                               @PathVariable(name = "cityId") int cityId) {
        Province province = provinceService.getProvinceById(provinceId);

        province.getCities().removeIf(city -> city.getId() == cityId);

        provinceService.updateProvince(province);

        City city = cityService.deleteCity(cityId);

        return ResponseEntity.ok(city);
    }

    @GetMapping("/provinces/{provinceId}/cities/{cityId}/zones")
    public ResponseEntity<Map<String, Object>> getZones(@PathVariable(name = "provinceId") int provinceId,
                                                        @PathVariable(name = "cityId") int cityId,
                                                        @RequestParam(required = false, defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "50") int pageSize) {
        Province province = provinceService.getProvinceById(provinceId);
        City city = cityService.getCity(cityId);

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Zone> zones = zoneService.getZones(province,city,pageable);

        return ResponseEntity.ok(toResponse(zones));
    }

    @GetMapping("/provinces/{provinceId}/cities/{cityId}/zones/{zoneId}")
    public ResponseEntity<Zone> getZones(@PathVariable(name = "provinceId") int provinceId,
                                         @PathVariable(name = "cityId") long cityId,
                                         @PathVariable(name = "zoneId") long zoneId) {
        Zone zone = provinceService.getProvinceById(provinceId).getCities()
                .stream().filter(city -> city.getId() == cityId).findFirst().orElseThrow(() -> {
                    throw new NoSuchCityException();
                }).getZones().stream().filter(zoneFilter -> zoneFilter.getId() == zoneId).findFirst().orElseThrow(() -> {
                    throw new NoSuchZoneExistsException("No Such Zone exists in ths city with zoneId: "+zoneId);
                });

        return ResponseEntity.ok(zone);
    }

    @PostMapping("/provinces/{provinceId}/cities/{cityId}/zones")
    public Zone addZone(@PathVariable(name = "provinceId") int provinceId,
                        @PathVariable(name = "cityId") long cityId,
                        @RequestBody ZoneRequest zoneRequest) {

        City city = provinceService.getProvinceById(provinceId).getCities().stream().filter(cityFilter -> cityFilter.getId() == cityId).findFirst().orElseThrow(() -> {
            throw new NoSuchCityException();
        });

        Zone zone = new Zone();
        zone.setName(zoneRequest.getName());
        zone.setDescription(zoneRequest.getDescription());
        zone.setCity(city);
        zone = zoneService.addZone(zone);
        return zone;

    }

    @PutMapping("/provinces/{provinceId}/cities/{cityId}/zones/{zoneId}")
    public Zone update(@PathVariable(name = "provinceId") int provinceId,
                       @PathVariable(name = "cityId") long cityId,
                       @PathVariable(name = "zoneId") long zoneId,
                       @RequestBody ZoneRequest zoneRequest) {

        Zone zone = provinceService.getProvinceById(provinceId)
                .getCities()
                .stream()
                .filter(cityFilter -> cityFilter.getId() == cityId)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchCityException();
                })
                .getZones().stream().filter(zoneFilter -> zoneFilter.getId() == zoneId)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchZoneExistsException("No Such zone exists with id: " + zoneId);
                });


        zone.setName(zoneRequest.getName());
        zone.setDescription(zoneRequest.getDescription());

        zone = zoneService.updateZone(zoneId, zone);

        return zone;
    }

    @DeleteMapping("/provinces/{provinceId}/cities/{cityId}/zones/{zoneId}")
    public Zone deleteZone(@PathVariable(name = "provinceId") int provinceId,
                       @PathVariable(name = "cityId") long cityId,
                       @PathVariable(name = "zoneId") long zoneId) {

        Zone zone = provinceService.getProvinceById(provinceId)
                .getCities()
                .stream()
                .filter(cityFilter -> cityFilter.getId() == cityId)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchCityException();
                })
                .getZones().stream().filter(zoneFilter -> zoneFilter.getId() == zoneId)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchZoneExistsException("No Such zone exists with id: " + zoneId);
                });


        return zoneService.deleteZone(zone.getId());

    }


    private Map<String, Object> toResponse(Page<?> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent());
        response.put("page", page.getPageable().getPageNumber());
        response.put("pageSize", page.getPageable().getPageSize());
        response.put("totalPages", page.getTotalPages());
        response.put("totalElements", page.getTotalElements());
        return response;
    }
}
