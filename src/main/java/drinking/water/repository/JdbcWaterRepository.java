package drinking.water.repository;

import drinking.water.domain.Water;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        return null;
    }

    @Override
    public Optional<Water> findById(int userId) {
        return Optional.empty();
    }
}
