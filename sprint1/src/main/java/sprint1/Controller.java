package sprint1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller {

    @GetMapping("/")
    public String renderIndex() {
        return "index";
    }
    
}