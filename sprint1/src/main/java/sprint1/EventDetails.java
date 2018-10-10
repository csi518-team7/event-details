package sprint1;

// Reference: https://spring.io/guides/gs/accessing-data-jpa/

import java.net.URL;
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
    private URL thumbnailImageURL; 
    private URL ImageURL;
    private Date startDate;
    private Date endDate;
    private int totalTickets;
    private int ticketsBooked;
    private float ticketPrice;

    // TODO Getters and Setters. Needed?

    protected EventDetails() {} // For JPA

    // TODO Starting with few details.
    public EventDetails(String name, String description, String location, long organizerId,\
                        URL thumbnailImageURL, URL ImageURL, Date startDate, Date endDate,\
                        int totalTickets, int ticketsBooked, float ticketPrice) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.organizerId = organizerId;
        this.thumbnailImageURL = thumbnailImageURL;
        this.ImageURL = ImageURL;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalTickets = totalTickets;
        this.ticketsBooked = ticketsBooked;
        this.ticketPrice = ticketPrice;
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public long getOrganizerId() {
        return this.organizerId;
    }
    
    public URL getThumbnailImageURL() {
        return this.thumbnailImageURL;
    }
    
    public URL getImageURL() {
        return this.mainImageURL;
    }
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public Date getEndDate() {
        return this.endDate;
    }
    
    public int getTotalTickets() {
        return this.totalTickets;
    }
    
    public int getTicketsBooked() {
        return this.ticketsBooked;
    }
    
    public float getTicketPrice() {
        return this.ticketPrice;
    }

    @Override
    public String toString() {
        // TODO A proper toString() implementation.
        return "[" + this.name + ", " + this.startDate + "]";
    }
}