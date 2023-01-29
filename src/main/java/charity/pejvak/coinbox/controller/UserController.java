package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0")
public class UserController {

    public User user;

}
