package drinking.water.service;

import drinking.water.domain.Water;
import drinking.water.repository.WaterRepository;
import drinking.water.domain.waterweb.WaterForm;
import drinking.water.domain.waterweb.WaterReq;
import drinking.water.domain.waterweb.WaterRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaterServiceImpl implements WaterService {

    private final WaterRepository waterRepository;

    @Override
    public WaterRes join(WaterForm waterForm) {
        log.info("join done.");

        Water water = new Water();
        water.setGoal(waterForm.getGoal());
        water.setCapacity(waterForm.getCapacity());
        waterRepository.save(water);

        return setWaterRes(water);

    }

    @Override
    public WaterRes update(WaterReq waterReq) {

        Water water = waterRepository.findById(waterReq.getUserId()).get();

        water.setCapacity(waterReq.getCapacity());
        setMyStatusService(water, waterReq);

            setMyRemainCup(water);
            if (water.getGoal() < water.getStatus()) {
                log.info("finish!");
            }


            log.info("update done.");
            log.info("status capacity: " + water.getCapacity());
            log.info("remain cups : " + water.getRemainCup());

            waterRepository.update(water);

            return setWaterRes(water);


    }

    WaterRes setWaterRes(Water water) {
        WaterRes waterRes = new WaterRes();

        waterRes.setGoal(water.getGoal());
        waterRes.setRemainPercent(setMyRemainPercent(water));
        waterRes.setRemainCup(water.getRemainCup());
        waterRes.setStatus(water.getStatus());
        waterRes.setUserId(water.getUserId());

        return waterRes;
    }


    double setMyRemainPercent(Water water) {
        return  (double) ((double) water.getStatus() / (double) water.getGoal() * 100);
    }


    int setMyStatusService(Water water, WaterReq waterReq) {
        // 먹은 양 업데이트
        water.setStatus(water.getStatus() + waterReq.getCapacity() * waterReq.getCnt());

        // 마신 횟수 map 업데이트
        Map<Integer, Integer> drinkCnt = water.getDrinkCnt();
        drinkCnt.put(waterReq.getWaterId(), waterReq.getCapacity() * waterReq.getCnt());
        log.info(drinkCnt.toString());

        return water.getStatus();
    }


    int setMyRemainCup(Water water) {
        log.info("Goal : "+water.getGoal());
        log.info("Status : "+water.getStatus());

//        if (water.getGoal() - water.getRemainCup() < 0) {
//            log.info("다 마셨다!");
//            return -1;
//        }

        water.setRemainCup((water.getGoal() - water.getStatus())/water.getCapacity());
        return water.getRemainCup();
    }
}
