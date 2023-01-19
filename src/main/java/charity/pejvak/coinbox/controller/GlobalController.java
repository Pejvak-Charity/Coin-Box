package charity.pejvak.coinbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class GlobalController {
    @GetMapping("/hi")
    public String sayHello(){
        return "hi";
    }
}
