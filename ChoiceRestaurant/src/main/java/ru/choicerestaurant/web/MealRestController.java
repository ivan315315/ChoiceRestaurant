package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.choicerestaurant.model.Meal;
import ru.choicerestaurant.service.MealServ;
import ru.choicerestaurant.to.MealTo;

import java.net.URI;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    static final String REST_URL = "/rest/meals";

    @Autowired
    private MealServ mealServ;

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        log.info("get {}", id);
        return mealServ.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> create(@RequestBody MealTo mealTo) {
        Meal created = mealServ.create(mealTo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody MealTo mealTo) {
        mealServ.update(mealTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        mealServ.delete(id);
    }
}
