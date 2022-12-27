package drinking.water;

import drinking.water.repository.MemoryWaterRepository;
import drinking.water.repository.WaterRepository;
import drinking.water.service.WaterService;
import drinking.water.service.WaterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public WaterService waterService() {
        return new WaterServiceImpl(waterRepository());
    }

    @Bean
    public WaterRepository waterRepository() {
        return new MemoryWaterRepository();
    }
}
