package sprint1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

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
    public String add(
        @RequestParam(name="name", required=true)
        String name,
        @RequestParam(name="category", required=false, defaultValue="")
        String category,
        @RequestParam(name="description", required=false, defaultValue="")
        String description,
        @RequestParam(name="location", required=false, defaultValue="")
        String location,
        @RequestParam(name="organizerId", required=false, defaultValue="0")
        long organizerId,
        @RequestParam(name="thumbnailImageURL", required=false, defaultValue="")
        URL thumbnailImageURL,
        @RequestParam(name="imageURL", required=false, defaultValue="")
        URL imageURL,
        @RequestParam(name="startDate", required=true)
        Date startDate,
        @RequestParam(name="endDate", required=true)
        Date endDate,
        @RequestParam(name="totalTickets", required=true)
        int totalTickets,
        @RequestParam(name="ticketPrice", required=true)
        float ticketPrice,
        Model model) throws MalformedURLException, ParseException {

        model.addAttribute("name", name);
        model.addAttribute("category", category);
        model.addAttribute("description", description);
        model.addAttribute("location", location);
        model.addAttribute("organizerId", organizerId);
        model.addAttribute("thumbnailImageURL", thumbnailImageURL);
        model.addAttribute("imageURL", imageURL);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("totalTickets", totalTickets);
        model.addAttribute("ticketPrice", ticketPrice);
        
        repository.save(new EventDetails(name, category, description, location, organizerId, thumbnailImageURL, imageURL, startDate, endDate, totalTickets, ticketPrice));
        return "success";
    }

    @GetMapping("/modify-event")
    public String modify(
        @RequestParam(name="id", required=true)
        long id,
        @RequestParam(name="name", required=true)
        String name,
        @RequestParam(name="category", required=false, defaultValue="")
        String category,
        @RequestParam(name="description", required=false, defaultValue="")
        String description,
        @RequestParam(name="location", required=false, defaultValue="")
        String location,
        @RequestParam(name="organizerId", required=false, defaultValue="0")
        long organizerId,
        @RequestParam(name="thumbnailImageURL", required=false, defaultValue="")
        URL thumbnailImageURL,
        @RequestParam(name="imageURL", required=false, defaultValue="")
        URL imageURL,
        @RequestParam(name="startDate", required=true)
        Date startDate,
        @RequestParam(name="endDate", required=true)
        Date endDate,
        @RequestParam(name="totalTickets", required=true)
        int totalTickets,
        @RequestParam(name="ticketPrice", required=true)
        float ticketPrice,
        Model model) throws MalformedURLException, ParseException {

        model.addAttribute("id", id);
        try {
            repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return "failed";
        }
        model.addAttribute("name", name);
        model.addAttribute("category", category);
        model.addAttribute("description", description);
        model.addAttribute("location", location);
        model.addAttribute("organizerId", organizerId);
        model.addAttribute("thumbnailImageURL", thumbnailImageURL);
        model.addAttribute("imageURL", imageURL);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("totalTickets", totalTickets);
        model.addAttribute("ticketPrice", ticketPrice);

        repository.save(new EventDetails(name, category, description, location, organizerId, thumbnailImageURL, imageURL, startDate, endDate, totalTickets, ticketPrice));
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

    @GetMapping("/show-by-category")
    public String showByCategory(@RequestParam(name = "category", required = true) String category, Model model)
            throws JsonProcessingException, ParseException {
        model.addAttribute("category", category);
        return mapper.writeValueAsString(repository.findByCategory(category));
    }
    @GetMapping("/show-by-organizer-id")
    public String showByOrganizerId(@RequestParam(name="organizerId", required=true) long organizerId, Model model)
            throws JsonProcessingException {
        model.addAttribute("organizerId", organizerId);
        return mapper.writeValueAsString(repository.findByOrganizerId(organizerId));
    }

    @GetMapping("/cancel-event")
    public void cancelEvent(@RequestParam(name="id", required=true) long id, Model model)
            throws JsonProcessingException {
        model.addAttribute("id", id);
        repository.delete(repository.findById(id).get());
    }
}