package sprint1;

import java.text.SimpleDateFormat;
import java.net.URL;
import java.util.List;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.NoSuchElementException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/event-details/")
public class EventDetailsController {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private EventDetailsRepository repository;

    @GetMapping("/add-new")
    public EventDetails add(
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
        String startDate,
        @RequestParam(name="endDate", required=true)
        String endDate,
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
        
        return repository.save(new EventDetails(name, category, description, location, organizerId, thumbnailImageURL, imageURL, df.parse(startDate), df.parse(endDate), totalTickets, ticketPrice));
    }

    @GetMapping("/modify")
    public boolean modify(
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
        @RequestParam(name="thumbnailImageURL", required=false, defaultValue="")
        URL thumbnailImageURL,
        @RequestParam(name="imageURL", required=false, defaultValue="")
        URL imageURL,
        @RequestParam(name="startDate", required=true)
        String startDate,
        @RequestParam(name="endDate", required=true)
        String endDate,
        @RequestParam(name="totalTickets", required=true)
        int totalTickets,
        @RequestParam(name="ticketPrice", required=true)
        float ticketPrice,
        Model model) throws MalformedURLException, ParseException {

        EventDetails updated;
        model.addAttribute("id", id);
        try {
            updated = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return false;
        }
        model.addAttribute("name", name);
        updated.setName(name);
        model.addAttribute("category", category);
        updated.setCategory(category);
        model.addAttribute("description", description);
        updated.setDescription(description);
        model.addAttribute("location", location);
        updated.setLocation(location);
        model.addAttribute("thumbnailImageURL", thumbnailImageURL);
        updated.setThumbnailImageURL(thumbnailImageURL);
        model.addAttribute("imageURL", imageURL);
        updated.setImageURL(imageURL);
        model.addAttribute("startDate", startDate);
        updated.setStartDate(df.parse(startDate));
        model.addAttribute("endDate", endDate);
        updated.setEndDate(df.parse(endDate));
        model.addAttribute("totalTickets", totalTickets);
        updated.setTotalTickets(totalTickets);
        model.addAttribute("ticketPrice", ticketPrice);
        updated.setTicketPrice(ticketPrice);

        repository.save(updated);
        return true;
    }    
    
    @GetMapping("/show-all")
    public Iterable<EventDetails> showAllEvents() {
        return repository.findAll();
    }
    
    @GetMapping("/show-by-start-date")
    public List<EventDetails> showByStartDate(@RequestParam(name = "startDate", required = true) String startDate, Model model)
            throws ParseException {
        model.addAttribute("startDate", startDate);
        return repository.findByStartDate(df.parse(startDate));
    }

    @GetMapping("/show-by-category")
    public List<EventDetails> showByCategory(@RequestParam(name = "category", required = true) String category, Model model)
            throws ParseException {
        model.addAttribute("category", category);
        return repository.findByCategory(category);
    }

    @GetMapping("/show-by-organizer-id")
    public List<EventDetails> showByOrganizerId(@RequestParam(name="organizerId", required=true) long organizerId, Model model) {
        model.addAttribute("organizerId", organizerId);
        return repository.findByOrganizerId(organizerId);
    }

    @GetMapping("/cancel")
    public void cancelEvent(@RequestParam(name="id", required=true) long id, Model model) {
        model.addAttribute("id", id);
        repository.delete(repository.findById(id).get());
    }
}