package drinking.water.domain.waterweb;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WaterForm {
    // 물 목표량 등록
    int userId;
    int goal;
    int capacity;
}
