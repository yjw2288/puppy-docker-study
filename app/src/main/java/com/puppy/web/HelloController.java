package com.puppy.web;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class HelloController {

    private static final int RANDOM = (int) (Math.random() * 100);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }
 
    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public String hello() {
        return "ok";
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public String random() {
        return "random : " + RANDOM;
    }

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public String run() {
        return "run";
    }
}
