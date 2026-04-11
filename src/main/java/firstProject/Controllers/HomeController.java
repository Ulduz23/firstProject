package firstProject.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "First Project is running";
    }

    @GetMapping("salam")
    public String hello(){
        return "hellooooo";
    }
}
