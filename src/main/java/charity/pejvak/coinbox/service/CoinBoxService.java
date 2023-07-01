package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchCoinBoxException;
import charity.pejvak.coinbox.model.CoinBox;
import charity.pejvak.coinbox.repository.CoinBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CoinBoxService {

    private final CoinBoxRepository coinBoxRepository;

    public CoinBox addCoinBox(CoinBox coinBox) {
        return coinBoxRepository.saveAndFlush(coinBox);
    }

    public CoinBox updateCoinBox(CoinBox coinBox) {
        return coinBoxRepository.saveAndFlush(coinBox);
    }

    public CoinBox deleteCoinBox(Long id) {
        CoinBox coinBox = coinBoxRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchCoinBoxException();
        });
        coinBoxRepository.delete(coinBox);

        return coinBox;
    }

    public Page<CoinBox> getCoinBoxes(Pageable pageable) {
        return coinBoxRepository.findAll(pageable);
    }

    public CoinBox getCoinBox(Long id) {
        return coinBoxRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchCoinBoxException();
        });
    }



    public LocalDateTime getLastCountingDate() {
        //todo
        // return truly data
        return LocalDateTime.now();
    }

    public long getLastUserId() {
        //todo complete method
        return 1L;
    }

    public String getLastUserFullName() {
        //todo complete this method and return user full name
        return "علی فرقانی";
    }
}

