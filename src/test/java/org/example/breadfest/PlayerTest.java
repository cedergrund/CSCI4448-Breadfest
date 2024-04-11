package org.example.breadfest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getInstance() {
        Player player_test_1 = Player.getInstance();
        Player player_test_2 = Player.getInstance();
        assertEquals(player_test_2, player_test_1);
    }

    @Test
    void changeCurrPatience() {
        Player player_test_1 = Player.getInstance();
        int curr_patience_test = player_test_1.getCurrPatience();
        player_test_1.changeCurrPatience(0);

    }

    @Test
    void getCurrPatience() {
    }

    @Test
    void resetPatience() {
    }

    @Test
    void upgradeBasePatience() {
    }

    @Test
    void rollCurrentDie() {
    }

    @Test
    void fightDinosaur() {
    }

    @Test
    void addToDamageModifier() {
    }

    @Test
    void getIngredientInventory() {
    }

    @Test
    void addIngredientToInventory() {
    }
}