package drinking.web;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Getter
@Setter
public class WaterReq {

    int userId;
    int waterId;

    int capacity;
    int cnt;

}
