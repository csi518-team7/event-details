package sprint1;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(EventDetailsRepository repository) {
		return (args) -> {
			repository.save(new EventDetails("Halloween", "", "", 0L, new URL("http://localhost/"), new URL("http://localhost/"), df.parse("2018-10-30"), df.parse("2018-10-31"), 1000, 100.00));
			repository.save(new EventDetails("Thanksgiving", "", "", 0L, new URL("http://localhost/"), new URL("http://localhost/"), df.parse("2018-11-22"), df.parse("2018-11-23"), 1000, 100.00));
			repository.save(new EventDetails("Christmas", "", "", 0L, new URL("http://localhost/"), new URL("http://localhost/"), df.parse("2018-12-25"), df.parse("2018-12-26"), 1000, 100.00));
			
			log.info("EventDetails found with findAll():");
			log.info("-------------------------------");
			for (EventDetails eventDetails : repository.findAll()) {
				log.info(eventDetails.toString());
			}
			log.info("");

			repository.findById(1L)
				.ifPresent(eventDetails -> {
					log.info("EventDetails found with findById(1L):");
					log.info("--------------------------------");
					log.info(eventDetails.toString());
					log.info("");
				});

			log.info("EventDetails found with findByStartDate():");
			log.info("--------------------------------------------");
			repository.findByStartDate(new Date(118, 11, 22)).forEach(dummy -> {
				log.info(dummy.toString());
			});

            log.info("");
		};
	}

}