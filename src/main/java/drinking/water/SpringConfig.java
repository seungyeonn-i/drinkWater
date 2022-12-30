package drinking.water;

import drinking.water.repository.JdbcWaterRepository;
import drinking.water.repository.MemoryWaterRepository;
import drinking.water.repository.WaterRepository;
import drinking.water.service.WaterService;
import drinking.water.service.WaterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public WaterService waterService() {
        return new WaterServiceImpl(waterRepository());
    }

    @Bean
    public WaterRepository waterRepository() {
        return new JdbcWaterRepository(dataSource);
    }
}
