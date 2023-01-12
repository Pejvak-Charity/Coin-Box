package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.model.Province;
import charity.pejvak.coinbox.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {


    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping()
    public List<Province> getProvinces(){
        return provinceService.getProvinces();
    }

}
