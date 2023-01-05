package drinking.water.domain.userweb;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data@Getter@Setter
public class UserRes {
    private int userId;

    public UserRes(int userId) {
        this.userId = userId;
    }

    private String userSession;
}
