package drinking.water.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter
public class User {

    private int userId;

    private String userName;
    private String userEmail;
    private String userPhone;

    private String loginPw;
    private String loginId;

}
