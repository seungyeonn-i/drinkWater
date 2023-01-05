package drinking.water.service;

import drinking.water.domain.User;
import drinking.water.domain.userweb.RegisterForm;
import drinking.water.domain.userweb.UserRes;

import java.util.Optional;

public interface UserService {

    UserRes join(RegisterForm registerForm);

    Optional<User> findOne(int userId);

}
