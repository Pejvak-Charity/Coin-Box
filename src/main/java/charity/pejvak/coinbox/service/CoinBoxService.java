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

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CoinBoxService {

    private final CoinBoxRepository coinBoxRepository;
    private final CoinBoxImageRepository coinBoxImageRepository;

    public CoinBox addCoinBox(CoinBox coinBox) {
        return coinBoxRepository.saveAndFlush(coinBox);
    }

    public CoinBox updateCoinBox(int id, CoinBox coinBox) {

        CoinBox oldCoinBox = coinBoxRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchCoinBoxException();
        });

        oldCoinBox.setName(coinBox.getName());
        oldCoinBox.setSize(coinBox.getSize());
        oldCoinBox.setStatus(coinBox.getStatus());

        return coinBoxRepository.saveAndFlush(oldCoinBox);
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
        return coinBoxRepository.findById(coinBoxId).orElseThrow(() -> {
            throw new NoSuchCoinBoxException();
        }).getCoinBoxImages();
    }

    public CoinBoxImage addCoinBoxImage(int coinBoxId, CoinBoxImage coinBoxImage) {
        CoinBox coinBox = getCoinBox(coinBoxId);
        coinBoxImage = coinBoxImageRepository.saveAndFlush(coinBoxImage);

        coinBox.addImage(coinBoxImage);
        coinBoxRepository.saveAndFlush(coinBox);

        return coinBoxImage;

    }
}
