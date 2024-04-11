package org.example.breadfest;

import org.example.breadfest.dice.Common.NormalDie;
import org.example.breadfest.dice.Dice;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private static final Player player = new Player();

//    private final ArrayList<Ingredient> ingredient_inventory;
    private final Map<String, List<Ingredient>> ingredient_inventory;

    // dice_inventory is all die that player has, while active_dice_inventory are the 3 that they currently use
    private final ArrayList<Dice> dice_inventory;

    private final Dice active_die;

    // base patience is starting patience at every time you enter maze
    private int base_patience;

    // curr patience starts at base patience and then is updated as you lose it, until turn is over
    private int curr_patience;

    // damage modifier in fights
    private double damage_modifier;

    private Player() {
        this.base_patience = 100;
        this.curr_patience = base_patience;
        this.damage_modifier = 1.0;

        this.ingredient_inventory = new HashMap<>();

        this.dice_inventory = new ArrayList<>();
        this.active_die = new NormalDie();
    }

    // singleton player
    public static Player getInstance() {
        return player;
    }

    public boolean changeCurrPatience(int patience_change){
        this.curr_patience += patience_change;
        return this.curr_patience <= 0;
    }

    public int getCurrPatience() {
        return this.curr_patience;
    }

    public void resetPatience(){
        this.curr_patience = this.base_patience;
    }

    public void upgradeBasePatience(int upgrade_amount){
        this.base_patience += upgrade_amount;
    }

    public int rollCurrentDie(){
        return this.active_die.rollDice();
    }

    private void beatDinosaur(Dinosaur dinosaur){
        Ingredient reward_ingredient = dinosaur.getRewardIngredient();
        Dice reward_die = dinosaur.getRewardDie();

        if (reward_ingredient != null){
            String name_of_reward_ingredient = reward_ingredient.getName();
            if (this.ingredient_inventory.containsKey(name_of_reward_ingredient)){
                this.ingredient_inventory.get(name_of_reward_ingredient).add(reward_ingredient);
            }
            else {
                List<Ingredient> new_list = new ArrayList<>();
                new_list.add(reward_ingredient);
                this.ingredient_inventory.put(name_of_reward_ingredient, new_list);
            }

        }
        if (reward_die != null){
            this.dice_inventory.add(reward_die);
            // TODO: address active dice inventory
        }

    }

    private void playerLost(){

    }

    public void fightDinosaur(Dinosaur dinosaur){

        // roll two die
        int player_roll = this.rollCurrentDie();
        int dino_roll = dinosaur.rollDie();

        // compare the two rolls
        int roll_difference = player_roll - dino_roll;

        if (roll_difference >= 5){ // embarrassing win
            // print whatever output
            if (dinosaur.changeCurrPatience((int) (-40*this.damage_modifier))){
                // dino "died", address
                return;
            };
        }
        else if (roll_difference > 0){ // normal win
            // print whatever output
            if (dinosaur.changeCurrPatience((int) (-20*this.damage_modifier))){
                // dino "died", address
                return;
            };
        }
        else if (roll_difference == 0){ // tie
            // print whatever output
            if (this.changeCurrPatience(-10)){
                // player "died", address
                return;
            };
            if (dinosaur.changeCurrPatience(-10)){
                // dino "died", address
                return;
            };
        }
        else if (roll_difference > -5){ // normal loss
            // print whatever output
            if(this.changeCurrPatience(-20)){
                // player "died", address
                return;
            };
        }
        else { // embarrassing loss
            // print whatever output
            if(this.changeCurrPatience(-40)){
                // player "died", address
                return;
            };
        }

        // print any other thing? maybe dino dialogue?
    }

    public void addToDamageModifier(int percent_change){
        this.damage_modifier *= 1 + ((double) percent_change / 100);
    }


    public List<String[]> getIngredientInventory() {

        List<String[]> ingredient_inventory = new ArrayList<>();
        for (String ingredient_name : this.ingredient_inventory.keySet()){
            String[] curr_ingredient_information = new String[4];
            List<Ingredient> ingredient_list_with_correct_name = this.ingredient_inventory.get(ingredient_name);
            Ingredient example_ingredient = ingredient_list_with_correct_name.get(0);

            curr_ingredient_information[0] = String.valueOf(ingredient_list_with_correct_name.size());
            curr_ingredient_information[1] = example_ingredient.getName();
            curr_ingredient_information[2] = example_ingredient.getType().toString();
            curr_ingredient_information[3] = example_ingredient.getRarity().toString();
            ingredient_inventory.add(curr_ingredient_information);
        }
        return ingredient_inventory;
    }

    public void addIngredientToInventory(Ingredient ingredient){

        String name_of_reward_ingredient = ingredient.getName();
        if (this.ingredient_inventory.containsKey(name_of_reward_ingredient)){
            this.ingredient_inventory.get(name_of_reward_ingredient).add(ingredient);
        }
        else {
            List<Ingredient> new_list = new ArrayList<>();
            new_list.add(ingredient);
            this.ingredient_inventory.put(name_of_reward_ingredient, new_list);
        }
    }



}
