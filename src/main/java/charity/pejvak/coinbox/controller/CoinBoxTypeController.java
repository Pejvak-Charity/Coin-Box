package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.CoinBoxTypeResponse;
import charity.pejvak.coinbox.model.CoinBoxType;
import charity.pejvak.coinbox.model.Image;
import charity.pejvak.coinbox.service.CoinBoxTypeService;
import charity.pejvak.coinbox.service.ImageService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class CoinBoxTypeController {

    private final CoinBoxTypeService coinBoxTypeService;

    private final ImageService imageService;


    @GetMapping("/coin-box-types")
    public ResponseEntity<Map<String, Object>> getCoinBoxes(@RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<CoinBoxType> coinBoxTypePage = coinBoxTypeService.getCoinBoxTypes(pageable);
        return ResponseEntity.ok(toResponse(coinBoxTypePage));
    }

    @PostMapping("/coin-box-types")
    public ResponseEntity<CoinBoxTypeResponse> addNewCoinBoxType(
            @RequestParam(name = "name") @NotBlank String name,
            @RequestParam(name = "size") @NotBlank String size,
            @RequestParam(name = "file") MultipartFile[] multipartFiles
    ) {

        Set<Image> images = Arrays.stream(multipartFiles).map(imageService::addNewImage).collect(Collectors.toSet());
        CoinBoxType coinBoxType = new CoinBoxType();
        coinBoxType.setName(name);
        coinBoxType.setSize(size);
        coinBoxType.setImages(images);

        coinBoxType = coinBoxTypeService.addCoinBoxType(coinBoxType);

        return ResponseEntity.ok(dto(coinBoxType));
    }

    @GetMapping("/coin-box-types/{coinBoxTypeId}")
    public ResponseEntity<CoinBoxTypeResponse> getCoinBoxType(@PathVariable long coinBoxTypeId) {
        CoinBoxType boxType = coinBoxTypeService.getCoinBoxType(coinBoxTypeId);
        return ResponseEntity.ok(dto(boxType));
    }

    //todo add images endpoint (get and delete and post)


    private Map<String, Object> toResponse(Page<CoinBoxType> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(this::dto).toList());
        response.put("page", page.getPageable().getPageNumber());
        response.put("pageSize", page.getPageable().getPageSize());
        response.put("totalPages", page.getTotalPages());
        response.put("totalElements", page.getTotalElements());
        return response;
    }

    private CoinBoxTypeResponse dto(CoinBoxType coinBoxType) {
        CoinBoxTypeResponse coinBoxTypeResponse = new CoinBoxTypeResponse();
        coinBoxTypeResponse.setId(coinBoxType.getId());
        coinBoxTypeResponse.setName(coinBoxType.getName());
        coinBoxTypeResponse.setSize(coinBoxType.getSize());
        List<String> paths = coinBoxType.getImages().stream().map(imageService::getDownloadPath).toList();
        coinBoxTypeResponse.setImagePaths(paths);
        return coinBoxTypeResponse;
    }
}
