package drinking.water.repository;

import drinking.water.domain.User;
import drinking.water.domain.Water;
import drinking.water.entity.DrinkStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaWaterRepository implements WaterRepository  {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Water save(Water water) {

        em.persist(water);

        em.flush();
        em.clear();

        Water findWater = em.find(Water.class, water.getWaterId());
        System.out.println("getUserId"+findWater.getUser().getUserId());
        System.out.println("getWaterId"+findWater.getWaterId());
        return findWater;
    }


    @Override
    public Water update(Water newWater) {
        em.persist(newWater);

        return newWater;

    }

    @Override
    public Optional<Water> findById(Long waterId) {

//        (em.find(Water.class, userId)).

//        return (em.find(Water.class, userId)).;
        return Optional.ofNullable(em.find(Water.class,waterId));
    }

    @Override
    public List<DrinkStatus> findAllByUserId(Long userId) {

        return em.createQuery("select w.drinkStatus from Water w ,DrinkStatus s where w.user.userId= :userId having s.water.user.userId= :userId", DrinkStatus.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Map<Integer, Integer>> findByIdDrink(int userId) {
        return null;
    }
}
