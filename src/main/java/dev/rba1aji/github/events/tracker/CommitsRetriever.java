package dev.rba1aji.github.events.tracker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.logging.Logger;

public class CommitsRetriever {
    private static final Logger logger = Logger.getLogger(CommitsRetriever.class.getName());

    public static void retrieveAndLog() throws IOException {
        String owner = "rba1aji", repo = "github-events-tracker";
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/" + owner + "/" + repo + "/commits")
                .build();

        String response = new OkHttpClient().newCall(request).execute().body().string();
        JsonNode resNode = new ObjectMapper().readTree(response);

        for (int i = resNode.size() - 1; i >= 0; i--) {
            JsonNode commit = resNode.get(i).get("commit");
            logger.info(
                    "##COMMIT by " + commit.get("author").get("name") +
                            " -> " + resNode.get(i).get("commit").get("message")
            );
        }
    }
}





