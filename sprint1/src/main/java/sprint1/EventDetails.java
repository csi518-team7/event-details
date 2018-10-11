package sprint1;

// Reference 1: https://spring.io/guides/gs/accessing-data-jpa/
// Reference 2: https://spring.io/guides/tutorials/rest/

import java.net.URL;
import java.util.Date;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data // lombok.Data to take care of getters, setters and toString
@Entity
public class EventDetails {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private Long id;
    private String name;
    private String category;
    private String description;
    private String location;
    private long organizerId;
    private URL thumbnailImageURL; 
    private URL imageURL;
    private Date startDate;
    private Date endDate;
    private int totalTickets;
    private int ticketsBooked;
    private double ticketPrice;

    public EventDetails(String name, String category, String description, String location,
                        long organizerId, URL thumbnailImageURL, URL imageURL, Date startDate,
                        Date endDate, int totalTickets, double ticketPrice) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.location = location;
        this.organizerId = organizerId;
        this.thumbnailImageURL = thumbnailImageURL;
        this.imageURL = imageURL;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalTickets = totalTickets;
        this.ticketsBooked = 0;
        this.ticketPrice = ticketPrice;
    }
}