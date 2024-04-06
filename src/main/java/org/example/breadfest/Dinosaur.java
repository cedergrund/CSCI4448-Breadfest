package org.example.breadfest;

import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Dinosaur {

    private int curr_patience;

    private final String DIALOGUE_FILE_PATH = "dialogues.json";
    private boolean dialogues_loaded;
    private Map<String, List<String>> dialogue;
    Dice die;

    public Dinosaur(){
        this.curr_patience = 10;
        this.dialogues_loaded = false;
        this.die = new NormalDie();
    }


    // load dialogues from JSON file using jackson
    public void loadDialoguesFromFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        dialogue = mapper.readValue(new File(filePath), HashMap.class);
    }

    // load dialogues from JSON file using jackson if not loaded already
    private void loadDialogues() throws IOException {
        if (!this.dialogues_loaded) {
            ObjectMapper mapper = new ObjectMapper();
            dialogue = mapper.readValue(new File(DIALOGUE_FILE_PATH), HashMap.class);
            this.dialogues_loaded = true;
        }
    }

    // Method to generate a dialogue of a specific type
    public String generateDialogue(String type) {
        List<String> options = dialogueMap.get(type);
        if (options == null || options.isEmpty()) {
            return "No dialogue options available for this type.";
        }
        // Choose a random option from the list of dialogue options
        Random random = new Random();
        int index = random.nextInt(options.size());
        return options.get(index);
    }

    public int getCurrPatience() {
        return this.curr_patience;
    }

    public void setCurrPatience(int curr_patience) {
        this.curr_patience = curr_patience;
    }
}
