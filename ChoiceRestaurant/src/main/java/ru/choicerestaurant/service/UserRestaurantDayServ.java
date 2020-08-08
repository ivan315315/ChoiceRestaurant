package ru.choicerestaurant.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.choicerestaurant.model.UserRestaurantDay;
import ru.choicerestaurant.repository.jpa.UserRestaurantDayRepJpa;
import ru.choicerestaurant.to.UserRestaurantDayTo;
import ru.choicerestaurant.util.exception.NotFoundException;
import ru.choicerestaurant.util.exception.VoteTooLateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.choicerestaurant.util.ValidationUtil.checkNotFoundWithId;
//todo transaction
//@PropertySource("classpath:com/sample/config.xml")
@Service
public class UserRestaurantDayServ {
    private static final Logger log = getLogger(UserRestaurantDayServ.class);

    //todo add depend
    /*@Value(value = "${vote.time.hour}")
    public String Stringhour;*/

    private Integer hour = 11;
    /*@Value(value = "${vote.time.minute}")
    private String Stringminute;*/

    private Integer minute = 0;

    private UserRestaurantDayRepJpa userRestaurantDayRepJpa;
    private UserServImpl userServImpl;
    private RestaurantServ restaurantServ;

    public UserRestaurantDayServ(UserRestaurantDayRepJpa userRestaurantDayRepJpa, UserServImpl userServImpl, RestaurantServ restaurantServ) {
        this.userRestaurantDayRepJpa = userRestaurantDayRepJpa;
        this.userServImpl = userServImpl;
        this.restaurantServ = restaurantServ;
    }

    public UserRestaurantDay create(UserRestaurantDayTo userRestaurantDayTo) {
        log.info("userRestaurantDayTo {}", userRestaurantDayTo);
        LocalDate voteLocalDate = userRestaurantDayTo.getLunchDay();
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        if (voteLocalDate.compareTo(nowLocalDateTime.toLocalDate()) > 0) {
            checkExistVote(userRestaurantDayTo);
            return userRestaurantDayRepJpa.save(getObjectByToObject(userRestaurantDayTo));
        }
        if (voteLocalDate.compareTo(nowLocalDateTime.toLocalDate()) < 0) {
            throw new VoteTooLateException("Wrong day, vote can't be changed");
        }
        if (LocalTime.of(hour, minute, 0).compareTo(nowLocalDateTime.toLocalTime()) > 0) {
            checkExistVote(userRestaurantDayTo);
            return userRestaurantDayRepJpa.save(getObjectByToObject(userRestaurantDayTo));
        }
        throw new VoteTooLateException("End time " + LocalTime.of(hour, minute, 0) + ". It is too late, vote can't be changed");
    }

    public Boolean delete(Integer userRestaurantDayId) throws NotFoundException {
        log.info("delete userRestaurantDay id {}", userRestaurantDayId);
        checkNotFoundWithId(userRestaurantDayRepJpa.delete(userRestaurantDayId), userRestaurantDayId);
        //todo check day
        return true;
    }

    public UserRestaurantDay getObjectByToObject(UserRestaurantDayTo userRestaurantDayTo) {
        Assert.notNull(userRestaurantDayTo, "mealTo must not be null");
        System.out.println(userRestaurantDayTo);
        return new UserRestaurantDay(userRestaurantDayTo.getId(), userServImpl.get(userRestaurantDayTo.getUser()),
                restaurantServ.get(userRestaurantDayTo.getRestaurant()), userRestaurantDayTo.getLunchDay());
    }

    private void checkExistVote(UserRestaurantDayTo userRestaurantDayTo) {
        UserRestaurantDay userRestaurantDay = userRestaurantDayRepJpa.get(userRestaurantDayTo.getUser(),/* userRestaurantDayTo.getRestaurant(),*/ userRestaurantDayTo.getLunchDay());//???
        if (userRestaurantDay != null) {
            delete(userRestaurantDay.getId());
        }
    }
}
