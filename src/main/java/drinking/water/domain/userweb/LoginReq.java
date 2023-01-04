package drinking.water.domain.userweb;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED) // ?
public class LoginReq {

    private String loginId;
    private String loginPw;
}
