package drinking.water.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
public class Water {

    int userId;

    int goal; // 전체 먹을 물의 양
    int status; // 현재 먹은 물의 양
    int capacity; // 컵의 용량

    //    int drinkCnt; // 물 먹은 횟수
    Map<Date, Integer> drinkCnt = new HashMap<>();
    // Map<waterId,waters>
//
    int remainCup; // 남은 물 컵 수
    int waterId;
//    int remainPercent;


    // goal / capacity = 먹어야하는 물 컵 수
    // 물을 먹으면 drinkCnt++ , status++

    // status = goal - capacity * drinkCnt;

    // cups = ( goal - status ) / capacity
}
