package drinking.water.service;

import drinking.water.domain.waterweb.WaterForm;
import drinking.water.domain.waterweb.WaterReq;
import drinking.water.domain.waterweb.WaterRes;
import drinking.water.entity.DrinkStatus;

import java.util.List;
import java.util.Map;

public interface WaterService {
    WaterRes join(WaterForm waterForm);

    WaterRes update(WaterReq waterReq);

//    Map<Integer, Integer> drinkCnt(int userId);
    List<DrinkStatus> findAll(Long userId);

}
