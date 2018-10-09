package sprint1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventDetailsController {

    @GetMapping("/add")
    public String add() {
        return "Add";
    }
    
}