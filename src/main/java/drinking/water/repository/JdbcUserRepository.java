package drinking.water.repository;

import drinking.water.domain.User;
import drinking.water.domain.userweb.RegisterForm;
import drinking.water.domain.userweb.UserRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserRes save(RegisterForm registerForm) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("User").usingGeneratedKeyColumns("userId");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("userName", registerForm.getUserName());
        parameters.put("userEmail", registerForm.getUserEmail());
        parameters.put("userPhone", registerForm.getUserPhone());
        parameters.put("loginId", registerForm.getLoginId());
        parameters.put("loginPw", registerForm.getLoginPw());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new UserRes(key.intValue());
    }

    @Override
    public Optional<User> findById(int userId) {
        List<User> result = jdbcTemplate.query("select * from User where userId=?", userRowMapper(), userId);
        return Optional.ofNullable(result.get(userId));
//        return Optional.ofNullable(result.get(0));
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        List<User> result = jdbcTemplate.query("select * from User where loginId=?", userRowMapper(), loginId);
        return Optional.ofNullable(result.get(0));
    }


    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from User", userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs,rowNum) -> {
            User user = new User(rs.getLong("user_id"));
//            user.setUserId(rs.getInt("userId"));
            user.setUserName(rs.getString("userName"));
            user.setUserEmail(rs.getString("userEmail"));
            user.setUserPhone(rs.getString("userPhone"));
            user.setLoginId(rs.getString("loginId"));
            user.setLoginPw(rs.getString("loginPw"));

            return user;
        };
    }
}
