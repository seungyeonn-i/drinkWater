package drinking.water.repository;

import drinking.water.domain.Water;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaWaterRepository extends JpaRepository<Water, Long> {
}
