package dev.rba1aji.github.events.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static dev.rba1aji.github.events.tracker.CommitsRetriever.retrieveAndLog;

@SpringBootApplication
public class GithubEventsTrackerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GithubEventsTrackerApplication.class, args);
		retrieveAndLog();
	}

}
