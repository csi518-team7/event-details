package sprint1;

public class EventDetailsNotFoundException extends Exception {

    private static final long serialVersionUID = 8869948598074378612L;
    private Long id;

    public EventDetailsNotFoundException(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return "EventDetails with id " + id + " not found.";
    }

}