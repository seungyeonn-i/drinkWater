package drinking.water.service;

import drinking.water.domain.Water;
import drinking.water.domain.User;
import drinking.water.repository.WaterRepository;
import drinking.water.domain.waterweb.WaterForm;
import drinking.water.domain.waterweb.WaterReq;
import drinking.water.domain.waterweb.WaterRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WaterServiceImpl implements WaterService {

    private final WaterRepository waterRepository;


    // waterall userid 따라서

    @Override
    public WaterRes join(WaterForm waterForm) {

        Water water = new Water();
//        water.setUser(new User().setUserId(waterForm.getUserId()););
//        water.setUser(new User((long) waterForm.getUserId()));
        water.setUser(new User(waterForm.getUserId()));
        water.setGoal(waterForm.getGoal());
        water.setCupSize(waterForm.getCapacity());

        waterRepository.save(water);
        log.info("join done.");

        return setWaterRes(water);

    }

    @Override
    public WaterRes update(WaterReq waterReq) {

        Water water = waterRepository.findById(waterReq.getUserId()).get();

        water.setCupSize(waterReq.getCapacity());
        setMyStatusService(water, waterReq);

            setMyRemainCup(water);
            if (water.getGoal() < water.getCurrent()) {
                log.info("finish!");
            }


            log.info("update done.");
            log.info("status capacity: " + water.getCupSize());
            log.info("remain cups : " + water.getRemainCup());

            waterRepository.update(water);

            return setWaterRes(water);


    }

//    @Override
//    public Map<Integer, Integer> drinkCnt(int userId) {
//        return ;
//    }

    WaterRes setWaterRes(Water water) {
        WaterRes waterRes = new WaterRes();

        waterRes.setGoal(water.getGoal());
        waterRes.setRemainPercent(setMyRemainPercent(water));
        waterRes.setRemainCup(water.getRemainCup());
        waterRes.setStatus(water.getCurrent());
//        waterRes.setUserId(water.getUser().getUserId());
        waterRes.setUserId(1L);

        return waterRes;
    }


    double setMyRemainPercent(Water water) {
        return  (double) ((double) water.getCurrent() / (double) water.getGoal() * 100);
    }


    int setMyStatusService(Water water, WaterReq waterReq) {
        // 먹은 양 업데이트
        water.setCurrent(water.getCurrent() + waterReq.getCapacity() * waterReq.getCnt());

        // 마신 횟수 map 업데이트
//        Map<Date, Integer> drinkCnt = water.getDrinkCnt();
//        drinkCnt.put(new Date(), waterReq.getCapacity() * waterReq.getCnt());
//        log.info(drinkCnt.toString());
//        water.setWaterId(waterReq.getWaterId());


        return water.getCurrent();
    }


    int setMyRemainCup(Water water) {
        log.info("Goal : "+water.getGoal());
        log.info("Status : "+water.getCurrent());

//        if (water.getGoal() - water.getRemainCup() < 0) {
//            log.info("다 마셨다!");
//            return -1;
//        }

        water.setRemainCup((water.getGoal() - water.getCurrent())/water.getCupSize());
        return water.getRemainCup();
    }
}
