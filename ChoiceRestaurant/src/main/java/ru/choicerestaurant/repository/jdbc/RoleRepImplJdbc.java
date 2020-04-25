package ru.choicerestaurant.repository.jdbc;

import org.slf4j.Logger;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.repository.RoleRep;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class RoleRepImplJdbc implements RoleRep {
    private static final Logger log = getLogger(RoleRepImplJdbc.class);
    public static final BeanPropertyRowMapper<Role> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Role.class);

    private JdbcTemplate jdbcTemplate;

    public RoleRepImplJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role get(Integer id) {
        log.info("get role {}", id);
        List<Role> roles = jdbcTemplate.query("SELECT * FROM roles where id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(roles);
    }

    @Override
    public List<Role> getAll() {
        log.info("get all roles");
        return jdbcTemplate.query("select * from roles", ROW_MAPPER);
    }
}
