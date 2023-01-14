//package drinking.water.repository;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@Repository
//public class MemoryWaterRepository implements WaterRepository{
//
//    private static Map<Integer, Water> store = new HashMap<>();
//    private static int sequence = 0;
//
//    @Override
//    public Water save(Water water) {
//        water.setUserId(++sequence);
//        store.put(water.getUserId(), water);
//        return water;
//    }
//
//
//    @Override
//    public Water update(Water water) {
//        water.setUserId(water.getUserId());
//        store.put(water.getUserId(), water);
//        return water;
//    }
//
//
//    @Override
//    public Optional<Water> findById(int userId) {
//        return Optional.ofNullable(store.get(userId));
//    }
//
//    @Override
//    public List<Map<Integer, Integer>> findByIdDrink(int userId) {
//        return null;
//    }
//
//
//}
