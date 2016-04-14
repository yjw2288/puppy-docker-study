package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    private static final int RANDOM = (int) (Math.random() * 100);

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
 
    @RequestMapping("/ok")
    public String hello() {
        return "ok";
    }

    @RequestMapping("/random")
    public String random() {
        return "random : " + RANDOM;
    }
}
