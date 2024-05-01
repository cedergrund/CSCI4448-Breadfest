package org.example.breadfest;

import org.example.breadfest.ingredients.IngredientFactory;
import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.junit.jupiter.api.Test;

class ZMainApplicationTest {

    @Test
    void main() {

        IngredientFactory test = null;
        try {
            test = new IngredientFactory();
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Water, IngredientRarity.Nuclear));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Flour, IngredientRarity.Nuclear));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Salt, IngredientRarity.Nuclear));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Yeast, IngredientRarity.Nuclear));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Water, IngredientRarity.Champion));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Flour, IngredientRarity.Champion));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Salt, IngredientRarity.Champion));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Yeast, IngredientRarity.Champion));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Water, IngredientRarity.Champion));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Flour, IngredientRarity.Champion));
            Player.getInstance().addIngredientToInventory(test.makeIngredientByType(IngredientTypes.Salt, IngredientRarity.Champion));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        MainApplication.main(null);
    }
}