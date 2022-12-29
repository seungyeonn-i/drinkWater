package drinking.water.service;

import drinking.water.domain.waterweb.WaterForm;
import drinking.water.domain.waterweb.WaterReq;
import drinking.water.domain.waterweb.WaterRes;

public interface WaterService {
    WaterRes join(WaterForm waterForm);

    WaterRes update(WaterReq waterReq);
}
