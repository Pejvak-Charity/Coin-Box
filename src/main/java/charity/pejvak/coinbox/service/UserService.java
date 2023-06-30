package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchUserExistsException;
import charity.pejvak.coinbox.exception.UserNotFoundException;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.model.enums.Role;
import charity.pejvak.coinbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
            user = userRepository.saveAndFlush(user);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }


    public void updateUser(User user) {
         userRepository.saveAndFlush(user);
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User deleteUser(long userId) {
        User user = getUser(userId);
        userRepository.delete(user);
        return user;

    }
}
