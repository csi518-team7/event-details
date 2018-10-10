package sprint1;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EventDetailsRepository extends CrudRepository<EventDetails, Long> {
    
    List<EventDetails> findByStartDate(Date startDate);

    List<EventDetails> findByCategory(String category);

    List<EventDetails> findByOrganizerId(long organizerId);
}