package org.example.breadfest.dice;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurTypes;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DieFactory {

    public Dice DieFactory(DinosaurTypes dinosaur_type) throws Exception {

        // grab folder of ingredients with correct type/rarity, and load classes
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
        return (Dice) constructor.newInstance();

    }
}
