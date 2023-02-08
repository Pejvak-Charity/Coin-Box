package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.UserRequest;
import charity.pejvak.coinbox.componenet.UserResponse;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getUsers(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "50") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> userPage = userService.getUsers(pageable);
        return ResponseEntity.ok(toResponse(userPage));

    }

    @PostMapping()
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        user = userService.addUser(user);
        return ResponseEntity.ok(userDTO(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(userDTO(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable long userId) {
        User user = userService.deleteUser(userId);
        return ResponseEntity.ok(userDTO(user));
    }


    private Map<String, Object> toResponse(Page<User> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(this::userDTO).collect(Collectors.toSet()));
        response.put("page", page.getPageable().getPageNumber());
        response.put("pageSize", page.getPageable().getPageSize());
        response.put("totalPages", page.getTotalPages());
        response.put("totalElements", page.getTotalElements());
        return response;
    }

    private UserResponse userDTO(User user) {
        return UserResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .nationalCode(user.getNationalCode())
                .role(user.getRole().toString())
                .signUpDate(user.getSignUpDate())
                .build();
    }
}
