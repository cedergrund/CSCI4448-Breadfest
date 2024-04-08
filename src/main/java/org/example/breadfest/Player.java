package org.example.breadfest;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredients;

import java.util.ArrayList;

public class Player {

    private static final Player player = new Player();

    private final ArrayList<Ingredients> ingredient_inventory;

    // dice_inventory is all die that player has, while active_dice_inventory are the 3 that they currently use
    private final ArrayList<Dice> dice_inventory;

    private final Dice[] active_dice_inventory;

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

        this.ingredient_inventory = new ArrayList<Ingredients>();

        this.dice_inventory = new ArrayList<>();
        this.active_dice_inventory = new Dice[3];
    }

    // singleton player
    public static Player getInstance() {
        return player;
    }

    public boolean changeCurrPatience(int patience_change){
        this.curr_patience += 5;
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

    public int rollDice(int selected_die){
        return this.active_dice_inventory[selected_die-1].rollDice();
    }

    public void fightDinosaur(Dinosaur dinosaur){

        // prompt selection of which die to use {1, 2, or 3 (if have 3 dice, do check on availability here)}
        int selected_die = 1;

        // roll two die
        int player_roll = this.rollDice(selected_die);
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




}
