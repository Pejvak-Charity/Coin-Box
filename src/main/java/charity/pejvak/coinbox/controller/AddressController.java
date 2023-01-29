package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.AddressRequest;
import charity.pejvak.coinbox.model.*;
import charity.pejvak.coinbox.service.*;
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
public class AddressController {

    private final AddressService addressService;

    private final ProvinceService provinceService;

    private final CityService cityService;
    private final ZoneService zoneService;

    private final UserService userService;


    @GetMapping("/{userId}/addresses")
    public ResponseEntity<Map<String, Object>> getUserAddresses(
            @PathVariable long userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Address> addressPage = addressService.getUserAddresses(userId, pageable);

        return ResponseEntity.ok(toResponse(addressPage));
    }

    @PostMapping("/{userId}/addresses")
    public ResponseEntity<Address> addAddress(@PathVariable long userId,
                                              @RequestBody AddressRequest addressRequest
    ) {
        Province province = provinceService.getProvinceById(addressRequest.getProvinceId());

        City city = cityService.getCity(addressRequest.getProvinceId(), addressRequest.getCityId());
        Zone zone = zoneService.getZone(province.getId(), city.getId(), addressRequest.getZoneId());

        User user
                = userService.getUser(userId);


        Address address
                = new Address();

        address.setUser(user);
        address.setText(addressRequest.getText());
        address.setZipCode(address.getZipCode());
        address.setProvince(province);
        address.setCity(city);
        address.setZone(zone);
        address = addressService.addAddress(address);
        user.addAddress(address);
        userService.updateUser(user);

        return ResponseEntity.ok(address);
    }

    @GetMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<Address> getUserAddress(
            @PathVariable long userId,
            @PathVariable long addressId,
            @RequestBody AddressRequest addressRequest
    ) {
        Province province = provinceService.getProvinceById(addressRequest.getProvinceId());
        City city = cityService.getCity(addressRequest.getProvinceId(), addressRequest.getCityId());
        Zone zone = zoneService.getZone(province.getId(), city.getId(), addressRequest.getZoneId());


        Address address = addressService.getAddress(userId,addressId);

        address.setProvince(province);
        address.setCity(city);
        address.setZone(zone);
        address.setText(addressRequest.getText());
        address.setZipCode(address.getZipCode());

        return ResponseEntity.ok(addressService.updateAddress(addressId,address));
    }

    @PutMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<Address> updateUserAddress(
            @PathVariable long userId,
            @PathVariable long addressId) {

        return ResponseEntity.ok(addressService.getAddress(userId, addressId));
    }

    @DeleteMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<Address> deleteUserAddress(
            @PathVariable long userId,
            @PathVariable long addressId) {

        return ResponseEntity.ok(addressService.deleteAddress(userId, addressId));
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
