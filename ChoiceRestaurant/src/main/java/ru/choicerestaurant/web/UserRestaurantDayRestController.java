package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.choicerestaurant.service.UserRestaurantDayServ;
import ru.choicerestaurant.to.UserRestaurantDayTo;
import ru.choicerestaurant.util.Utils;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = UserRestaurantDayRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantDayRestController {
    private static final Logger log = getLogger(UserRestaurantDayRestController.class);

    static final String REST_URL = "/rest/userrestaurantday";

    @Autowired
    private UserRestaurantDayServ userRestaurantDayServ;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody UserRestaurantDayTo userRestaurantDayTo) {
        Utils.checkAuthUser(userRestaurantDayTo.getUser());
        userRestaurantDayServ.create(userRestaurantDayTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userRestaurantDayServ.delete(id);
    }
}
