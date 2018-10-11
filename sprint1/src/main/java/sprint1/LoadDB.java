package sprint1;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.text.SimpleDateFormat;

@Configuration
@Slf4j
public class LoadDB {

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Bean
	CommandLineRunner initDB(EventDetailsRepository repository) {
		return args -> {
			log.info("Preloading dummy record into DB ..." + repository.save(new EventDetails("Halloween", "Festival", "", "", 0L, new URL("http://localhost/"), new URL("http://localhost/"), df.parse("2018-10-30"), df.parse("2018-10-31"), 1000, 100.00)));
			log.info("Preloading dummy record into DB ..." + repository.save(new EventDetails("Thanksgiving", "Festival", "", "", 0L, new URL("http://localhost/"), new URL("http://localhost/"), df.parse("2018-11-22"), df.parse("2018-11-23"), 1000, 100.00)));
			log.info("Preloading dummy record into DB ..." + repository.save(new EventDetails("Christmas", "Festival", "", "", 0L, new URL("http://localhost/"), new URL("http://localhost/"), df.parse("2018-12-25"), df.parse("2018-12-26"), 1000, 100.00)));
		};
	}
}
