package org.example.breadfest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.breadfest.ingredients.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void getName() throws IOException {
        String expected_name = "Test";
        Ingredient test_ingredient = new Ingredient(expected_name, IngredientTypes.Flour, IngredientRarity.Common);
        assertEquals(expected_name, test_ingredient.getName());
    }

    @Test
    void getType() {
        IngredientTypes expected_type = IngredientTypes.Flour;
        Ingredient test_ingredient = new Ingredient("test", expected_type, IngredientRarity.Common);
        assertEquals(expected_type, test_ingredient.getType());
    }

    @Test
    void getRarity() {
        IngredientRarity expected_rarity = IngredientRarity.Common;
        Ingredient test_ingredient = new Ingredient("test", IngredientTypes.Flour, expected_rarity);
        assertEquals(test_ingredient.getRarity(), expected_rarity);
    }

    @Test
    void getScore() {
        Ingredient test_ingredient = new Ingredient("test", IngredientTypes.Flour, IngredientRarity.Common);
        assertEquals(test_ingredient.getScore(), 5*10);
    }

    @Test
    void testFactoryCreation() throws IOException {
        IngredientFactory test_factory = new IngredientFactory();
        assertNotNull(test_factory);
    }


    @Test
    void makeIngredient() throws Exception {
        IngredientFactory test_factory = new IngredientFactory();
        Ingredient test_ingredient = test_factory.makeIngredient();
        assertNotNull(test_ingredient);
    }

    @Test
    void makeIngredientByType() throws Exception {

        IngredientFactory test_factory = new IngredientFactory();
        Ingredient test_ingredient = test_factory.makeIngredientByType(IngredientTypes.Flour, IngredientRarity.Common);
        assertNotNull(test_ingredient);
        assertEquals(test_ingredient.getType(), IngredientTypes.Flour);
        assertEquals(test_ingredient.getRarity(), IngredientRarity.Common);

    }

    @Test
    void makeNuclearIngredient() throws Exception {
        IngredientFactory test_factory = new IngredientFactory();
        Ingredient test_ingredient = test_factory.makeNuclearIngredient();
        assertNotNull(test_ingredient);
        assertEquals(test_ingredient.getRarity(), IngredientRarity.Nuclear);
    }
}