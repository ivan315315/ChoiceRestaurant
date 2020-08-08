package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class RootWebController {
    private static final Logger log = getLogger(RootWebController.class);

    @GetMapping("/")
    public String root() {
        log.info("root page");
        return "index";
    }
}
