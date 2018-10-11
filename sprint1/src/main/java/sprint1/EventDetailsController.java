package sprint1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api")
class EventDetailsController {

    private final EventDetailsRepository repository;
    
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	EventDetailsController(EventDetailsRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping("/events")
	Iterable<EventDetails> all() {
		return repository.findAll();
	}

	@PostMapping("/events")
	EventDetails newEmployee(@RequestBody EventDetails newEvent) {
		return repository.save(newEvent);
    }

    // Aggregate by property
    
    @GetMapping("/events/date?{startDate}")
    List<EventDetails> getByStartDate(@PathVariable String startDate) throws ParseException {
        return repository.findByStartDate(df.parse(startDate));
    }

    @GetMapping("/events/category?{category}")
    List<EventDetails> getByCategory(@PathVariable String category) {
        return repository.findByCategory(category);
    }

    @GetMapping("/events/organizerId?{organizerId}")
    List<EventDetails> getByOrganizerId(@PathVariable Long organizerId) {
        return repository.findByOrganizerId(organizerId);
    }

    // Single item

	@GetMapping("/events/{id}")
	EventDetails newEvent(@PathVariable Long id) throws EventDetailsNotFoundException {

		return repository.findById(id)
			.orElseThrow(() -> new EventDetailsNotFoundException(id));
	}

	@PutMapping("/events/{id}")
	EventDetails modifyEvent(@RequestBody EventDetails newEvent, @PathVariable Long id) {

		return repository.findById(id)
			.map(event -> {
				event.setName(newEvent.getName());
                event.setCategory(newEvent.getCategory());
                event.setDescription(newEvent.getDescription());
                event.setLocation(newEvent.getLocation());
                //event.setOrganizerId(newEvent.getOrganizerId());
                event.setThumbnailImageURL(newEvent.getThumbnailImageURL());
                event.setImageURL(newEvent.getImageURL());
                event.setStartDate(newEvent.getStartDate());
                event.setEndDate(newEvent.getEndDate());
                event.setTotalTickets(newEvent.getTotalTickets());
                event.setTicketPrice(newEvent.getTicketPrice());
                return repository.save(event);
			})
			.orElseGet(() -> {
				newEvent.setId(id);
				return repository.save(newEvent);
			});
	}

	@DeleteMapping("/employees/{id}")
	void cancelEvent(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
