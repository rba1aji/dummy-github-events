package dev.rba1aji.github.events.tracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/api")
public class WebhookController {
    private static final Logger logger = Logger.getLogger(WebhookController.class.getName());

    @PostMapping("/webhook")
    @ResponseStatus(HttpStatus.OK)
    public static String trackEvent(@RequestBody String event) throws JsonProcessingException {
        JsonNode eventDetails = new ObjectMapper().readTree(event);

        if (eventDetails.get("head_commit") != null) {
            logger.info(
                    "##COMMIT by " + eventDetails.get("head_commit").get("author").get("name")
                            + " -> "
                            + eventDetails.get("head_commit").get("message")
            );
        }

        return "Event tracked";
    }

}
