package org.example.breadfest.ingredients;

import org.example.breadfest.dinosaurs.DinosaurFactory;

import java.lang.reflect.Constructor;
import java.io.File;


public class IngredientFactory {

    public Ingredients makeIngredient() throws Exception {

        IngredientTypes ingredient_type = IngredientTypes.getRandomIngredientType();
        IngredientRarity ingredient_rarity = IngredientRarity.getRandomIngredientRarity();

        return this.makeIngredientByType(ingredient_type, ingredient_rarity);

    }

    public Ingredients makeIngredientByType(IngredientTypes ingredient_type, IngredientRarity ingredient_rarity) throws Exception {

        // grab folder of ingredients with correct type/rarity, and load classes
        String file_path_name = ingredient_type.toString() + "/" + ingredient_rarity.toString();
        File directory = new File(file_path_name);
        Constructor<?> constructor = DinosaurFactory.grabRandomClassConstructor(directory);
        return (Ingredients) constructor.newInstance();

    }

    public Ingredients makeNuclearIngredient() throws Exception {
        return makeIngredientByType(IngredientTypes.getRandomIngredientType(), IngredientRarity.Nuclear);
    }
}
