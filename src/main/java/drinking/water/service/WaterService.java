package drinking.water.service;

import drinking.water.domain.Water;
import drinking.web.WaterForm;
import drinking.web.WaterReq;
import drinking.web.WaterRes;

public interface WaterService {
    Water join(WaterForm waterForm);

    WaterRes update(WaterReq waterReq);
}
