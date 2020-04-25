package ru.choicerestaurant.util;

import ru.choicerestaurant.model.BaseEntity;
import ru.choicerestaurant.util.exception.NotFoundException;

public class ValidationUtil {
    public static <T> T checkNotFoundWithId(T object, Integer id){
        if (object == null){
            throw new NotFoundException("Not found entity with id" + id);
        }
        return object;
    }

    public static void checkNotFoundWithId(Boolean found, Integer id) {
        if (found == false){
            throw new NotFoundException("Not found entity with id" + id);
        }
    }

    public static void checkNew(BaseEntity entity){
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }

    }

    public static void assureIdConsistent(BaseEntity entity, int id) {
//      conservative when you reply, but accept liberally todo read (http://stackoverflow.com/a/32728226/548473)
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}
