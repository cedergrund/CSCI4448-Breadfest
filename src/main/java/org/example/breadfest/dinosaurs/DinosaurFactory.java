package org.example.breadfest.dinosaurs;

import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DinosaurFactory {

    public Dinosaur DinosaurFactory(DinosaurTypes dinosaur_type) throws Exception {

        // grab folder of ingredients with correct type, and load classes
        File directory = new File(dinosaur_type.toString());
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

        return (Dinosaur) constructor.newInstance();

    }
}
