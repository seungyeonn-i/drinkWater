package drinking.water.domain.waterweb;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WaterReq {

    int userId;
    int waterId;

    int capacity;
    int cnt;

}
