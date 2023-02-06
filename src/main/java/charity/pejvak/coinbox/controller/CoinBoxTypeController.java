package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.model.CoinBoxType;
import charity.pejvak.coinbox.model.Image;
import charity.pejvak.coinbox.service.CoinBoxTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class CoinBoxTypeController {

    private final CoinBoxTypeService coinBoxTypeService;


    @GetMapping("/coin-box-types")
    public ResponseEntity<Map<String, Object>> getCoinBoxes(@RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<CoinBoxType> coinBoxTypePage = coinBoxTypeService.getCoinBoxTypes(pageable);
        return ResponseEntity.ok(toResponse(coinBoxTypePage));
    }

    @PostMapping("/coin-box-types")
    public ResponseEntity<CoinBoxType> addNewCoinBoxType(@RequestParam(name = "file") MultipartFile[] request,@RequestParam(name = "test") String s){

        System.out.println("---------");
        for (MultipartFile multipartFile : request) {
            System.out.println("multipartFile = " + multipartFile.getName());
            System.out.println(multipartFile.getOriginalFilename());
        }
        System.out.println("-------------");
        System.out.println("s = " + s);
        List<Image> images = new ArrayList<>();


//
//
//        CoinBoxType coinBoxType = new CoinBoxType();
//        coinBoxType.set
//        CoinBoxType coinBoxType = coinBoxTypeService.addCoinBoxType()
        return null;
    }



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
