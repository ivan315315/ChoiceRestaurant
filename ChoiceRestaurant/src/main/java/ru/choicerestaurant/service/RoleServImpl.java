package ru.choicerestaurant.service;

import org.slf4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.repository.RoleRep;
import ru.choicerestaurant.util.ValidationUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class RoleServImpl implements RoleServ {
    private static final Logger log = getLogger(RoleServImpl.class);

    /*@Autowired
    @Resource(type = RoleRepImplJdbc.class)*/
    private RoleRep roleRep;

    public RoleServImpl(RoleRep roleRep) {
        this.roleRep = roleRep;
    }

    @Override
    @Cacheable("roles")
    public Role get(Integer id) {
        log.info("get user {}", id);
        return ValidationUtil.checkNotFoundWithId(roleRep.get(id), id);
    }

   @Override
   @Cacheable("roles")
    public List<Role> getAll() {
        log.info("get all users");
        return roleRep.getAll();
    }
}
