package org.example.breadfest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class JSONReading {


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
