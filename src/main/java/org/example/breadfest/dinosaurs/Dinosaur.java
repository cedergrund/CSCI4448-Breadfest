package org.example.breadfest.dinosaurs;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.breadfest.ingredients.JSONReadingHelper;
import org.example.breadfest.dice.Dice;
import org.example.breadfest.ingredients.Ingredient;

import java.io.File;

public class Dinosaur {

    private final String name;

    private final DinosaurTypes type_of_dinosaur;
    private int curr_patience;
    private final double damage_modifier;

    private boolean dialogues_loaded;
    private Map<String, List<String>> dialogue;
    Dice die;

    Dice reward_die;
    Ingredient reward_ingredient;

    public Dinosaur(String name, DinosaurTypes type_of_dinosaur) throws Exception {
        this.name = name;
        this.dialogues_loaded = false;

        // base stats
        this.type_of_dinosaur = type_of_dinosaur;
        this.curr_patience = type_of_dinosaur.getBasePatience();
        this.damage_modifier = type_of_dinosaur.getDamageModifier();
        this.die = type_of_dinosaur.getDice();

        // seeding dinosaur with rewards after loss
        Random random_seed = new Random();
        double random_roll = random_seed.nextDouble();

        if (random_roll < 0.25){ // no rewards
            this.reward_die = null;
            this.reward_ingredient = null;
        }
        else if (random_roll < 0.5){ // only die reward
            this.reward_die = type_of_dinosaur.getRewardDice();
            this.reward_ingredient = null;
        }
        else if (random_roll < 0.75){
            this.reward_die = null;
            this.reward_ingredient = type_of_dinosaur.getRewardIngredient();
        }
        else{
            this.reward_die = type_of_dinosaur.getRewardDice();
            this.reward_ingredient = type_of_dinosaur.getRewardIngredient();
        }
    }


    // load dialogues from JSON file using jackson if not loaded already
//    private void loadDialogues() throws IOException {
//        if (!this.dialogues_loaded) {
//            ObjectMapper mapper = new ObjectMapper();
//            String DIALOGUE_FILE_PATH = "dialogues.json";
//            dialogue = mapper.readValue(new File(DIALOGUE_FILE_PATH), new TypeReference<>() {});
//            this.dialogues_loaded = true;
//        }
//    }

    // generate dialogue of specific type
//    public String generateDialogueOfType(String type) throws IOException {
//        loadDialogues(); // Ensure dialogues are loaded
//        List<String> options = dialogue.get(type);
//        if (options == null || options.isEmpty()) {
//            return "";
//        }
//        // Choose a random option from the list of dialogue options
//        Random random = new Random();
//        int index = random.nextInt(options.size());
//        return options.get(index);
//    }


//    public String speak() throws IOException {
//        // TODO: implement odds of dialogue
//        // TODO: implements odds of each dialogue type
//
//        return JSONReading.generateRandomElementFromJSON(this.dialogue, "");
//    }

    public int getCurrPatience() {
        return this.curr_patience;
    }

    public boolean changeCurrPatience(int patience_change) {
        this.curr_patience += patience_change;
        return this.curr_patience <= 0;
    }

    public double getDamageModifier(){
        return this.damage_modifier;
    }

    public int rollDie(){
        return die.rollDice();
    }

    public DinosaurTypes getDinosaurType() {
        return this.type_of_dinosaur;
    }

    public Dice getRewardDie(){
        return this.reward_die;
    }

    public Ingredient getRewardIngredient() {
        return this.reward_ingredient;
    }

    public String getName() {
        return name;
    }
}
