package drinking.water.service;

import drinking.water.domain.User;
import drinking.water.domain.userweb.RegisterForm;
import drinking.water.domain.userweb.UserRes;
import drinking.water.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserRes join(RegisterForm registerForm) {
        return userRepository.save(registerForm);
    }

    @Override
    public Optional<User> findOne(int userId) {
        return Optional.empty();
    }
}
