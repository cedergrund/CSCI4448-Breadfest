package org.example.breadfest.ingredients;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.lang.reflect.Constructor;
import java.io.File;
import java.util.Random;


public class IngredientFactory {

    public Ingredients IngredientFactory(IngredientTypes ingredient_type, IngredientRarity ingredient_rarity) throws Exception {

        // grab folder of ingredients with correct type/rarity, and load classes
        String file_path_name = ingredient_type.toString() + "/" + ingredient_rarity.toString();
        File directory = new File(file_path_name);
        File[] files = directory.listFiles();

        List<Class<?>> classes = new ArrayList<>();
        assert files != null;
        for (File file : files) {
            // Get the class name without the .class extension
            String className = file.getName().replace(".class", "");

            // Load the class
            Class<?> loadedClass = Class.forName(className);
            classes.add(loadedClass);
        }

        // randomly select an ingredient of the right type
        Random random = new Random();
        Class<?> selectedClass = classes.get(random.nextInt(classes.size()));

        // grab constructor of ingredient and create ingredient of desired type
        Constructor<?> constructor = selectedClass.getConstructor();
        return (Ingredients) constructor.newInstance();

    }

    public Ingredients makeNuclearIngredient() throws Exception {
        return IngredientFactory(IngredientTypes.getRandomIngredientType(), IngredientRarity.Nuclear);
    }
}
