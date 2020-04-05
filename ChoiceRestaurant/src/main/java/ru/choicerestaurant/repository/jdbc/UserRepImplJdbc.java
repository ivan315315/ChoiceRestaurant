package ru.choicerestaurant.repository.jdbc;

import org.slf4j.Logger;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.RoleRep;
import ru.choicerestaurant.repository.UserRep;
import ru.choicerestaurant.repository.memory.UserRepImplInMem;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class UserRepImplJdbc implements UserRep {
    private static final Logger log = getLogger(UserRepImplInMem.class);
    //public static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);
    public RowMapper<User> ROW_MAPPER;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private RoleRep roleRepImplJdbc;

    public UserRepImplJdbc(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, RoleRep roleRepImplJdbc) {
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.roleRepImplJdbc = roleRepImplJdbc;
        ROW_MAPPER = new UserRepRowMapper(this.roleRepImplJdbc);
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("registered", user.getRegistered())
                .addValue("enabled", user.getEnabled())
                .addValue("role_id", user.getRole().getId());
        if (user.isNew()){
            user.setId(simpleJdbcInsert.executeAndReturnKey(sqlParameterSource).intValue());
        } else {
            if (namedParameterJdbcTemplate.update("UPDATE users " +
                            /*"SET name=:name" +
                            " WHERE id=:id"*/
                            "SET name=:name, email=:email, password=:password, registered=:registered, " +
                            "enabled=:enabled, role_id=:role_id WHERE id=:id"
                    , sqlParameterSource) == 0) {
                return null;
            }
        }
        return user;
    }

    @Override
    public Boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(Integer id) {
        log.debug("id = " + id);
        List<User> users = jdbcTemplate.query("select * from users where id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        log.debug("metod UserRepImplJdbc.getAll");
        return jdbcTemplate.query("select * from users", ROW_MAPPER);
    }
}
