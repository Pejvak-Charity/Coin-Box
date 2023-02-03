package charity.pejvak.coinbox.controller;


import charity.pejvak.coinbox.componenet.CoinBoxRequest;
import charity.pejvak.coinbox.model.CoinBox;
import charity.pejvak.coinbox.model.enums.CoinBoxStatus;
import charity.pejvak.coinbox.service.CoinBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping(value = "/api/v1.0")
@RequiredArgsConstructor
public class CoinBoxController {


    final private CoinBoxService coinBoxService;

    @GetMapping("/coin-boxes")
    public ResponseEntity<Map<String, Object>> getCoinBoxes(@RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<CoinBox> coinBoxes = coinBoxService.getCoinBoxes(pageable);

        return ResponseEntity.ok(toResponse(coinBoxes));
    }

    @PostMapping("/coin-boxes")
    public ResponseEntity<CoinBox> addCoinBox(@RequestBody CoinBoxRequest coinBoxRequest) {
        CoinBox coinBox = new CoinBox();

        coinBox.setName(coinBoxRequest.getName());
        coinBox.setType(coinBoxRequest.getType());
        coinBox.setSize(coinBoxRequest.getSize());
        coinBox.setStatus(CoinBoxStatus.ACTIVE.getCode());
        coinBox = coinBoxService.addCoinBox(coinBox);
        return ResponseEntity.ok(coinBox);
    }

    @GetMapping("/coin-boxes/{coinBoxId}")
    public ResponseEntity<CoinBox> getCoinBox(@PathVariable int coinBoxId) {
        CoinBox coinBox = coinBoxService.getCoinBox(coinBoxId);
        return ResponseEntity.ok(coinBox);
    }

    @PutMapping("/coin-boxes/{coinBoxId}")
    public ResponseEntity<CoinBox> updateCoinBox(@PathVariable int coinBoxId, @RequestBody CoinBox coinBox) {
        CoinBox newCoinBox = coinBoxService.updateCoinBox(coinBoxId, coinBox);
        return ResponseEntity.ok(newCoinBox);
    }

    @DeleteMapping("/coin-boxes/{coinBoxId}")
    public ResponseEntity<CoinBox> deleteCoinBox(@PathVariable int coinBoxId) {
        return ResponseEntity.ok(coinBoxService.deleteCoinBox(coinBoxId));
    }

    //TODO complete coin box images

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
