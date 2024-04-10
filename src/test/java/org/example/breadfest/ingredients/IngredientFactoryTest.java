package org.example.breadfest.ingredients;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IngredientFactoryTest {

    @Test
    void testCreation() throws IOException {
        IngredientFactory test_factory = new IngredientFactory();
        assertNotNull(test_factory);
    }

    @Test
    void makeIngredient() throws IOException {
        IngredientFactory test_factory = new IngredientFactory();
        Ingredient test_ingredient = null;
        try {
            test_ingredient = test_factory.makeIngredient();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(test_ingredient);
    }

    @Test
    void makeIngredientByType() throws IOException {
        IngredientFactory test_factory = new IngredientFactory();
        Ingredient test_ingredient = null;
        IngredientRarity desired_rarity = IngredientRarity.Champion;
        IngredientTypes desired_type = IngredientTypes.Salt;

        try {
            test_ingredient = test_factory.makeIngredientByType(desired_type, desired_rarity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(test_ingredient);
        assertSame(desired_type, test_ingredient.getType());
        assertSame(desired_rarity, test_ingredient.getRarity());
    }

    @Test
    void makeNuclearIngredient() throws IOException {
        IngredientFactory test_factory = new IngredientFactory();
        Ingredient test_ingredient = null;
        try {
            test_ingredient = test_factory.makeNuclearIngredient();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(test_ingredient);
        assertSame(IngredientRarity.Nuclear, test_ingredient.getRarity());

    }
}