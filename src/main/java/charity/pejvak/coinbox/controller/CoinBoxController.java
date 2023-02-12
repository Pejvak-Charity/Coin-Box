package charity.pejvak.coinbox.controller;


import charity.pejvak.coinbox.componenet.CoinBoxRequest;
import charity.pejvak.coinbox.componenet.CoinBoxResponse;
import charity.pejvak.coinbox.model.CoinBox;
import charity.pejvak.coinbox.model.CoinBoxType;
import charity.pejvak.coinbox.model.enums.CoinBoxStatus;
import charity.pejvak.coinbox.service.CoinBoxService;
import charity.pejvak.coinbox.service.CoinBoxTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/api/v1.0")
@RequiredArgsConstructor
public class CoinBoxController {


    private final CoinBoxService coinBoxService;
    private final CoinBoxTypeService coinBoxTypeService;

    @GetMapping("/coin-boxes")
    public ResponseEntity<Map<String, Object>> getCoinBoxes(@RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<CoinBox> coinBoxes = coinBoxService.getCoinBoxes(pageable);

        return ResponseEntity.ok(toResponse(coinBoxes));
    }

    @PostMapping("/coin-boxes")
    public ResponseEntity<CoinBoxResponse> addCoinBox(@RequestBody CoinBoxRequest coinBoxRequest) {
        CoinBoxType coinBoxType = coinBoxTypeService.getCoinBoxType(coinBoxRequest.getTypeId());

        CoinBox coinBox = new CoinBox();
        coinBox.setType(coinBoxType);
        coinBox.setCode(coinBoxRequest.getCode());
        coinBox.setManufactureDate(coinBoxRequest.getManufactureDate());
        coinBox.setStatus(CoinBoxStatus.ACTIVE.getCode());
        coinBox = coinBoxService.addCoinBox(coinBox);
        return ResponseEntity.ok(coinBoxDTO(coinBox));
    }

    @GetMapping("/coin-boxes/{coinBoxId}")
    public ResponseEntity<CoinBoxResponse> getCoinBox(@PathVariable int coinBoxId) {
        CoinBox coinBox = coinBoxService.getCoinBox(coinBoxId);
        return ResponseEntity.ok(coinBoxDTO(coinBox));
    }

    @PutMapping("/coin-boxes/{coinBoxId}")
    public ResponseEntity<CoinBoxResponse> updateCoinBox(@PathVariable int coinBoxId, @RequestBody CoinBoxRequest coinBoxRequest) {
        CoinBoxType coinBoxType = coinBoxTypeService.getCoinBoxType(coinBoxRequest.getTypeId());
        CoinBox coinBox = coinBoxService.getCoinBox(coinBoxId);
        coinBox.setCode(coinBoxRequest.getCode());
        coinBox.setType(coinBoxType);
        coinBox = coinBoxService.updateCoinBox( coinBox);
        return ResponseEntity.ok(coinBoxDTO(coinBox));
    }

    @DeleteMapping("/coin-boxes/{coinBoxId}")
    public ResponseEntity<CoinBoxResponse> deleteCoinBox(@PathVariable int coinBoxId) {
        CoinBox coinBox = coinBoxService.deleteCoinBox(coinBoxId);
        return ResponseEntity.ok(coinBoxDTO(coinBox));
    }

    private Map<String, Object> toResponse(Page<CoinBox> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(this::coinBoxDTO).collect(Collectors.toSet()));
        response.put("page", page.getPageable().getPageNumber());
        response.put("pageSize", page.getPageable().getPageSize());
        response.put("totalPages", page.getTotalPages());
        response.put("totalElements", page.getTotalElements());
        return response;
    }
    private CoinBoxResponse coinBoxDTO(CoinBox coinBox) {
        CoinBoxResponse coinBoxResponse = new CoinBoxResponse();
        coinBoxResponse.setId(coinBox.getId());
        coinBoxResponse.setCode(coinBox.getCode());
        coinBoxResponse.setTypeId(coinBox.getType().getId());
        coinBoxResponse.setTypeName(coinBox.getType().getName());
        coinBoxResponse.setManufactureDateTime(coinBox.getManufactureDate());
        coinBoxResponse.setLastCountingDateTime(coinBoxService.getLastCountingDate());
        coinBoxResponse.setLastUserId(coinBoxService.getLastUserId());
        coinBoxResponse.setLastUserFullName(coinBoxService.getLastUserFullName());
        return coinBoxResponse;
    }

}
