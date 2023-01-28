package drinking.water.repository;

import drinking.water.domain.Water;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface WaterRepository {

    Water save(Water water);

    Water update(Water water);

    Optional<Water> findById(Long waterId);

    List<Map<Integer, Integer>> findByIdDrink(int userId);

}
