package drinking.water.domain.waterweb;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WaterReq {

    Long userId;
    Long waterId;

    int capacity;
    int cnt;

}
