package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.ProvinceRequest;
import charity.pejvak.coinbox.model.Province;
import charity.pejvak.coinbox.service.CityService;
import charity.pejvak.coinbox.service.ProvinceService;
import charity.pejvak.coinbox.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class AreaController {


    private final ProvinceService provinceService;

    private final CityService cityService;

    private final ZoneService zoneService;

    @GetMapping("/provinces")
    public ResponseEntity<Map<String, Object>> getProvinces(@RequestParam(required = false, defaultValue = "1") int page,
                                                            @RequestParam(required = false, defaultValue = "50") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Province> provincePage = provinceService.getProvinces(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("content", provincePage.getContent());
        response.put("page", provincePage.getPageable().getPageNumber());
        response.put("pageSize", provincePage.getPageable().getPageSize());
        response.put("totalPages", provincePage.getTotalPages());
        response.put("totalElements", provincePage.getTotalElements());
        return ResponseEntity.ok(response);
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
        return provinceService.updatePrivince(provinceId, Province.builder().name(provinceRequest.getName()).build());
    }

    @DeleteMapping("/provinces/{provinceId}")
    public Province deleteProvince(@PathVariable int provinceId) {
        return provinceService.deleteProvince(provinceId);
    }


//    @GetMapping("/provinces/{provinceId}/cities")
//    public Page<City> getCities(@PathVariable(name = "provinceId") int provinceId){
//        Province province = provinceService.getProvinceById(provinceId);
//
//        return cityService.getCities(province,);
//    }
/*
province
    city
        zone


    */


}
