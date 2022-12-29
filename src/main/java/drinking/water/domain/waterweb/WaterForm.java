package drinking.water.domain.waterweb;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WaterForm {
    int goal;
    int capacity;
}
