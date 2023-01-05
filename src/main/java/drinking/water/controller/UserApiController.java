package drinking.water.controller;

import drinking.water.domain.userweb.LoginReq;
import drinking.water.domain.userweb.RegisterForm;
import drinking.water.domain.userweb.UserRes;
import drinking.water.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user/signUp")
    @ResponseBody
    public ResponseEntity<UserRes> createUser(@RequestBody RegisterForm registerForm) {
        if (userService.multiCheck(registerForm.getUserEmail())) {
            UserRes join = userService.join(registerForm);
            return new ResponseEntity<>(join, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/user/login")
    @ResponseBody
    public ResponseEntity<UserRes> login(@RequestBody LoginReq loginReq) {
        return new ResponseEntity<>(userService.login(loginReq), HttpStatus.OK);
    }
}
