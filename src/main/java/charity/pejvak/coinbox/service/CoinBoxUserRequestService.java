package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.componenet.CoinBoxUserRequestRequest;
import charity.pejvak.coinbox.componenet.NewCoinBoxUserRequest;
import charity.pejvak.coinbox.componenet.CoinBoxUserRequestResponse;
import charity.pejvak.coinbox.model.CoinBoxUserRequest;
import charity.pejvak.coinbox.model.user.User;
import charity.pejvak.coinbox.model.enums.CoinBoxUserRequestStatus;
import charity.pejvak.coinbox.model.enums.CoinBoxUserRequestType;
import charity.pejvak.coinbox.repository.CoinBoxUserRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinBoxUserRequestService {


    private final CoinBoxUserRequestRepository repository;
    
    private final UserService userService;
    
    private final AddressService addressService;
    
    

    public CoinBoxUserRequestResponse newCoinBoxUser(Long userId, NewCoinBoxUserRequest request) {
        User user = userService.getUser(userId);
        
        CoinBoxUserRequest userRequest = CoinBoxUserRequest.builder()
                .user(user)
                .address(addressService.getAddress(request.getAddressId()))
                .status(CoinBoxUserRequestStatus.SUBMITTED)
                .type(CoinBoxUserRequestType.GET_COINBOX)
                .preferredDateTime(request.getPreferredDateTime())
                .coinBoxTypeId(request.getCoinBoxType())
                .build();
        
        repository.save(userRequest);
        return CoinBoxUserRequestResponse.builder().message("Submitted Successfully!").build();
    }
    
    public CoinBoxUserRequestResponse countingRequest(Long id, CoinBoxUserRequestRequest request) {
        return null;
    }

    public CoinBoxUserRequestResponse returningRequest(Long id, CoinBoxUserRequestRequest request) {
        return null;
    }
}
