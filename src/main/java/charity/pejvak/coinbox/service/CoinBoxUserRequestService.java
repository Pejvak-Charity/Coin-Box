package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchRequestExistsException;
import charity.pejvak.coinbox.model.CoinBoxUserRequest;
import charity.pejvak.coinbox.model.CoinBoxUserRequestLog;
import charity.pejvak.coinbox.model.enums.CoinBoxUserRequestStatus;
import charity.pejvak.coinbox.repository.CoinBoxUserRequestLogRepository;
import charity.pejvak.coinbox.repository.CoinBoxUserRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinBoxUserRequestService {


    private final CoinBoxUserRequestRepository repository;

    private final CoinBoxUserRequestLogRepository logRepository;

    public CoinBoxUserRequest addCoinBoxUserRequest(CoinBoxUserRequest request) {
        return repository.saveAndFlush(request);
    }

    public CoinBoxUserRequest updateStatus(long id, CoinBoxUserRequestStatus status) {
        CoinBoxUserRequest coinBoxUserRequest = getCoinBoxUserRequest(id);
        //todo log status changing
        coinBoxUserRequest.setStatus(status);
        return coinBoxUserRequest;
    }

    public CoinBoxUserRequest getCoinBoxUserRequest(long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoSuchRequestExistsException();
        });
    }
}
