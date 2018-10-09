package sprint1;

// Reference: https://spring.io/guides/gs/accessing-data-jpa/

//import java.net.URL; // Should we use URL instead of String?
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventDetails {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String location;
    private long organizerId;
    private String headerImageURL; 
    private String mainImageURL;
    private Date startDate;
    private Date endDate;
    private int totalTickets;
    private int ticketsBooked;
    private float ticketPrice;

    // TODO Getters and Setters. Needed?

    protected EventDetails() {} // For JPA

    // TODO Starting with few details.
    public EventDetails(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        // TODO A proper toString() implementation.
        return "[" + name + ", " + startDate + "]";
    }
}