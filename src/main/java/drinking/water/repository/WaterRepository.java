package drinking.water.repository;

import drinking.water.domain.Water;
import drinking.web.WaterReq;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaterRepository {

    Water save(Water water);
    Water update(Water water);

    Optional<Water> findById(int userId);

}
