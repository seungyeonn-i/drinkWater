package drinking.water.repository;

import drinking.water.domain.Water;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Repository
public class JdbcWaterRepository implements WaterRepository {


    private final JdbcTemplate jdbcTemplate;

    public JdbcWaterRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Water save(Water water) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Water").usingGeneratedKeyColumns("waterId");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("userId", water.getUserId());
        parameters.put("capacity", water.getCapacity());
        parameters.put("goal", water.getGoal());
        parameters.put("remainCup", water.getRemainCup());
        parameters.put("status", water.getStatus());


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        water.setUserId(key.intValue());

        return water;
    }

    @Override
    public Water update(Water water) {
//        water.setUserId(water.getUserId());
//        store.put(water.getUserId(), water);
//        return water;



        // status 변경
        jdbcTemplate.update("update Water set status=? where userId=?", water.getStatus(), water.getUserId());
        // capacity 변경 -> 쿼리 한번에 진행하는 방법 고안
        jdbcTemplate.update("update Water set capacity=? where userId=?", water.getCapacity(), water.getUserId());


        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Drink").usingColumns("userId","waterId","ts","drinkHow"); ///????


        Map<String, Object> parameters = new HashMap<>();
        Map<Date, Integer> drinkCnt = water.getDrinkCnt();


        for (Date date : drinkCnt.keySet()) {
            parameters.put("userId", water.getUserId());
            parameters.put("waterId", water.getWaterId());

            parameters.put("ts", Timestamp.valueOf(LocalDateTime.now()));
            parameters.put("drinkHow", drinkCnt.get(date).intValue());

            log.info("ts={},drinkHow={}", date, drinkCnt.get(date).intValue());

            jdbcInsert.execute(new MapSqlParameterSource(parameters));

        } // 계속 저장되는지 확인


        return findById(water.getUserId()).get() ;
    }

    @Override
    public Optional<Water> findById(int userId) {

        List<Water> result = jdbcTemplate.query("select * from Water where userId = ?", waterRowMapper(), userId);
        log.info(result.toString());
        return Optional.ofNullable(result.get(0));
    }

    @Override
    public List<Map<Integer, Integer>> findByIdDrink(int userId) {
        return null;
    }

    private RowMapper<Water> waterRowMapper() {
        return (rs,rowNum) -> {
            Water water = new Water();

            water.setUserId(rs.getInt("userId"));
            water.setCapacity(rs.getInt("capacity"));
            water.setGoal(rs.getInt("goal"));
            water.setStatus(rs.getInt("status"));
            water.setRemainCup(rs.getInt("remainCup"));



//            water.setDrinkCnt((Map<Integer, Integer>)rs.getObject(1));
            return water;
        };
    }
}
