package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.componenet.CoinBoxUserResponse;
import charity.pejvak.coinbox.repository.CoinBoxUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinBoxUserService {

    private final CoinBoxUserRepository repository;

    private final CoinBoxService coinBoxService;

    public List<CoinBoxUserResponse> getCoinBoxUser(Long userId) {
        return repository.findByUserId(userId).stream().map(coinBoxUser -> CoinBoxUserResponse.builder()
                .coinBoxId(coinBoxUser.getCoinBoxId())
                .coinBoxTitle(coinBoxService.getCoinBox(coinBoxUser.getCoinBoxId()).getCode())
                .coinBoxAmount(2500000L)
                .build()
        ).toList();
    }
}
