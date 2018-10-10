package sprint1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class EventDetailsController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    private static ObjectMapper mapper = new ObjectMapper();
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    EventDetailsController() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Autowired
    private EventDetailsRepository repository;

    @GetMapping("/add-new-event")
    public String add(@RequestParam(name="name", required=true) String name, Model model) throws ParseException {
        model.addAttribute("name", name);
        repository.save(new EventDetails(name, df.parse("2018-11-22")));
        return "success";
    }
    
    @GetMapping("/show-all-events")
    public String showAllEvents() throws JsonProcessingException {
        return mapper.writeValueAsString(repository.findAll());
    }

    @GetMapping("/show-by-start-date")
    public String showByStartDate(@RequestParam(name = "startDate", required = true) String startDate, Model model)
            throws JsonProcessingException, ParseException {
        model.addAttribute("startDate", startDate);
        return mapper.writeValueAsString(repository.findByStartDate(df.parse(startDate)));
    }

    @GetMapping("/show-by-organizer-id")
    public String showByOrganizerId(@RequestParam(name="organizerId", required=true) long organizerId, Model model)
            throws JsonProcessingException {
        model.addAttribute("organizerId", organizerId);
        return mapper.writeValueAsString(repository.findByOrganizerId(organizerId));
    }
}