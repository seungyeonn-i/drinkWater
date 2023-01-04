package drinking.water.domain.userweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RegisterForm {


    private String userName;
    private String userEmail;
    private String userPhone;

    private String loginId;
    private String loginPw;
}
