package drinking.water.domain.waterweb;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter
public class WaterRes {

    int userId;

    int goal;
    int remainCup; // 남은 먹을 물 컵 수
    int status; // 현재 먹은 물의 양

    double remainPercent;


}
