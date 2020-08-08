package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.choicerestaurant.model.Restaurant;
import ru.choicerestaurant.service.RestaurantServ;
import ru.choicerestaurant.to.RestaurantTo;
import ru.choicerestaurant.util.Utils;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    private static final Logger log = getLogger(RestaurantRestController.class);

    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantServ restaurantServ;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantServ.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get {}", id);
        return restaurantServ.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody RestaurantTo restaurantTo) {
        Restaurant created = restaurantServ.create(restaurantTo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody RestaurantTo restaurantTo) {
        restaurantServ.update(restaurantTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        restaurantServ.delete(id);
    }

    @GetMapping("statistic/{dateVote}")
    public Map<Restaurant, Integer> getVoteStatistic(@PathVariable String dateVote) {
        return restaurantServ.getVoteStatistic(Utils.stringDateToLocalDate(dateVote));
    }
}
