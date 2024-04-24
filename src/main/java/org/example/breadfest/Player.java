package org.example.breadfest;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dice.DieFactory;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredient;

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

    // base patience is starting patience at every time you enter maze
    private int base_patience;

    // curr patience starts at base patience and then is updated as you lose it, until turn is over
    private int curr_patience;

    //base honor is starting honor at every time you begin the game!
    private int base_honor;
    // you curr honor is the honor the player has at any given moment
    private int curr_honor;


    // damage modifier in fights
    private double damage_modifier;

    private Player() {
        this.base_patience = 110;
        this.base_honor = 0;
        this.curr_patience = base_patience;
        this.curr_honor = base_honor;
        this.damage_modifier = 1.0;

        this.ingredient_inventory = new HashMap<>();

        this.active_dice_inventory = new Dice[3];
        this.active_dice_inventory[0] = DieFactory.generateBaseDie();
        this.active_dice_inventory[1] = null;
        this.active_dice_inventory[2] = null;
    }



    // singleton player
    public static Player getInstance() {
        return player;
    }

    public boolean changeCurrPatience(int patience_change){
        this.curr_patience += patience_change;
        return this.curr_patience <= 0;
    }

    public boolean changeCurrHonor(int honor_change){
        this.curr_honor += honor_change;
        return this.curr_patience >= 1000; //returns true if honor exceeds threshold of 1000
    }

    public Dice[] getActiveDieInventory() {
        return this.active_dice_inventory;
    }

    public int getCurrPatience() {
        return this.curr_patience;
    }
    public int getCurrHonor() {
        return this.curr_honor;
    }

    public int getBasePatience() { return this.base_patience; }

    public int getBaseHonor() {return this.base_honor;}

    public void resetPatience(){
        this.curr_patience = this.base_patience;
    }

    public void upgradeBasePatience(int upgrade_amount){
        this.base_patience += upgrade_amount;
    }

    public int rollDie(int index_selection){
        return this.active_dice_inventory[index_selection].rollDice();
    }

    public boolean beatDinosaur(){
        Ingredient reward_ingredient = fightingDinosaur.getRewardIngredient();
        Dice reward_die = fightingDinosaur.getRewardDie();

        if (reward_ingredient != null){
            addIngredientToInventory(reward_ingredient);
        }
        if (reward_die != null){
            return addDieToInventory(reward_die);
        }

        fightingDinosaur = null;
        return false;
    }

//    private void playerLost(){
//
//    }

    public int[] attackDinosaur(int die_index_selection){
        // returned by index: 0:player roll, 1: dino roll, 2: result

        // results:
        // returns 0 if no one died
        // returns 1 if player died
        // returns 2 if dino died

        // roll two die
        int player_roll = this.rollDie(die_index_selection);
        int dino_roll = this.fightingDinosaur.rollDie();

        int[] returned_array = new int[3];
        returned_array[0] = player_roll;
        returned_array[1] = dino_roll;

        // compare the two rolls
        int roll_difference = player_roll - dino_roll;

        if (roll_difference >= 5){ // embarrassing win
            // print whatever output
            if (fightingDinosaur.changeCurrPatience((int) (-40*this.damage_modifier))){
                // dino "died", address
                returned_array[2] = 2;
            }
        }
        else if (roll_difference > 0){ // normal win
            // print whatever output
            if (fightingDinosaur.changeCurrPatience((int) (-20*this.damage_modifier))){
                // dino "died", address
                returned_array[2] = 2;
            };

        }
        else if (roll_difference == 0){ // tie
            // print whatever output
            if (this.changeCurrPatience(-10)){
                // player "died", address
                returned_array[2] = 1;
            };
            if (fightingDinosaur.changeCurrPatience(-10)){
                // dino "died", address
                returned_array[2] = 2;
            };

        }
        else if (roll_difference > -5){ // normal loss
            // print whatever output
            if(this.changeCurrPatience(-20)){
                // player "died", address
                returned_array[2] = 1;
            };

        }
        else { // embarrassing loss
            // print whatever output
            if(this.changeCurrPatience(-40)){
                // player "died", address
                returned_array[2] = 1;
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
        active_dice_inventory[index_replaced] = potential_die;
    }


    public String[] getFightersInformation(){
        String[] returned_strings = new String[5];
        returned_strings[0] = "Player";
        returned_strings[1] = String.valueOf(curr_patience);
        returned_strings[2] = fightingDinosaur.getName();
        returned_strings[3] = String.valueOf(fightingDinosaur.getCurrPatience());

        return returned_strings;
    }
    public String[][] getActiveDieInventoryInformation() {
        String[][] returned_strings = new String[3][3];

        for (int die_index = 0; die_index < 3; die_index++){
            Dice curr_die = this.active_dice_inventory[die_index];
            if (curr_die == null){
                returned_strings[die_index][0] = "null";
                continue;
            }
            returned_strings[die_index][0] = curr_die.getName();
            returned_strings[die_index][1] = curr_die.getDescription();
            returned_strings[die_index][2] = curr_die.getPDFImage();
        }
        return returned_strings;
    }

    public void setFightingDinosaur(Dinosaur dino_to_fight){
        this.fightingDinosaur = dino_to_fight;
    }

    public Dinosaur getFightingDinosaur(){
        return this.fightingDinosaur;
    }
}
