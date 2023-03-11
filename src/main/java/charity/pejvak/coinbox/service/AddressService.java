package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchAddressFoundException;
import charity.pejvak.coinbox.model.Address;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final UserService userService;

    private final AddressRepository addressRepository;

    public Page<Address> getUserAddresses(long userId, Pageable pageable) {
        User user = userService.getUser(userId);
        return addressRepository.findAllByUser(user, pageable);
    }

    public Address getAddress(long id) {
        return addressRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchAddressFoundException();
        });
    }

    public Address getAddress(long userId, long addressId) {
        return addressRepository.findByUser_IdAndId(userId, addressId).orElseThrow(() -> {
            throw new NoSuchAddressFoundException();
        });
    }


    public Address addAddress(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    public Address updateAddress(long addressId, Address address) {
        Address oldAddress = getAddress(addressId);

        oldAddress.setProvince(address.getProvince());
        oldAddress.setCity(address.getCity());
        oldAddress.setZone(address.getZone());
        oldAddress.setText(address.getText());
        oldAddress.setZipCode(address.getZipCode());

        return addressRepository.saveAndFlush(oldAddress);
    }

    public Address deleteAddress(long addressId) {
        Address address = getAddress(addressId);
        addressRepository.delete(address);
        return address;
    }

    public Page<Address> getAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }
}
