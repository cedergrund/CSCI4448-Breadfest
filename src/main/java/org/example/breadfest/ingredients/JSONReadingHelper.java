package org.example.breadfest.ingredients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class JSONReadingHelper {

    private final List<String> flee_excuses;

    public JSONReadingHelper() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String EXCUSES_FILE_PATH = "src/main/java/org/example/breadfest/dinosaurs/flee_excuses.json";

        Map<String, List<String>> temp = mapper.readValue(new File(EXCUSES_FILE_PATH), new TypeReference<>() {});
        this.flee_excuses = temp.get("excuses");
    }

    public String getRandomExcuse() {
        Random random = new Random();
        int index = random.nextInt(flee_excuses.size());
        return flee_excuses.get(index);
    }

    public static String generateRandomElementFromJSON(Map<String, List<String>> JSON, String type) throws IOException {

        List<String> options = JSON.get(type);
        if (options == null || options.isEmpty()) {
            return "";
        }
        // Choose a random option from the list of dialogue options
        Random random = new Random();
        int index = random.nextInt(options.size());
        return options.get(index);
    }

}
