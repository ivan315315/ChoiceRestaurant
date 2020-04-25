package ru.choicerestaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.RoleRep;
import ru.choicerestaurant.util.TimeUtil;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

public class UserRepRowMapper implements RowMapper<User> {

    RoleRep roleRepImplJdbc;

    public UserRepRowMapper(RoleRep roleRepImplJdbc) {
        this.roleRepImplJdbc = roleRepImplJdbc;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        //user.setRegistered(LocalDateTime.of(rs.getDate("registered").toLocalDate(), rs.getTime("registered").toLocalTime()));
        user.setRegistered(TimeUtil.timestampToLdt(rs.getTimestamp("registered")));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setRole(roleRepImplJdbc.get(rs.getInt("role_id")));
        return user;
    }
}
