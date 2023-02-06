package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchCoinBoxTypeException;
import charity.pejvak.coinbox.model.CoinBoxType;
import charity.pejvak.coinbox.repository.CoinBoxTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinBoxTypeService {

    private final CoinBoxTypeRepository repository;

    public Page<CoinBoxType> getCoinBoxTypes(Pageable pageable){
        return repository.findAll(pageable);
    }

    public CoinBoxType getCoinBoxType(long id){
        return repository.findById(id).orElseThrow(() -> {
            throw new NoSuchCoinBoxTypeException();
        });
    }

}
