package ru.choicerestaurant.repository.memory;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.repository.RoleRep;
import ru.choicerestaurant.util.exception.ErrorStructureDataException;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class RoleRepImplInMem implements RoleRep {
    private static final Logger log = getLogger(UserRepImplInMem.class);

    @Override
    public Role get(Integer id) {
        log.info("id = " + id);
        List<Role> roles = MemoryData.roles.stream().filter(role -> role.getId().equals(id)).collect(Collectors.toList());
        //todo my be in service ??
        if (roles.size() > 1) {
            throw new ErrorStructureDataException("id = " + id);
        }
        return roles.stream().findFirst().orElse(null);
    }

    @Override
    public List<Role> getAll() {
        log.info("");
        return MemoryData.roles;
    }
}
