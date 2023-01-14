package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.NoSuchProvinceExistsException;
import charity.pejvak.coinbox.exception.NoSuchUserExistsException;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User getUserByUsername(String  username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new NoSuchUserExistsException();
        });
    }


    public User getUserByPhoneNumber(String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }
}
