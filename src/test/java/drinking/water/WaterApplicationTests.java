package drinking.water;

import drinking.water.domain.Water;
import drinking.water.repository.MemoryWaterRepository;
import drinking.water.repository.WaterRepository;
import drinking.water.service.WaterService;
import drinking.web.WaterReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class WaterApplicationTests {

	@Autowired
	WaterService waterService;
	@Autowired
	WaterRepository waterRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void drinkWater() {
//		Water water = new Water();
//
//		water.setGoal(2000);
//		water.setCapacity(250);
//		water.setDrinkCnt(new HashMap<>());
//		water.setStatus(0);
//		water.setRemains(0);
//		water.setUserId(1);
//
//		waterService.join(water);
//
//		WaterReq waterReq = new WaterReq();
//		waterReq.setWaterId(1);
//		waterReq.setUserId(1);
//
//		waterReq.setCapacity(120);
//		waterReq.setCnt(1);
//
//		waterService.update(waterReq);


	}

}
