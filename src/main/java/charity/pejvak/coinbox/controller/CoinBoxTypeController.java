package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.CoinBoxTypeResponse;
import charity.pejvak.coinbox.componenet.ImageResponse;
import charity.pejvak.coinbox.exception.NoSuchImageFoundException;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

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
            @RequestParam(name = "files") MultipartFile[] multipartFiles
    ) {


        CoinBoxType coinBoxType = new CoinBoxType();
        coinBoxType.setName(name);
        coinBoxType.setSize(size);

        coinBoxType = coinBoxTypeService.addCoinBoxType(coinBoxType);

        Image image;
        File file;
        for (MultipartFile multipartFile : multipartFiles) {
            file = imageService.addNewImageFile(multipartFile);
            image = new Image();
            image.setPath(file.getAbsolutePath());
            image.setCoinBoxType(coinBoxType);
            image = imageService.addImage(image);
            coinBoxType.addImage(image);
            coinBoxType = coinBoxTypeService.updateCoinBoxType(coinBoxType);
        }

        return ResponseEntity.ok(coinBoxTypeDTO(coinBoxType));
    }

    @GetMapping("/coin-box-types/{coinBoxTypeId}")
    public ResponseEntity<CoinBoxTypeResponse> getCoinBoxType(@PathVariable long coinBoxTypeId) {
        CoinBoxType boxType = coinBoxTypeService.getCoinBoxType(coinBoxTypeId);
        return ResponseEntity.ok(coinBoxTypeDTO(boxType));
    }

    @GetMapping("/coin-box-types/{coinBoxTypeId}/images")
    public ResponseEntity<List<ImageResponse>> getCoinBoxImages(@PathVariable long coinBoxTypeId) {

        CoinBoxType coinBoxType = coinBoxTypeService.getCoinBoxType(coinBoxTypeId);
        List<ImageResponse> images = coinBoxType.getImages().stream().map(this::imageDTO).toList();
        return ResponseEntity.ok(images);
    }

    @PostMapping("/coin-box-types/{coinBoxTypeId}/images")
    public ResponseEntity<ImageResponse> addCoinBoxTypeImage(
            @PathVariable long coinBoxTypeId,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        CoinBoxType coinBoxType = coinBoxTypeService.getCoinBoxType(coinBoxTypeId);
        File file = imageService.addNewImageFile(multipartFile);
        Image image = new Image();
        image.setPath(file.getAbsolutePath());
        image.setCoinBoxType(coinBoxType);
        image = imageService.addImage(image);
        coinBoxType.addImage(image);
        coinBoxTypeService.updateCoinBoxType(coinBoxType);

        return ResponseEntity.ok(imageDTO(image));
    }

    @GetMapping("/coin-box-types/{coinBoxTypeId}/images/{imageId}")
    public ResponseEntity<?> getCoinBoxTypeImage(
            @PathVariable long coinBoxTypeId,
            @PathVariable long imageId
    ) {
        Image image = coinBoxTypeService.getCoinBoxType(coinBoxTypeId)
                .getImages().stream().filter(imageFilter -> imageFilter.getId() == imageId)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchImageFoundException();
                });
        return ResponseEntity.ok(imageDTO(image));
    }

    @DeleteMapping("/coin-box-types/{coinBoxTypeId}/images/{imageId}")
    public ResponseEntity<?> deleteCoinBoxTypeImage(
            @PathVariable long coinBoxTypeId,
            @PathVariable long imageId
    ) {
        Image image = coinBoxTypeService.getCoinBoxType(coinBoxTypeId)
                .getImages().stream().filter(imageFilter -> imageFilter.getId() == imageId)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchImageFoundException();
                });
        imageService.deleteImage(image.getId());
        return ResponseEntity.ok(imageDTO(image));
    }


    private Map<String, Object> toResponse(Page<CoinBoxType> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(this::coinBoxTypeDTO).toList());
        response.put("page", page.getPageable().getPageNumber());
        response.put("pageSize", page.getPageable().getPageSize());
        response.put("totalPages", page.getTotalPages());
        response.put("totalElements", page.getTotalElements());
        return response;
    }

    private CoinBoxTypeResponse coinBoxTypeDTO(CoinBoxType coinBoxType) {
        CoinBoxTypeResponse coinBoxTypeResponse = new CoinBoxTypeResponse();
        coinBoxTypeResponse.setId(coinBoxType.getId());
        coinBoxTypeResponse.setName(coinBoxType.getName());
        coinBoxTypeResponse.setSize(coinBoxType.getSize());
        List<ImageResponse> paths = coinBoxType.getImages().stream().map(this::imageDTO).toList();
        coinBoxTypeResponse.setImages(paths);
        return coinBoxTypeResponse;
    }

    private ImageResponse imageDTO(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .downloadURL(imageService.getDownloadPath(image))
                .build();
    }
}
