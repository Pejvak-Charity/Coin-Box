package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchCoinBoxException;
import charity.pejvak.coinbox.model.CoinBox;
import charity.pejvak.coinbox.model.CoinBoxImage;
import charity.pejvak.coinbox.repository.CoinBoxImageRepository;
import charity.pejvak.coinbox.repository.CoinBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CoinBoxService {

    private final CoinBoxRepository coinBoxRepository;
    private final CoinBoxImageRepository coinBoxImageRepository;

    public CoinBox addCoinBox(CoinBox coinBox) {
        return coinBoxRepository.saveAndFlush(coinBox);
    }

    public CoinBox updateCoinBox(CoinBox coinBox) {
        return coinBoxRepository.saveAndFlush(coinBox);
    }

    public CoinBox deleteCoinBox(int id) {
        CoinBox coinBox = coinBoxRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchCoinBoxException();
        });
        coinBoxRepository.delete(coinBox);

        return coinBox;
    }

    public Page<CoinBox> getCoinBoxes(Pageable pageable) {
        return coinBoxRepository.findAll(pageable);
    }

    public CoinBox getCoinBox(int id) {
        return coinBoxRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchCoinBoxException();
        });
    }

    public Set<CoinBoxImage> getCoinBoxImages(int coinBoxId) {
        //fixme
//        return coinBoxRepository.findById(coinBoxId).orElseThrow(() -> {
//            throw new NoSuchCoinBoxException();
//        }).getCoinBoxImages();

    return null;
    }

    public CoinBoxImage addCoinBoxImage(int coinBoxId, CoinBoxImage coinBoxImage) {
        CoinBox coinBox = getCoinBox(coinBoxId);
        coinBoxImage = coinBoxImageRepository.saveAndFlush(coinBoxImage);

//        coinBox.addImage(coinBoxImage);
        coinBoxRepository.saveAndFlush(coinBox);

        return coinBoxImage;

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

