package drinking.water.service;

import drinking.water.domain.User;
import drinking.water.domain.userweb.LoginReq;
import drinking.water.domain.userweb.RegisterForm;
import drinking.water.domain.userweb.UserRes;
import drinking.water.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Boolean multiCheck(String userEmail) {
        List<User> all = userRepository.findAll();
        for (User user : all) {
            if (user.getUserEmail().equals(userEmail)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public UserRes join(RegisterForm registerForm) {
        return userRepository.save(registerForm);

    }

    @Override
    public Optional<User> findOne(String loginId) {

        userRepository.findByLoginId(loginId);

        return Optional.empty();
    }

    @Override
    public UserRes login(LoginReq loginReq) {
        User user = userRepository.findByLoginId(loginReq.getLoginId())
                .filter(m -> m.getLoginPw().equals(loginReq.getLoginPw()))
                .orElse(null);

        return new UserRes(user.getUserId());
    }
}
