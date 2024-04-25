package org.example.breadfest;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dice.DieFactory;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredient;
import org.example.breadfest.ingredients.IngredientFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private static final Player player = new Player();

    private final Map<String, List<Ingredient>> ingredient_inventory;

    private final Dice[] active_dice_inventory;

    private Dice potential_die = null;

    private Dinosaur fightingDinosaur = null;

    private String[] previous_reward;

    private int previous_roll;


    // base patience is starting patience at every time you enter maze
    private int base_patience;

    // curr patience starts at base patience and then is updated as you lose it, until turn is over
    private int curr_patience;

    // you curr honor is the honor the player has at any given moment
    private int curr_honor;


    // damage modifier in fights
    private double damage_modifier;

    private Player() {
        this.base_patience = 100;
        this.curr_patience = base_patience;
        this.curr_honor = 0;
        this.damage_modifier = 1.0;

        this.ingredient_inventory = new HashMap<>();

        this.active_dice_inventory = new Dice[3];
        this.active_dice_inventory[0] = DieFactory.generateBaseDie();
        this.active_dice_inventory[1] = null;
        this.active_dice_inventory[2] = null;

        this.previous_roll = 0;
    }



    // singleton player
    public static Player getInstance() {
        return player;
    }

    public boolean changeCurrPatience(int patience_change){
        this.curr_patience += patience_change;
        return this.curr_patience <= 0;
    }

    public int changeCurrHonor(int honor_change){
        this.curr_honor += honor_change;
        if (curr_honor > 250){
            damage_modifier = 2;
            base_patience = 200;
        }
        if (curr_honor > 500){
            damage_modifier = 3;
            base_patience = 400;
        }
        if (curr_honor > 750){
            damage_modifier = 4;
            base_patience = 600;
        }
        if (curr_honor > 750){
            damage_modifier = 5;
            base_patience = 1000;
        }

        return this.curr_honor; //returns the update honor!
    }

    public Dice[] getActiveDieInventory() {
        return this.active_dice_inventory;
    }

    public int getCurrPatience() {
        return this.curr_patience;
    }

    public int getBasePatience() { return this.base_patience; }

    public void resetPatience(){
        this.curr_patience = this.base_patience;
    }

    public void upgradeBasePatience(int upgrade_amount){
        this.base_patience += upgrade_amount;
    }

    public int rollDie(int index_selection){
        return this.active_dice_inventory[index_selection].rollDice();
    }

    public boolean stopFight(boolean fight_won){
        // returns false if stopping fight runs as normal
        // returns true if more stuff needs to be addressed
        //   if fleeing from fight: true if player ran out of patience while trying to flee
        //   if fight is won: true if there is a merge conflict with reward die

        if (!fight_won){
            fightingDinosaur = null;
            if (this.curr_patience <= 0){
                return false;
            }

            if (this.changeCurrPatience(-10)){
                return true;
            };
            return false;
        }
        Ingredient reward_ingredient = fightingDinosaur.getRewardIngredient();
        Dice reward_die = fightingDinosaur.getRewardDie();

        previous_reward = new String[6];
        previous_reward[0] = fightingDinosaur.getName();

        if (reward_ingredient != null){
            addIngredientToInventory(reward_ingredient);
            previous_reward[1] = reward_ingredient.getName();
            previous_reward[2] = reward_ingredient.getRarity().toString();
            previous_reward[3] = reward_ingredient.getType().toString();
        }
        if (reward_die != null){
            previous_reward[4] = reward_die.getName();
            previous_reward[5] = reward_die.getRarity();
            return addDieToInventory(reward_die);
        }
        else {
            previous_reward[4] = "";
        }

        fightingDinosaur = null;
        return false;
    }

//    private void playerLost(){
//
//    }

    public String[] attackDinosaur(int die_index_selection){
        // returned by index: 0: dying party (empty if no death), 1:player roll, 2: dino roll, 3: result

        // roll two die
        int player_roll = this.rollDie(die_index_selection);
        int dino_roll = this.fightingDinosaur.rollDie();

        switch (player_roll){
            case -1000:{
                player_roll = this.previous_roll;
                break;
            }
            case -2000:{
                player_roll = 2*this.previous_roll;
                this.previous_roll = player_roll;
                break;
            }
            case -3000:{
                player_roll = dino_roll+1;
                this.previous_roll = player_roll;
                break;
            }
            case -4000:{
                player_roll = 10;
                if (player_roll > dino_roll){
                    IngredientFactory ingredient_factory;
                    try {
                        ingredient_factory = new IngredientFactory();
                        this.addIngredientToInventory(ingredient_factory.makeIngredient());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                this.previous_roll = player_roll;
                break;
            }
            default:{
                this.previous_roll = player_roll;
            }

        }

        String[] returned_array = new String[4];
        returned_array[0] = "";
        returned_array[1] = String.valueOf(player_roll);
        returned_array[2] = String.valueOf(dino_roll);

        // compare the two rolls
        int roll_difference = player_roll - dino_roll;

        if (roll_difference >= 5){ // embarrassing win
            // print whatever output
            returned_array[3] = "How Embarrassing!\nDino loses " + (int) (40 * this.damage_modifier) + " patience";
            if (fightingDinosaur.changeCurrPatience(-1* (int) (40*this.damage_modifier))){
                // dino "died", address
                returned_array[0] = "dino";
            }
        }
        else if (roll_difference > 0){ // normal win
            // print whatever output
            returned_array[3] = "Hooray!\nDino loses " + (int) (20 * this.damage_modifier) + " patience";
            if (fightingDinosaur.changeCurrPatience(-1* (int) (20*this.damage_modifier))){
                // dino "died", address
                returned_array[0] = "dino";
            };

        }
        else if (roll_difference == 0){ // tie
            // print whatever output
            returned_array[3] = "Draw!\nYou both lose 10 patience";
            if (this.changeCurrPatience(-10)){
                // player "died", address
                returned_array[0] = "player";
            }
            else if (fightingDinosaur.changeCurrPatience(-10)){
                // dino "died", address
                returned_array[0] = "dino";
            };

        }
        else if (roll_difference > -5){ // normal loss
            // print whatever output
            returned_array[3] = "Oh no!\nYou lose " + (int) (20*this.fightingDinosaur.getDinosaurType().getDamageModifier()) + " patience";
            if(this.changeCurrPatience( -1* (int) (20*this.fightingDinosaur.getDinosaurType().getDamageModifier()))){
                // player "died", address
                returned_array[0] = "player";
            };

        }
        else { // embarrassing loss
            // print whatever output
            returned_array[3] = "How Embarrassing!\nYou lose " + (int) (40*this.fightingDinosaur.getDinosaurType().getDamageModifier()) + " patience";
            if(this.changeCurrPatience(-1* (int) (40 * this.fightingDinosaur.getDinosaurType().getDamageModifier()))){
                // player "died", address
                returned_array[0] = "player";
            };
        }

        return returned_array;

    }

    public void addToDamageModifier(int percent_change){
        this.damage_modifier *= 1 + ((double) percent_change / 100);
    }


    public List<String[]> getIngredientInventory() {

        List<String[]> ingredient_inventory = new ArrayList<>();
        for (String ingredient_name : this.ingredient_inventory.keySet()){
            String[] curr_ingredient_information = new String[5];
            List<Ingredient> ingredient_list_with_correct_name = this.ingredient_inventory.get(ingredient_name);
            Ingredient example_ingredient = ingredient_list_with_correct_name.get(0);

            curr_ingredient_information[0] = String.valueOf(ingredient_list_with_correct_name.size());
            curr_ingredient_information[1] = example_ingredient.getName();
            curr_ingredient_information[2] = example_ingredient.getType().toString();
            curr_ingredient_information[3] = example_ingredient.getRarity().toString();
            curr_ingredient_information[4] = "false";
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

    public Ingredient removeIngredientFromInventory(String ingredient_name){
            if (this.ingredient_inventory.containsKey(ingredient_name)){
                // we know that the ingredient is in there... check the length of the list
                if (this.ingredient_inventory.get(ingredient_name).size() > 1){
                    // then we have multiple!
                    int last_index = this.ingredient_inventory.get(ingredient_name).size() - 1;
                    Ingredient needed_ingredient = this.ingredient_inventory.get(ingredient_name).get(last_index);
                    this.ingredient_inventory.get(ingredient_name).remove(last_index);
                    return needed_ingredient;
                }
                else {
                    // we have a string name that points to a list of one item. Remove it all
                    Ingredient needed_ingredient = this.ingredient_inventory.get(ingredient_name).get(0);
                    this.ingredient_inventory.remove(ingredient_name);
                    return needed_ingredient;
                }

            } else {
                return null;
            }
    }

    public boolean addDieToInventory(Dice die){

        if (active_dice_inventory[1] == null){
            active_dice_inventory[1] = die;
            return false;
        }
        else if (active_dice_inventory[2] == null){
            active_dice_inventory[2] = die;
            return false;
        }
        potential_die = die;
        return true;

    }

    public void solveDieMergeConflict(int index_replaced){
        if (index_replaced == 3) {
            potential_die = null;
        }
        else {
            active_dice_inventory[index_replaced] = potential_die;
        }
    }

    public String getDinoImage(){
        String base_string = "file:src/main/resources/org/example/breadfest/Images/";
        return switch (this.fightingDinosaur.getDinosaurType()){
            case Common -> base_string + "dino1.100x.gif";
            case Rare -> base_string + "dino2.100x.gif";
            case Epic -> base_string + "dino3.100x.gif";
            case Nuclear -> base_string + "dino_button_image.png";
        };
    }

    public String[] getFightersInformation(){
        String[] returned_strings = new String[6];
        returned_strings[0] = "Player";
        returned_strings[1] = String.valueOf(curr_patience);
        returned_strings[2] = String.valueOf(base_patience);
        returned_strings[3] = fightingDinosaur.getName();
        returned_strings[4] = String.valueOf(fightingDinosaur.getCurrPatience());
        returned_strings[5] = String.valueOf(fightingDinosaur.getDinosaurType().getBasePatience());

        return returned_strings;
    }
    public String[] getActiveDieInventoryInformation(int die_index) {

        String[] returned_strings = new String[3];

        Dice curr_die;
        if (die_index == 3){
            curr_die = this.potential_die;
        }
        else{
            curr_die = this.active_dice_inventory[die_index];
        }

        if (curr_die == null){
            returned_strings[0] = "null";
            returned_strings[2] = "";
            return returned_strings;
        }
        returned_strings[0] = curr_die.getName();
        returned_strings[1] = curr_die.getDescription();
        returned_strings[2] = curr_die.getPDFImage();
        return returned_strings;
    }

    public void setFightingDinosaur(Dinosaur dino_to_fight){
        this.fightingDinosaur = dino_to_fight;
    }

    public String[] getPreviousReward(){
        return this.previous_reward;
    }
}
