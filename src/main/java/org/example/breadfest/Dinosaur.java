package org.example.breadfest;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.breadfest.dice.Dice;
import org.example.breadfest.dice.NormalDie;

import java.io.File;

public class Dinosaur {

    private int curr_patience;

    private final String DIALOGUE_FILE_PATH = "dialogues.json";
    private boolean dialogues_loaded;
    private Map<String, List<String>> dialogue;
    Dice die;

    public Dinosaur() {
        this.curr_patience = 40;
        this.dialogues_loaded = false;
        this.die = new NormalDie();
    }


    // load dialogues from JSON file using jackson if not loaded already
    private void loadDialogues() throws IOException {
        if (!this.dialogues_loaded) {
            ObjectMapper mapper = new ObjectMapper();
            dialogue = mapper.readValue(new File(DIALOGUE_FILE_PATH), new TypeReference<Map<String, List<String>>>() {});
            this.dialogues_loaded = true;
        }
    }

    // generate dialogue of specific type
    public String generateDialogueOfType(String type) throws IOException {
        loadDialogues(); // Ensure dialogues are loaded
        List<String> options = dialogue.get(type);
        if (options == null || options.isEmpty()) {
            return "";
        }
        // Choose a random option from the list of dialogue options
        Random random = new Random();
        int index = random.nextInt(options.size());
        return options.get(index);
    }

    public String speak() throws IOException {
        // implement odds of dialogue
        // implements odds of each dialogue type

        return generateDialogueOfType("");
    }

    public int getCurrPatience() {
        return this.curr_patience;
    }

    public boolean changeCurrPatience(int patience_change) {
        this.curr_patience += patience_change;
        return this.curr_patience <= 0;
    }

    public int rollDie(){
        return die.rollDice();
    }
}
