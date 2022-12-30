package drinking.water.repository;

import drinking.water.domain.Water;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        jdbcInsert.withTableName("Water").usingGeneratedKeyColumns("userId");

        Map<String, Object> parameters = new HashMap<>();

//        parameters.put("userId", water.getUserId());
        parameters.put("capacity", water.getCapacity());
        parameters.put("drinkCnt", water.getDrinkCnt());
        parameters.put("goal", water.getGoal());
        parameters.put("remainCup", water.getRemainCup());
        parameters.put("status", water.getStatus());


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        water.setUserId(key.intValue());

        return water;
    }

    @Override
    public Water update(Water water) {
//        water.setUserId(water.getUserId());
//        store.put(water.getUserId(), water);
//        return water;

        Water find = findById(water.getUserId()).get();


        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Water").usingGeneratedKeyColumns("userId");

        jdbcTemplate.update("update Water set status=? where userId=?", water.getStatus(), water.getUserId());

        return findById(water.getUserId()).get() ;
    }

    @Override
    public Optional<Water> findById(int userId) {

        List<Water> result = jdbcTemplate.query("select * from Water where userId = ?", waterRowMapper(), userId);
        return Optional.ofNullable(result.get(0));
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