package drinking.water.repository;

import drinking.water.domain.User;
import drinking.water.domain.Water;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class JpaWaterRepository implements WaterRepository  {


    private final EntityManager em;

    public JpaWaterRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Water save(Water water) {

        em.persist(water);

        em.flush();
        em.clear();

        Water findWater = em.find(Water.class, water.getWaterId());
        System.out.println("getUserId"+findWater.getUser().getUserId());
        System.out.println("getWaterId"+findWater.getWaterId());
        return findWater;
//        return null;
    }


    @Override
    public Water update(Water water) {
        return null;
    }

    @Override
    public Optional<Water> findById(int userId) {
        return Optional.empty();
    }

    @Override
    public List<Map<Integer, Integer>> findByIdDrink(int userId) {
        return null;
    }
}
