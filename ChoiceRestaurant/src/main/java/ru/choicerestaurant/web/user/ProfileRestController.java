package ru.choicerestaurant.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.service.UserServ;
import ru.choicerestaurant.to.UserTo;
import ru.choicerestaurant.util.SecurityUtil;

import java.net.URI;

import static ru.choicerestaurant.util.SecurityUtil.authUserId;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController {
    static final String REST_URL = "/rest/profile";

    @Autowired
    private UserServ userServ;

    //todo don't understand, why do we need another methods (register)

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return userServ.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        userServ.delete(authUserId());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserTo userTo ) {
        User created = userServ.create(userTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo) {
        userServ.update(userTo);
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }
}
