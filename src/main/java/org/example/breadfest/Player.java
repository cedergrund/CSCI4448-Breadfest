package org.example.breadfest;

import java.util.ArrayList;

public class Player {

    private static final Player player = new Player();

    private final ArrayList<String> inventory;

    // base patience is starting patience at every time you enter maze
    private int base_patience;

    // curr patience starts at base patience and then is updated as you lose it, until turn is over
    private int curr_patience;

    private Player() {
        base_patience = 100;
        curr_patience = base_patience;
        inventory = new ArrayList<String>();
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


}
