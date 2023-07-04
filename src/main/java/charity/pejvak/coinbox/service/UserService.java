package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchUserExistsException;
import charity.pejvak.coinbox.exception.UserNotFoundException;
import charity.pejvak.coinbox.model.enums.UserStatus;
import charity.pejvak.coinbox.model.user.User;
import charity.pejvak.coinbox.model.enums.Role;
import charity.pejvak.coinbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new NoSuchUserExistsException();
        });
    }

    public User addOrGetUserByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(null);
        if (user == null) {
            user = new User();
            user.setUsername(phoneNumber);
            user.setPhoneNumber(phoneNumber);
            user.setRole(Role.USER);
            user.setStatus(getUserStatus(user));
            user.setSignUpDate(LocalDateTime.now());
            user = userRepository.saveAndFlush(user);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    public User addUser(User user) {
        user.setStatus(getUserStatus(user));
        return userRepository.saveAndFlush(user);
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }


    public User updateUser(User user) {
        user.setStatus(getUserStatus(user));
         return userRepository.saveAndFlush(user);
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User deleteUser(long userId) {
        User user = getUser(userId);
        userRepository.delete(user);
        return user;

    }
    private UserStatus getUserStatus(User user) {
        UserStatus status = UserStatus.COMPLETED;
        if (user.getFirstName() == null ||
                user.getLastName() == null ||
                user.getEmail() == null ||
                user.getNationalCode() == null)
            status = UserStatus.NOT_COMPLETED;
        return status;
    }
}
