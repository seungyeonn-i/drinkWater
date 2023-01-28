package drinking.water;

//import drinking.water.repository.JdbcWaterRepository;
import drinking.water.repository.*;
//import drinking.water.repository.MemoryWaterRepository;
import drinking.water.service.WaterService;
import drinking.water.service.WaterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final DataJpaUserRepository dataJpaUserRepository;
    private final DataJpaWaterRepository dataJpaWaterRepository;

    @PersistenceContext
    EntityManager em;



    public SpringConfig(DataSource dataSource, DataJpaUserRepository dataJpaUserRepository, DataJpaWaterRepository dataJpaWaterRepository) {
        this.dataSource = dataSource;
        this.dataJpaUserRepository = dataJpaUserRepository;
        this.dataJpaWaterRepository = dataJpaWaterRepository;
    }

    @Bean
    public WaterService waterService() {
        return new WaterServiceImpl(waterRepository(), userRepository());
    }

    @Bean
    public WaterRepository waterRepository() {
        return new JpaWaterRepository(em);
    }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(em);
    }
}
