package drinking.water.service;

import drinking.water.domain.Water;
import drinking.water.domain.User;
import drinking.water.entity.DrinkStatus;
import drinking.water.repository.*;
import drinking.water.domain.waterweb.WaterForm;
import drinking.water.domain.waterweb.WaterReq;
import drinking.water.domain.waterweb.WaterRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WaterServiceImpl implements WaterService {

    private final WaterRepository waterRepository;
    private final UserRepository userRepository;


    // waterall userid 따라서

    @Override
    public WaterRes join(WaterForm waterForm) {

        User findUser = userRepository.findById(waterForm.getUserId()).get();
        Water newWater = new Water(findUser, waterForm.getCapacity(), waterForm.getGoal());

        waterRepository.save(newWater);

        return setWaterRes(newWater);
    }

    @Override
    public WaterRes update(WaterReq waterReq) {

        Water findWater = waterRepository.findById(waterReq.getWaterId()).get();

        findWater.setCupSize(waterReq.getCapacity());
        setMyStatusService(findWater, waterReq);

        setMyRemainCup(findWater);
        if (findWater.getGoal() < findWater.getCurrent()) {
            log.info("finish!");
        }
        waterRepository.save(findWater);

        return setWaterRes(findWater);

    }


    public List<DrinkStatus> findAll(Long userId){
        return waterRepository.findAllByUserId(userId);
    }


//    @Override
//    public Map<Integer, Integer> drinkCnt(int userId) {ㄴ
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
        DrinkStatus newDrinkStatus = new DrinkStatus();
        newDrinkStatus.setDrinkDate(LocalDateTime.now());
        newDrinkStatus.setHow(waterReq.getCapacity() * waterReq.getCnt());

        water.getDrinkStatus().add(newDrinkStatus);


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
