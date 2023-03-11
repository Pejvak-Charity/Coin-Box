package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.AddressRequest;
import charity.pejvak.coinbox.componenet.AddressResponse;
import charity.pejvak.coinbox.model.*;
import charity.pejvak.coinbox.model.enums.Role;
import charity.pejvak.coinbox.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    private final Logger LOG = LoggerFactory.getLogger(AddressController.class);

    @GetMapping("/addresses")
    public ResponseEntity<Map<String, Object>> getUserAddresses(
            HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        User user = getUser();

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Address> addressPage;
        if (user.getRole().equals(Role.ADMIN))
            addressPage = addressService.getAddresses(pageable);

        else
            addressPage = addressService.getUserAddresses(user.getId(), pageable);


        LOG.info("Connection from : '" + request.getRemoteAddr() + ":" + request.getRemotePort() +
                "' to '/api/v1.0/addresses' at GET.getUserAddresses with userId " + user.getId());

        return ResponseEntity.ok(toResponse(addressPage));
    }



    @PostMapping("/addresses")
    public ResponseEntity<AddressResponse> addAddress(HttpServletRequest request,
                                              @RequestBody AddressRequest addressRequest
    ) {
        Province province = provinceService.getProvinceById(addressRequest.getProvinceId());

        City city = cityService.getCity(addressRequest.getProvinceId(), addressRequest.getCityId());
        Zone zone = zoneService.getZone(province.getId(), city.getId(), addressRequest.getZoneId());

        User user = userService.getUser(addressRequest.getUserId());

        Address address = new Address();

        address.setUser(user);
        address.setText(addressRequest.getText());
        address.setZipCode(address.getZipCode());
        address.setProvince(province);
        address.setCity(city);
        address.setZone(zone);
        address = addressService.addAddress(address);
        user.addAddress(address);
        userService.updateUser(user);

        LOG.info("Connection from : '" + request.getRemoteAddr() + ":" + request.getRemotePort() +
                "' to '/api/v1.0/addresses' at POST.addAddress");

        return ResponseEntity.ok(AddressDTO(address));
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(HttpServletRequest request,
                                                  @PathVariable long addressId,
                                                  @RequestBody AddressRequest addressRequest
    ) {
        Province province = provinceService.getProvinceById(addressRequest.getProvinceId());
        City city = cityService.getCity(addressRequest.getProvinceId(), addressRequest.getCityId());
        Zone zone = zoneService.getZone(province.getId(), city.getId(), addressRequest.getZoneId());

        Address address = addressService.getAddress(addressRequest.getUserId(), addressId);

        address.setProvince(province);
        address.setCity(city);
        address.setZone(zone);
        address.setText(addressRequest.getText());
        address.setZipCode(address.getZipCode());

        LOG.info("Connection from : '" + request.getRemoteAddr() + ":" + request.getRemotePort() +
                "' to '/api/v1.0/addresses/" + addressId + "at PUT.getUserAddress");

        return ResponseEntity.ok(AddressDTO(addressService.updateAddress(addressId, address)));
    }


    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<AddressResponse> deleteUserAddress(HttpServletRequest request,
                                                     @PathVariable long addressId) {

        LOG.info("Connection from : '" + request.getRemoteAddr() + ":" + request.getRemotePort() +
                "' to '/api/v1.0/addresses/" + addressId + "at DELETE.deleteUserAddress");

        return ResponseEntity.ok(AddressDTO(addressService.deleteAddress(addressId)));
    }

    private Map<String, Object> toResponse(Page<Address> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(this::AddressDTO).toList());
        response.put("page", page.getPageable().getPageNumber());
        response.put("pageSize", page.getPageable().getPageSize());
        response.put("totalPages", page.getTotalPages());
        response.put("totalElements", page.getTotalElements());
        return response;
    }

    private static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
    private AddressResponse AddressDTO(Address address){
        return new AddressResponse();
    }
}
